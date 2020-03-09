package com.jianming.rocketmq.order;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * 顺序发送消息
 * @author jianming
 * @create 2020-03-08-15:09
 */
public class Producer {

    public static void main(String[] args) throws Exception{

        // 1、创建消息生产者Producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("OrderGroup");
        // 2、制定NameServer地址
        producer.setNamesrvAddr("192.168.0.141:9876");
        // 3、启动Producer
        producer.start();
        // 4、发送多组消息,以组id选择队列顺序发送
        // 消息组id
        int[] m = new int[]{1,2,1,3,2,1,3,3,2};
        for(int i = 0; i < 9; i ++) {
            Message msg = new Message("OrderTopic","OrderTag",(m[i] + "的消息 第 " + i + "条").getBytes());
            /**
             * {==send方法==}
             *  参数一：消息对象
             *  参数二：消息队列选择器
             *  参数三：选择队列的业务标识（用来选择队列的标识）
             */
            SendResult send = producer.send(msg, (List<MessageQueue> list, Message message, Object o) -> {
                /**
                 * {==消息队列选择器 MessageQueueSelector==}
                 *  参数一：可选择的消息队列
                 *  参数二：发送的消息
                 *  参数三：传入的业务标识
                 */
                int id = (int) o;
                // 使用对id取模的方式，将同一id的消息按先后顺序发送到同一队列中
                // （可能不连在一起，但保证了消息的先后顺序）
                int index = list.size() % id;
                return list.get(index);
            }, m[i]);

            System.out.println("send = " + send);
        }
        // 5、关闭生产者Producer
        producer.shutdown();

    }

}
