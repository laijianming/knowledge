package com.jianming.algorithm.algorithm.practice.day20_0518;

import org.junit.Test;

import java.time.Instant;

/**
 * 力扣试题    https://leetcode-cn.com/problems/integer-break/
 *  整数拆分
 *
 *给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 *
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 *
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 * @author jianming
 * @create 2020-05-18-22:31
 */
public class IntegerBreak {


    /**
     * jianming
     */
    @Test
    public void test() {
        for(int i = 2; i <= 10; i++) {
            Instant start = Instant.now();
            System.out.println(i + " -> " + integerBreak(i));
            Instant end = Instant.now();
            System.out.println(end.toEpochMilli() - start.toEpochMilli());
            System.out.println();
        }

    }

    int max = 0;
    int N = 0;

    public int integerBreak(int n) {
        N = n;
        for(int i = 1; i < n; i++) {
            def(i,i,i);
        }
        return max;
    }

    /**
     *
     * @param sum 上个方法的总和
     * @param n 上个方法的 i
     * @param mul 上个方法的总积
     */
    public void def(int sum,int n,int mul) {
        int temp;
        for(int i = n; i < N; i++) {
            if((temp = sum + i) == N) {
                if((temp = mul*i) > max) {
                    max = temp;
                }
                break;
            }else if(temp < N) {
                def(temp, i, mul * i);
            }else {
                // 已经超过大小了
                break;
            }
        }
    }


    /**
     * 题解一 直接算
     *
     * 将数字 n 尽可能以因子 3 等分时，乘积最大。
     *
     * 拆分规则：
     * 最优： 3 。把数字 n 可能拆为多个因子 3 ，余数可能为 0,1,2 三种情况。
     * 次优： 2 。若余数为 2 ；则保留，不再拆为 1+1 。
     * 最差： 1 。若余数为 1 ；则应把一份 3 + 1 替换为 2 + 2，因为 2×2>3×1。
     *
     * 算法流程：
     * 当 n≤3 时，按照规则应不拆分，但由于题目要求必须拆分，因此必须拆出一个因子 11 ，即返回 n - 1 。
     * 当 n>3 时，求 n 除以 3 的 整数部分 a 和 余数部分 b （即 n = 3a + b ），并分为以下三种情况：
     *
     * 当 b = 0，直接返回 3^a
     * 当 b = 1 时，要将一个 1 + 3 转换为 2+2，因此返回 3^{a-1} * 4
     * 当 b = 2 时，返回 3^a * 2
     *
     * @param n
     * @return
     */
    public int integerBreak1(int n) {
        if(n <= 3) {return n - 1;}
        int a = n / 3, b = n % 3;
        if(b == 0) {return (int)Math.pow(3, a);}
        if(b == 1) {return (int)Math.pow(3, a - 1) * 4;}
        return (int)Math.pow(3, a) * 2;
    }


}
