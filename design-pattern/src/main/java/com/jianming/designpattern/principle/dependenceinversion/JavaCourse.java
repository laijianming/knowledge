package com.jianming.designpattern.principle.dependenceinversion;

/**
 * @author jianming
 * @create 2020-04-14-6:39
 */
public class JavaCourse implements ICourse {
    @Override
    public void study() {
        System.out.println("正在学习java课程");
    }
}
