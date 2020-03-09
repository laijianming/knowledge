package com.jianming.rocketmq.base.async;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 接收同步消息
 * @author jianming
 * @create 2020-03-08-7:49
 */
public class Consumer {

    public static void main(String[] args) throws Exception {

        //1.	创建消息消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("rocketMQ_group");
        //2.	指定NameServer地址
        consumer.setNamesrvAddr("192.168.0.141:9876");
        //3.	订阅主题Topic和Tag
        consumer.subscribe("rocketMQ_Topic","Tag_async");
        //4.	设置回调函数，处理消息 MessageListenerConcurrently = 多线程获取各个队列消息
        consumer.registerMessageListener((List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) -> {
            list.forEach(x -> System.out.println("msg = " + new String(x.getBody())));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        //5.	启动消费者Consumer 一直监听
        consumer.start();


    }


}
