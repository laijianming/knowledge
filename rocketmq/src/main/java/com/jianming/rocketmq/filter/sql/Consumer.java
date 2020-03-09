package com.jianming.rocketmq.filter.sql;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * sql 过滤样例
 *      接收消息 根据用户消息属性的sql过滤
 *      MessageSelector.bySql("表达式")
 *
 *      注：使用sql过滤时会出现 “MQ不支持SQL过滤错误” ==> Exception : The broker does not support consumer to filter message by SQL92
 *      TIPS •该方式只支持RoketMQ，不支持Kafka/RabbitMQ•用了sql，就不要用Tag
 *      RocketMQ支持使用SQL语法过滤消息。官方文档：http://rocketmq.apache.org/rocketmq/filter-messages-by-sql92-in-rocketmq/
 *
 *      开启SQL 92支持
 *      默认情况下，RocketMQ的SQL过滤支持是关闭的，要想使用SQL 92过滤消息，需要：
 *      1 在 conf/broker.conf 添加
 *      enablePropertyFilter = true
 *
 *      2 启动RocketMQ 在后边带上配置文件
 *      nohup sh bin/mqbroker -n localhost:9876 -c ./conf/broker.conf &
 *
 * @author jianming
 * @create 2020-03-08-7:49
 */
public class Consumer {

    public static void main(String[] args) throws Exception {

        //1.	创建消息消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("filterGroup");
        //2.	指定NameServer地址
        consumer.setNamesrvAddr("192.168.0.141:9876");
        //3.	订阅主题Topic和 用户消息属性条件  {==根据sql过滤，只接收用户属性i >5的消息==}
        // CODE: 1  DESC: The broker does not support consumer to filter message by SQL92
        consumer.subscribe("filterSqlTopic",MessageSelector.bySql("i>5"));
        //4.	设置回调函数，处理消息  MessageListenerConcurrently = 多线程获取各个队列消息
        consumer.registerMessageListener((List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) -> {
            list.forEach(x -> System.out.println("msg = " + new String(x.getBody())));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        //5.	启动消费者Consumer 一直监听
        consumer.start();


    }


}
