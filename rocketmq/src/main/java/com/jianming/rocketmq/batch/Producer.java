package com.jianming.rocketmq.batch;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * 发送批量消息
 *      一次发送多条消息
 * @author jianming
 * @create 2020-03-08-7:48
 */
public class Producer {

    public static void main(String[] args) throws Exception {

        //1.	创建消息生产者Producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("BatchGroup");
        //2.	指定NameServer地址
        producer.setNamesrvAddr("192.168.0.141:9876");
        //3.	启动Broker （启动Producer实例）
        producer.start();

        // 消息集合
        List<Message> messages = new ArrayList<>();
        //4.	创建消息对象，指定主题Topic、Tag和消息体
        for(int i = 0, len = 10; i < len; i ++) {
            // 参数一：Topic  参数二：Tag  参数三：消息
            Message message = new Message("BatchTopic","BatchTag",("hello batch msg " + i).getBytes());
            messages.add(message);
        }
        //5.    {==发送批量消息==} 注：批量消息单次总大小不能大于4MB
        SendResult send = producer.send(messages);
        System.out.println("发送结果：" + send);
        //6.	关闭生产者Producer
        producer.shutdown();

    }

}
