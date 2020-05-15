package com.jianming.designpattern.pattern.creational.factorymethod;

/**
 * @author jianming
 * @create 2020-04-14-9:29
 */
public class JavaVideo extends Video {
    @Override
    public void produce() {
        System.out.println("正在制作Java视频");
    }
}
