package com.jianming.designpattern.pattern.creational.factorymethod;

/**
 * @author jianming
 * @create 2020-04-14-11:25
 */
public class PythonVideoFactory extends VideoFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }
}
