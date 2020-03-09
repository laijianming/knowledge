package com.jianming.rocketmq.base.async;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 发送异步消息
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
//        producer_sync.setAsyncSenderExecutor(); // 可以设置一个线程池，给其异步发送消息
        //4.	创建消息对象，指定主题Topic、Tag和消息体
        for(int i = 0, len = 10; i < len; i ++) {
            Message message = new Message("rocketMQ_Topic","Tag_async",("hello " + i).getBytes());
            //5.	{==异步发送消息==}
            producer.send(message, new SendCallback() {
                // 回调函数来显示发送结果 --> 即异步发送消息
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("OK sendResult = [" + sendResult + "]");
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("NOT OK = [" + throwable + "]");
                }
            });
        }
        // 这里别关太快，不然异步发送还没发完，Producer就被关了
        Thread.sleep(5000);
        //6.	关闭生产者Producer
        producer.shutdown();

    }

}
