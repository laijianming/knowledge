package com.jianming.algorithm.algorithm.day20_0318;

import java.util.Scanner;

/**
 * 蓝桥杯试题  http://lx.lanqiao.cn/problem.page?gpid=T596###
 *
 * 问题描述
 * 　　输入一个字符串，将大写字符变成小写、小写变成大写，然后输出
 * 输入格式
 * 　　acbAB
 * 输出格式
 * 　　ACBab
 *
 * @author jianming
 * @create 2020-03-18-23:11
 */
public class WordTrans {


    /**
     *
     * Ascll 码表
     *  48 == '0'   57 == '9'
     *  65 == 'A'   90 == 'Z'
     *  97 == 'a'   122 == 'z'
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String word = s.nextLine();
        char c;
        StringBuilder result = new StringBuilder(word.length());
        for(int i = 0,len = word.length(); i < len; i ++) {
            c = word.charAt(i);

            // A - Z
            if(c >= 65 && c <= 90) {
                result.append((char)(c + 32));
            }else if(c >= 97 && c <= 122) {
                // a - z
                result.append((char)(c - 32));
            }
        }
        System.out.println(result.toString());

    }

}
