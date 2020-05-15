package com.jianming.designpattern.pattern.creational.factorymethod;

/**
 * 工厂方法
 *  使用工厂类的子类来创建对象
 *  扩展时只需新增工厂类的子类实现即可，可大大降低扩展所带来的风险
 * @author jianming
 * @create 2020-04-14-9:32
 */
public class Test {

    public static void main(String[] args) {
        VideoFactory videoFactory = new JavaVideoFactory();
        Video video = videoFactory.getVideo();
        video.produce();
        videoFactory = new PythonVideoFactory();
        video = videoFactory.getVideo();
        video.produce();
    }

}
