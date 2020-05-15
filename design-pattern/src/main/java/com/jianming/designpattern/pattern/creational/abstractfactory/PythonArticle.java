package com.jianming.designpattern.pattern.creational.abstractfactory;

/**
 * @author jianming
 * @create 2020-04-14-16:28
 */
public class PythonArticle extends Article {
    @Override
    public void produce() {
        System.out.println("正在制作Python手记");
    }
}
