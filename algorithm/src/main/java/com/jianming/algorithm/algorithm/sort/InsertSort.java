package com.jianming.algorithm.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序-简单插入排序
 *  思路：通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，
 *  找到相应位置并插入。可以理解为玩扑克牌时的理牌；
 *  延伸：直接插入：将当前判断元素找到在已排序的元素中的位置，并将该位置之后的元素全部往后移一位，再将元素插入
 *          二分插入：由于当前判断的元素前所有元素是已排好序的，便可利用二分查找法找到应插入的位置，随后重复直接插入的操作
 *
 *  时间复杂度：平均 O(n²)  最坏 O(n²) 最好O(n) 稳定
 * @author jianming
 * @create 2020-04-14-21:14
 */
public class InsertSort {

    public static void main(String[] args) {
        // 待排序数组
        int[] array = new int[]{3,6,1,9,0,5,2,4,8,7};

        // 从第二个元素开始，每一个数字都试图跟它的前一个比较并交换，并重复；
        // 直到前一个数字不存在或比它小或相等时停下来
        for(int i = 1,len = array.length; i < len; i ++) {
            int key = array[i];
            // 当前判断元素的前一个元素，从这个开始比较
            int j = i - 1;
            // 当key(当前判断元素) 比前一位元素小时，则交换位置
            // 注：判断元素前的所有元素是已排好序的,且第一次判断时，j+1元素就是key
            while (j >= 0 && array[j] > key) {
                // 将较大的元素替换到后一位
                array[j + 1] = array[j];
                // 下标前移
                j--;
            }
            // 最后一次满足的情况下 j进行了一次--，故得加1
            array[j + 1] = key;
        }
        System.out.println("InsertSort 简单插入排序效果 -- " + Arrays.toString(array));

    }

}
