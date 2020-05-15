package com.jianming.algorithm.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序-简单选择排序
 *  思路：冒泡排序是通过相邻的比较和交换，每次找个最小值。
 *  选择排序是：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 *  然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 *  以此类推，直到所有元素均排序完毕。
 *
 *  时间复杂度：平均 O(n²)  最坏 O(n²) 最好O(n²) 不稳定
 * @author jianming
 * @create 2020-04-14-17:57
 */
public class SelectSort {

    public static void main(String[] args) {
        // 待排序数组
        int[] array = new int[]{3,6,1,9,0,5,2,4,8,7};
        // 记录最小元素下标
        int min;
        int temp;
        // 遍历所有元素
        for(int i = 0,len = array.length; i < len; i ++) {
            min = i;
            // 选择出未排序元素的最小元素
            for(int j = i; j < len; j ++) {
                if(array[j] < array[min]) {
                    min = j;
                }
            }
            // 将此次选择的最小元素交换到已排序元素的后一个下标
            temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }

        System.out.println("SelectSort 选择排序效果 -- " + Arrays.toString(array));

    }

}
