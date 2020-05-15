package com.jianming.designpattern.pattern.creational.factorymethod;

/**
 * @author jianming
 * @create 2020-04-14-11:23
 */
public class JavaVideoFactory extends VideoFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }
}
