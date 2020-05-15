package com.jianming.designpattern.pattern.creational.builder;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

/**
 * @author jianming
 * @create 2020-04-17-9:29
 */
public class Test {

    public static void main(String[] args) {
        Course course = new Course.CourseBuilder().buildCourseName("Java设计模式精讲").buildCoursePPT("Java设计模式精讲PPT").buildCourseVideo("Java设计模式精讲视频").build();
        System.out.println(course);

        /**
         * 创建guava的set也是建造者模式
         */
        Set<String> set = ImmutableSet.<String>builder().add("a").add("b").build();

        System.out.println(set);
    }

}
