package com.jianming.rocketmq.transaction;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 接收事务消息
 * @author jianming
 * @create 2020-03-08-7:49
 */
public class Consumer {

    public static void main(String[] args) throws Exception {

        //1.	创建消息消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("TransactionGroup");
        //2.	指定NameServer地址
        consumer.setNamesrvAddr("192.168.0.141:9876");
        //3.	订阅主题Topic和Tag  若接收到TAGA 和TAGC 的消息，则表示事物消息成功
        consumer.subscribe("TransactionTopic","*");
        //4.	设置回调函数，处理消息  MessageListenerConcurrently = 多线程获取各个队列消息
        consumer.registerMessageListener((List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) -> {
            list.forEach(x -> System.out.println("msg = " + new String(x.getBody()) + " || TAG = " + x.getTags()));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        //5.	启动消费者Consumer 一直监听
        consumer.start();

    }

}
