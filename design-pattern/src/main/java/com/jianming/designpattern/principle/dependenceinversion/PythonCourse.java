package com.jianming.designpattern.principle.dependenceinversion;

/**
 * @author jianming
 * @create 2020-04-14-6:40
 */
public class PythonCourse implements ICourse {
    @Override
    public void study() {
        System.out.println("正在学习python课程");
    }
}
