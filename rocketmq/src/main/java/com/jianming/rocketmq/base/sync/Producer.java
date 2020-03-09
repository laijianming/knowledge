package com.jianming.rocketmq.base.sync;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 发送同步消息
 * @author jianming
 * @create 2020-03-08-7:48
 */
public class Producer {

    public static void main(String[] args) throws Exception {

        //1.	创建消息生产者Producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("rocketMQ_group");
        //2.	指定NameServer地址
        producer.setNamesrvAddr("192.168.0.141:9876");
        //3.	启动Broker （启动Producer实例）
        producer.start();
        //4.	创建消息对象，指定主题Topic、Tag和消息体
        for(int i = 0, len = 10; i < len; i ++) {
            // 参数一：Topic  参数二：Tag  参数三：消息
            Message message = new Message("rocketMQ_Topic","Tag_sync",("hello " + i).getBytes());
            //5.	发送消息
            SendResult send = producer.send(message);
            System.out.println("发送结果：" + send);
            Thread.sleep(500);
        }
        //6.	关闭生产者Producer
        producer.shutdown();

    }

}
