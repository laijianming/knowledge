package com.jianming.designpattern.principle.demeter;

import java.util.List;

/**
 * Boss类与TeamLeader有交流，但是和课程无交流，则Boss中不应出现课程类
 * @author jianming
 * @create 2020-04-14-7:46
 */
public class Boss {

    public List commandCheckNumber(TeamLeader teamLeader) {
        return teamLeader.checkNumberOfCourses();
    }

}
