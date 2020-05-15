package com.jianming.designpattern.principle.dependenceinversion;

/**
 * 依赖倒置原则
 *  针对接口（抽象）编程
 * @author jianming
 * @create 2020-04-14-6:40
 */
public class Test {

    public static void main(String[] args) {
        // 业务：可随意添加在学习的课程
        Jianming jianming = new Jianming();
        jianming.setiCourse(new JavaCourse());
        jianming.study();
        // 添加为在学习Python课程
        jianming.setiCourse(new PythonCourse());
        jianming.study();
        /**
         * 以此类推
         *  若需要学习其他的课程，不需要修改 Jianming类或其他课程类的代码，只需实现ICourse类来进行扩展修改，
         *      然后将实现类传入Jianming中即可
         *
         *   高层次的模块不应依赖低层次的模块
         *      高层的Jianming类是不需要依赖于低层的ICourse类，这样修改需求时只需实现新增ICourse接口，而不需要修改Jianming类
         *   抽象搭建出的框架会比细节搭建出的框架稳定得多
         */
    }

}
