package com.jianming.designpattern.principle.openclose;

/**
 * @author jianming
 * @create 2020-04-14-5:31
 */
public class Test {


    public static void main(String[] args) {
        ICourse javaCourse = new JavaCourse(1,"java课程",168d);
        // 原本的业务需求
        System.out.println("课程ID："  + javaCourse.getId() + "\n课程名称：" + javaCourse.getName() + "\n课程价格：" + javaCourse.getPrice());
        // 增加对java系列课程的打折活动需求（不对其他类型课程进行打折）
        /**
         * 遵循开闭原则的实现方式
         *      不应修改ICourse接口或修改JavaCourse类，以免引起其他的错误和增加修改量
         *      应对JavaCourse类进行继承方式扩展
         */
        javaCourse = new JavaDiscountCourse(1,"java课程",168d);
        JavaDiscountCourse javaDiscountCourse = (JavaDiscountCourse)javaCourse;
        System.out.println("课程ID："  + javaDiscountCourse.getId() + "\n课程名称：" + javaDiscountCourse.getName() + "\n课程打折价格：" + javaDiscountCourse.getPrice() + "\n课程原价格：" + javaDiscountCourse.getOriginPrice());

    }


}
