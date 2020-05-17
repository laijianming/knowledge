package com.jianming.designpattern.pattern.creational.singleton;

/**
 * 简单饿汉单例模式
 * @author jianming
 * @create 2020-05-17-11:13
 */
public class HungrySingleton {
    /**
     * 在类加载时就初始化
     */
    private final static HungrySingleton instance;

    static {
        instance = new HungrySingleton();
    }

    private HungrySingleton() {}

    public static HungrySingleton getInstance() {
        return instance;
    }

}
