package com.jianming.rocketmq.filter.tag;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * tag 过滤样例
 * 接收消息 根据tag过滤
 * @author jianming
 * @create 2020-03-08-7:49
 */
public class Consumer {

    public static void main(String[] args) throws Exception {

        //1.	创建消息消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("filterGroup");
        //2.	指定NameServer地址
        consumer.setNamesrvAddr("192.168.0.141:9876");
        //3.	订阅主题Topic和Tag  {==根据Tag过滤，只接收Tag0和Tag2的消息==}
        consumer.subscribe("filterTopic","filterTag0 || filterTag2");
        //4.	设置回调函数，处理消息  MessageListenerConcurrently = 多线程获取各个队列消息
        consumer.registerMessageListener((List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) -> {
            list.forEach(x -> System.out.println("msg = " + new String(x.getBody()) + " ||  Tag = " + x.getTags()));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        //5.	启动消费者Consumer 一直监听
        consumer.start();


    }


}
