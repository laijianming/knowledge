package com.jianming.designpattern.pattern.creational.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 容器单例模式
 *  多线程情况下也是有线程安全隐患的，如同未加双重检查锁的懒汉模式
 *  适用于单线程加载时就已经将所有单例放入容器中的情况
 *
 *  优点：统一管理，节省资源
 *  缺点：线程不安全
 *
 * @author jianming
 * @create 2020-05-17-12:48
 */
public class ContainerSingleton {

    private static Map<String,Object> singletonMap = new HashMap<>();

    private ContainerSingleton() {}

    public static void putInstance(String key, Object instance) {
        if(key.trim() != "" && instance != null) {
            if(!singletonMap.containsKey(key)) {
                singletonMap.put(key,instance);
            }
        }
    }

    public static Object getInstance(String key) {
        return singletonMap.get(key);
    }

}
