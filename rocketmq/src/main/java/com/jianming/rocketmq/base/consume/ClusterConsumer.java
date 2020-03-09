package com.jianming.rocketmq.base.consume;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * 负载均衡消费消息（默认）
 *      多个消费者共同消费（轮询或其他方式）同一组消息
 * @author jianming
 * @create 2020-03-08-9:54
 */
public class ClusterConsumer {

    public static void main(String[] args) throws Exception {

        //1.	创建消息消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("rocketMQ_group");
        //2.	指定NameServer地址
        consumer.setNamesrvAddr("192.168.0.141:9876");
        //3.	订阅主题Topic和Tag
        consumer.subscribe("rocketMQ_Topic","Tag_one_way");
        // {==设置消费方式 负载均衡模式（默认）==}
        consumer.setMessageModel(MessageModel.CLUSTERING);
        //4.	设置回调函数，处理消息  MessageListenerConcurrently
        consumer.registerMessageListener((List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) -> {
            list.forEach(x -> System.out.println("msg = " + new String(x.getBody())));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        //5.	启动消费者Consumer 一直监听
        consumer.start();


    }

}
