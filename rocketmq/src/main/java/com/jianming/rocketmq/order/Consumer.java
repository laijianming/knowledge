package com.jianming.rocketmq.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 顺序消费消息
 * @author jianming
 * @create 2020-03-08-15:35
 */
public class Consumer {

    public static void main(String[] args) throws Exception {

        //1.	创建消息消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("OrderGroup");
        //2.	指定NameServer地址
        consumer.setNamesrvAddr("192.168.0.141:9876");
        //3.	订阅主题Topic和Tag
        consumer.subscribe("OrderTopic","*");
        //4.	设置回调函数，处理消息  {==MessageListenerOrderly = 一个队列开一个线程==}
        consumer.registerMessageListener((List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) -> {
            list.forEach(x -> System.out.println(Thread.currentThread().getName() + " msg = " + new String(x.getBody())));
            return ConsumeOrderlyStatus.SUCCESS;
        });
        //5.	启动消费者Consumer 一直监听
        consumer.start();


    }

}
