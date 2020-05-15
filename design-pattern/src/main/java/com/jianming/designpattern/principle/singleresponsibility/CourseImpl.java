package com.jianming.designpattern.principle.singleresponsibility;

/**
 * 实现课程的内容获取及相关管理
 * @author jianming
 * @create 2020-04-14-7:11
 */
public class CourseImpl implements ICourseManager,ICourseContent {
    @Override
    public String getCourseName() {
        return null;
    }

    @Override
    public byte[] getCourseVideo() {
        return new byte[0];
    }

    @Override
    public void studyCourse() {

    }

    @Override
    public void refoundCourse() {

    }
}
