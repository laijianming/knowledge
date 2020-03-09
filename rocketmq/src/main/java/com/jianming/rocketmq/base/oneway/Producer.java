package com.jianming.rocketmq.base.oneway;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * 发送单向消息
 *      生产者不关心发送结果,不获取发送结果
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
            Message message = new Message("rocketMQ_Topic","Tag_one_way",("hello one way " + i).getBytes());
            //5.	{==发送单向消息  sendOneway==}
            producer.sendOneway(message);
        }
        //6.	关闭生产者Producer
        producer.shutdown();

    }

}
