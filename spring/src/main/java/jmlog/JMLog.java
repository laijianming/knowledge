package jmlog;

import java.time.LocalDateTime;

/**
 * 日志类
 * @author jianming
 * @create 2020-05-16-11:28
 */
public class JMLog {

    public static void info(Class clazz,String log) {
        // 等级 时间 线程 类名 日志
        // 注：不使用println的原因 -- println是给out对象加锁了的（避免线程冲突导致换行出现不对应位置），影响性能。
        //     所以直接在字符串后加 "\n" 来达到换行的效果，且不用加锁
        System.out.print("[INFO] " + LocalDateTime.now() + " [" +Thread.currentThread().getName() + "] " + clazz.getName() + " -> " + log + "\n");
    }

    public static void log(String log) {
        System.out.println(log);
    }


}
