package com.jianming.designpattern.principle.demeter;

import java.util.List;

/**
 * 迪米特法则
 *  只和朋友类发生接触，即非朋友类不应出现在类中
 *      朋友类：出现在成员变量、方法的输入、输出参数中的类称为成员朋友类
 * @author jianming
 * @create 2020-04-14-7:47
 */
public class Test {

    /**
     * 需求：Boss命令TeamLeader去查询课程数量
     *  Boss与TeamLeader有交流，但是和课程无交流，则Boss中不应出现课程类
     * @param args
     */
    public static void main(String[] args) {
        Boss boss = new Boss();
        TeamLeader teamLeader = new TeamLeader();
        List list = boss.commandCheckNumber(teamLeader);
        System.out.println("课程数量为：" + list.size());
    }

}
