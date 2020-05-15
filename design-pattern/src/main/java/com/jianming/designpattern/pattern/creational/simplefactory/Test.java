package com.jianming.designpattern.pattern.creational.simplefactory;

/**
 * 简单工厂（非GOF23种设计模式）
 *  使用工厂类来创建对象，则可不必了解创建对象时的具体逻辑
 * @author jianming
 * @create 2020-04-14-9:32
 */
public class Test {

    public static void main(String[] args) {
        VideoFactory videoFactory = new VideoFactory();
        Video video = videoFactory.getVideo(JavaVideo.class);
        video.produce();
    }

}
