package com.jianming.designpattern.pattern.creational.abstractfactory;

/**
 * java 产品族的工厂，可生产java产品族的所有对象
 * @author jianming
 * @create 2020-04-14-16:31
 */
public class JavaCourseFactory extends CourseFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }

    @Override
    public Article getArticle() {
        return new JavaArticle();
    }
}
