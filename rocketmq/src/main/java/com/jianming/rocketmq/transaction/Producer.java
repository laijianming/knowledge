package com.jianming.rocketmq.transaction;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 发送事务消息
 *      发送步骤：消息先发送到broker，broker回查Producer的事务状态，确认状态后返回发送结果，
 *          并根据事务回查得到的事务状态对其消息做相应处理
 * @author jianming
 * @create 2020-03-08-7:48
 */
public class Producer {

    public static void main(String[] args) throws Exception {

        //1.	创建消息生产者Producer，并制定生产者组名  {==使用事务Producer==}
        TransactionMQProducer producer = new TransactionMQProducer("TransactionGroup");
        //2.	指定NameServer地址
        producer.setNamesrvAddr("192.168.0.141:9876");
        //  {==设置事务监听器，用于服务端给Producer回查事务==}
        producer.setTransactionListener(new TransactionListener() {
            /**
             * 用于回查事务状态
             * @param message
             * @param o
             * @return
             */
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                System.out.println("事务回查方法  TAG = " + message.getTags());
                if(StringUtils.equals("TAGA",message.getTags())) {
                    // 提交
                    return LocalTransactionState.COMMIT_MESSAGE;
                }else if(StringUtils.equals("TAGB",message.getTags())) {
                    // 回滚
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }else if(StringUtils.equals("TAGC",message.getTags())) {
                    // 回查 -> 事务补偿   注：server默认 UNKNOW 去事务补偿处理的等待时间较长
                    return LocalTransactionState.UNKNOW;
                }
                return LocalTransactionState.UNKNOW;
            }

            /**
             * 用于事务补偿
             * @param messageExt
             * @return
             */
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                // 提交
                System.out.println("进入事务补偿方法  TAG = "  + messageExt.getTags());
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });
        String[] tags = new String[]{"TAGA","TAGB","TAGC"};
        //3.	启动Broker （启动Producer实例）
        producer.start();
        //4.	创建消息对象，指定主题Topic、Tag和消息体
        for(int i = 0, len = 3; i < len; i ++) {
            // 参数一：Topic  参数二：Tag  参数三：消息
            Message message = new Message("TransactionTopic",tags[i],("hello transaction " + i).getBytes());
            //5.	发送消息  {==参数二为单条消息事务处理方式，这里统一使用事务监听器的处理方式，故传null==}
            SendResult send = producer.sendMessageInTransaction(message,null);
            System.out.println("发送结果：" + send);
        }
        // 不关闭Producer，需等待事务监听工作
        // provider.shutdown();

    }

}
