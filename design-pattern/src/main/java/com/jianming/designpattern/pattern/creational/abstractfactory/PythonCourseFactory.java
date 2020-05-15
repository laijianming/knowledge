package com.jianming.designpattern.pattern.creational.abstractfactory;

/**
 * @author jianming
 * @create 2020-04-14-16:32
 */
public class PythonCourseFactory extends CourseFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }

    @Override
    public Article getArticle() {
        return new PythonArticle();
    }
}
