package com.jianming.designpattern.pattern.creational.singleton;

/**
 * 懒汉式单例模式
 *  初始化类时是没有加载对象的，并且构造器也是private的
 *
 *  这种方式是有线程安全的
 * @author jianming
 * @create 2020-05-17-9:42
 */
public class LazySingleTon {

    private static LazySingleTon lazySingleTon = null;

    private LazySingleTon() {}

    public static LazySingleTon getInstance() {
        // 只有在使用的时候才会去实例化
        if(lazySingleTon == null) {
            lazySingleTon = new LazySingleTon();
        }
        return lazySingleTon;
    }


}
