package com.jianming.designpattern.pattern.creational.abstractfactory;

/**
 * 抽象工厂模式
 *  工厂类的子类来创建该产品族的所有产品对象
 *  应用层不关心该对象如何创建且与创建该对象无关 -- 应用层（Test）  工厂创建对象（JavaVideo、JavaArticle）
 * @author jianming
 * @create 2020-04-14-16:33
 */
public class Test {

    public static void main(String[] args) {
        CourseFactory courseFactory = new JavaCourseFactory();
        Video video = courseFactory.getVideo();
        Article article = courseFactory.getArticle();
        video.produce();
        article.produce();
    }


}
