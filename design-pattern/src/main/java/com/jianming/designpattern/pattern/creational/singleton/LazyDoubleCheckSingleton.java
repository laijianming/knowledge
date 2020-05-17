package com.jianming.designpattern.pattern.creational.singleton;

/**
 * 单例模式 -- 懒加载
 *  采用双重检查锁的方式避免线程安全问题
 *
 *  存在隐患：
 *      因为指令重排序的原因，new Instance 的操作 2 和 3 可能会颠倒，那么在1 3步骤后，对象还未被初始化
 *        但是其他线程判断对象是不为null的，则直接返回了未初始化的对象
 *
 *  解决方案：
 *   1、 不允许指令重排序 -- volatile关键字 防止指令重排序
 *   2、 允许指令重排序，但不允许其他线程看到这个重排序 -- 基于类初始化的方案，静态内部类
 *
 *  注：单线程情况下，一些指令重排序是不会影响最终结果的
 * @author jianming
 * @create 2020-05-17-10:16
 */
public class LazyDoubleCheckSingleton {

    /**
     * 加 volatile 防止指令重排序
     */
    private volatile static LazyDoubleCheckSingleton instance = null;

    private LazyDoubleCheckSingleton() {}

    public static LazyDoubleCheckSingleton getInstance() {
        // 双重检查锁
        if(instance == null) {
            // 只有一个线程能进来创建对象
            synchronized (LazyDoubleCheckSingleton.class) {
                if(instance == null) {
                    // 创建对象的指令三步骤  -- 2 3步骤可能会倒序；
                    // 1、分配内存
                    // 2、初始化对象  // intra-thread semantics 指令也可以避免指令重排序
                    // 3、设置 instance 指向刚分配的内存地址
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

}
