package com.jianming.designpattern.principle.openclose;

/**
 * 增加java课程打折需求功能
 *      继承原java课程类来对其进行功能扩展，则不会影响到原其他业务功能，且可以对需求进行扩展
 * @author jianming
 * @create 2020-04-14-5:39
 */
public class JavaDiscountCourse extends JavaCourse {
    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    /**
     * 对打折活动业务的增加所进行的修改
     * @return
     */
    @Override
    public Double getPrice() {
        return super.getPrice() * 0.8;
    }

    /**
     * 获取原价方法
     * @return
     */
    public Double getOriginPrice() {
        return super.getPrice();
    }

}
