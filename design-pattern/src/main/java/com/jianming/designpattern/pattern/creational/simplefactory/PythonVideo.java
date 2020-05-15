package com.jianming.designpattern.pattern.creational.simplefactory;

/**
 * @author jianming
 * @create 2020-04-14-9:30
 */
public class PythonVideo extends Video {
    @Override
    public void produce() {
        System.out.println("正在制作Python视频");
    }
}
