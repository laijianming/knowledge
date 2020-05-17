package com.jianming.designpattern.pattern.creational.singleton;

/**
 * 枚举类单例模式
 *  枚举类天然的可序列化机制 强力的保证了不会出现多次实例化的情况
 *
 * @author jianming
 * @create 2020-05-17-11:49
 */
public enum EnumInstance {
    INSTANCE{
        @Override
        protected void print() {
            System.out.println("枚举类对象输出");
        }
    };

    /**
     * 通过这个抽象方法来调用 INSTANCE 中的方法
     */
    protected abstract void print();

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumInstance getInstance() {
        return INSTANCE;
    }
}
