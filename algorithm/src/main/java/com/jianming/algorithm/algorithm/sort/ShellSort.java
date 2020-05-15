package com.jianming.algorithm.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序-希尔排序
 *  思路：希尔排序是插入排序的一种高效率的实现，也叫缩小增量排序。
 *   先将整个待排记录序列分割成为若干子序列分别进行直接插入排序，
 *   待整个序列中的记录基本有序时再对全体记录进行一次直接插入排序。
 *
 *  问题：增量的序列取法？
 * 　　关于取法，没有统一标准，但最后一步必须是1；
 *    因为不同的取法涉及时间复杂度不一样，具体了解可以参考《数据结构与算法分析》；
 *    一般以length/2为算法。（再此以gap=gap*3+1为公式）
 *
 *  由于增量的引入，使元素分为多个组来进行冒泡排序，则可降低冒泡排序移动元素的次数，从而降低时间复杂度
 *
 *  时间复杂度：平均 O(n^1.3)  最坏 O(n²) 最好O(n) 不稳定
 * @author jianming
 * @create 2020-04-14-21:58
 */
public class ShellSort {

    public static void main(String[] args) {
        // 待排序数组
        int[] array = new int[]{3,6,1,9,0,5,2,4,8,7};

        int len = array.length;
        int h = 1;
        // 取增量 一定是3的倍数+1
        while (h < len / 3) {
            h = 3 * h + 1;
        }
        // 排序，按增量分为 增量数个 组
        while (h >= 1) {
            for(int i = h; i < len; i ++) {
                // 每一次的里循环都是使 当前下标 前的所有该组元素进行一次冒泡排序（从后往前的冒泡）
                for(int j = i; j >= h && (array[j] < array[j - h]); j -= h) {
                    int temp = array[j];
                    array[j] = array[j - h];
                    array[j-h]= temp;
                }
            }
            // 减小增量，直到为1
            h /= 3;
        }

        System.out.println("ShellSort 希尔排序效果 -- " + Arrays.toString(array));

    }



}
