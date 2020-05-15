package com.jianming.designpattern.principle.demeter;

import java.util.ArrayList;

/**
 * @author jianming
 * @create 2020-04-14-7:44
 */
public class TeamLeader {

    /**
     * 查询课程数量
     * @return
     */
    ArrayList<Course> checkNumberOfCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        // 模拟查询数据库
        for(int i = 0,len = 20; i < len; i ++) {
            courses.add(new Course());
        }
        return courses;
    }

}
