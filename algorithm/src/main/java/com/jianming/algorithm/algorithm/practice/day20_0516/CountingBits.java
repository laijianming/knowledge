package com.jianming.algorithm.algorithm.practice.day20_0516;

import java.util.Arrays;

/**
 * 力扣试题     https://leetcode-cn.com/problems/counting-bits/
 *
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 *
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * @author jianming
 * @create 2020-05-16-21:16
 */
public class CountingBits {

    public static void main(String[] args) {
        int[] ints = countBits(16);
        System.out.println(Arrays.toString(ints));
    }

    static int[] record;

    public static int[] countBits(int num) {
        int[] result = new int[num + 1];
        int temp;
        int flag = 1;
        result[1] = 1;
        for(int i = 2; i <= num; i++) {
            // 幂次位，更替下标，以计算这个幂次位的数
            // 如 1111 -> 10000 ； 把幂次位更换到 16；接下来计算的则是 5位的二进制了
            if(i == flag*2) {
                flag = i;
                result[i] = 1;
            }else {
                // 减去上一个2的幂次位的下标，就是之前计算过的，再+1即可
                // 如 1010 - 1000 = 10 ； 10 - 8 = 2 ； 1010 = 1000 + 10 = 1 + 1 = 2个1
                temp = i - flag;
                result[i] = result[temp] + 1;
            }
        }

        /**
         * 解法二： 奇偶分开运算
         *  偶数的1的个数 等于 偶数/2 的1的个数  如： 1100 >>> 2 = 110  都是2个1
         *  奇数的1的个数 等于 上一个偶数的1的个数+1 如：1101 是 1100 个数+1
         */

        return result;
    }


}
