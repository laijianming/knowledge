package com.jianming.designpattern.pattern.creational.singleton;

/**
 * 基于ThreadLocal的"单例"模式
 *  可以保证单个线程中的单例，但不能保证全局单例
 *
 *  ThreadLocal会给每个线程一个独立的实例副本
 *
 *  适用于 request 域的IOC设置
 *
 * @author jianming
 * @create 2020-05-17-12:58
 */
public class ThreadLocalInstance {

    private static final ThreadLocal<ThreadLocalInstance> THREAD_LOCAL_INSTANCE_THREAD_LOCAL =
            new ThreadLocal<>();

    private ThreadLocalInstance() {}

    public static ThreadLocalInstance getInstance() {
        return THREAD_LOCAL_INSTANCE_THREAD_LOCAL.get();
    }



}
