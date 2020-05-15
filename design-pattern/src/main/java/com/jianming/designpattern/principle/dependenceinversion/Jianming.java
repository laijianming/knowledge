package com.jianming.designpattern.principle.dependenceinversion;

/**
 *  对于ICourse不进行具体的实现依赖
 * @author jianming
 * @create 2020-04-14-6:36
 */
public class Jianming {

    ICourse iCourse;

    public void setiCourse(ICourse iCourse) {
        this.iCourse = iCourse;
    }

    public void study() {
        iCourse.study();
    }

}
