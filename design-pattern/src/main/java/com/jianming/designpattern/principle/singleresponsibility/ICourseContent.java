package com.jianming.designpattern.principle.singleresponsibility;

/**
 * 单一职责原则
 *  将课程管理相关内容放置在一个接口
 * @author jianming
 * @create 2020-04-14-7:10
 */
public interface ICourseContent {
    String getCourseName();
    byte[] getCourseVideo();
}
