package com.jianming.designpattern.pattern.creational.abstractfactory;

/**
 * 抽象工厂
 *  产品族的工厂
 *      该工厂能够创建改产品族的所有产品
 * @author jianming
 * @create 2020-04-14-16:29
 */
public abstract class CourseFactory {
    public abstract Video getVideo();
    public abstract Article getArticle();
}
