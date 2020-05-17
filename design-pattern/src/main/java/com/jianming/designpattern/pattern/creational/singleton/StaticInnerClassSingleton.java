package com.jianming.designpattern.pattern.creational.singleton;

/**
 * 基于类初始化的延迟加载解决方案
 * 通过静态内部类的方式，防止指令重排序带来的线程安全隐患
 *  让指令重排序可以进行，且其他线程看不到
 *
 * 多个线程需要先获取到Class对象的初始化锁才能对其进行操作
 *  而在对象初始化过程中，已经将单例对象实例化了，即使发生了指令重排序，
 *  对于其他非构造线程而言，都是看不到的
 *
 * 导致类被立刻初始化的条件
 *  1、有一个该类的实例被创建
 *  2、该类中的静态方法被调用
 *  3、类中声明的静态成员被赋值
 *  4、类中声明的静态成员被使用且这个静态成员不是一个常量成员
 *  5、如果类是一个顶级类，且在类中有断言语句
 *
 * @author jianming
 * @create 2020-05-17-10:31
 */
public class StaticInnerClassSingleton {
    private static class InnerClass {
        private static StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    private StaticInnerClassSingleton() {
        // 防止反射攻击
        if(InnerClass.instance != null) {
            throw new RuntimeException("该类不允许反射创建对象");
        }
    }

    public static StaticInnerClassSingleton getInstance() {
        return InnerClass.instance;
    }

}
