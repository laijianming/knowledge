package com.jianming.designpattern.principle.singleresponsibility;

/**
 * 该接口包含 获取课程名称、获取课程视频 、 学习课程、取消课程 方法
 *  由单一职责原则 应将 课程内容相关放一起，课程管理内容相关放一起
 *  所以应该分为课程内容和管理两个接口
 * @author jianming
 * @create 2020-04-14-7:05
 */
public interface ICourse {
    String getCourseName();
    byte[] getCourseVideo();

    void studyCourse();
    void refoundCourse();
}
