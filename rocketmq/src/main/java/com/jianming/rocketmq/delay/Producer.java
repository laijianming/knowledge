package com.jianming.rocketmq.delay;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 发送延时消息
 *      设定level等级的延时时间，使消费者必须等延时时间之后才能消费消息
 * @author jianming
 * @create 2020-03-08-7:48
 */
public class Producer {

    public static void main(String[] args) throws Exception {

        //1.	创建消息生产者Producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("DelayGroup");
        //2.	指定NameServer地址
        producer.setNamesrvAddr("192.168.0.141:9876");
        //3.	启动Broker （启动Producer实例）
        producer.start();
        //4.	创建消息对象，指定主题Topic、Tag和消息体
        for(int i = 0, len = 10; i < len; i ++) {
            // 参数一：Topic  参数二：Tag  参数三：消息
            Message message = new Message("DelayTopic","TagDelay",("hello delay msg " + i).getBytes());
            // {==延时消息设置==} 参数为level 1=1s 2=5s 3=10s ... 18=2h
            message.setDelayTimeLevel(2);
            //5.	发送消息
            SendResult send = producer.send(message);
            System.out.println("发送结果：" + send);
            Thread.sleep(500);
        }
        //6.	关闭生产者Producer
        producer.shutdown();

    }

}
