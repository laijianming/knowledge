package com.jianming.designpattern.pattern.creational.simplefactory;

/**
 * 简单工厂
 *  根据入参选择创建对应对象并返回
 * @author jianming
 * @create 2020-04-14-9:30
 */
public class VideoFactory {

    /**
     * 使用反射方式创建对象可在一定程度上满足开闭原则
     * @param c
     * @return
     */
    public Video getVideo(Class c) {
        Video video = null;
        try {
            video = (Video) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return video;
    }


}
