package com.jianming.rocketmq.filter.tag;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * tag 过滤样例
 * 发送消息
 * @author jianming
 * @create 2020-03-08-7:48
 */
public class Producer {

    public static void main(String[] args) throws Exception {

        //1.	创建消息生产者Producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("filterGroup");
        //2.	指定NameServer地址
        producer.setNamesrvAddr("192.168.0.141:9876");
        //3.	启动Broker （启动Producer实例）
        producer.start();
        //4.	创建消息对象，指定主题Topic、Tag和消息体
        for(int i = 0, len = 10; i < len; i ++) {
            // {==发送Tag0,1,2的消息==}
            Message message = new Message("filterTopic","filterTag" + (i % 3),("hello tag filter " + i).getBytes());
            //5.	发送消息
            SendResult send = producer.send(message);
            System.out.println("发送结果：" + send);
        }
        //6.	关闭生产者Producer
        producer.shutdown();

    }

}
