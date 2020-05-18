package com.jianming.algorithm.algorithm.practice.day20_0518;

import org.junit.Test;

/**
 * 力扣试题    https://leetcode-cn.com/problems/add-binary/
 *
 * 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 *
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *  
 *
 * 提示：
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 *
 * @author jianming
 * @create 2020-05-18-21:40
 */
public class AddBinary {

    /**
     * jianming
     */
    @Test
    public void test() {
        String a = "11";
        String b = "1";
        System.out.println(addBinary(a, b));

    }


    public String addBinary(String a, String b) {
        int alen = a.length();
        int blen = b.length();
        if(alen > blen) {
            String temp = a;
            a = b;
            b = temp;

            int ltemp = alen;
            alen = blen;
            blen = ltemp;
        }
        StringBuilder stringBuilder = new StringBuilder(blen+1);
        int j = blen - 1;
        int flag = 0;
        int sum;
        // 48 = 0  49 = 1
        for(int i = alen - 1; i >= 0; i--,j--) {
            char c1 = a.charAt(i);
            char c2 = b.charAt(j);
            sum = (c1 + c2) + flag;
            if(sum > 98) {
                // 三个1
                stringBuilder.insert(0,1);
                flag = 1;
            }else if(sum == 98) {
                // 两个1
                stringBuilder.insert(0,0);
                flag = 1;
            } else if(sum == 97){
                // 一个1
                stringBuilder.insert(0,1);
                flag = 0;
            }else {
                // 没有1
                stringBuilder.insert(0,0);
                flag = 0;
            }
        }
        for(;j >= 0; j--) {
            char c = b.charAt(j);
            sum = c + flag;
            if(sum == 50) {
                // 两个1
                stringBuilder.insert(0,0);
                flag = 1;
            }else if(sum == 49){
                // 一个1
                stringBuilder.insert(0,1);
                flag = 0;
            }else {
                // 没有1
                stringBuilder.insert(0,0);
                flag = 0;
            }
        }
        if(flag == 1) {
            stringBuilder.insert(0,1);
        }

        return stringBuilder.toString();
    }


    /**
     * 题解一
     * @param a
     * @param b
     * @return
     */
    public String addBinary1(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int ca = 0;
        for(int i = a.length() - 1, j = b.length() - 1;i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum % 2);
            ca = sum / 2;
        }
        ans.append(ca == 1 ? ca : "");
        return ans.reverse().toString();
    }


}
