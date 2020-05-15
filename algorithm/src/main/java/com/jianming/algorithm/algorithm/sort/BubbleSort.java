package com.jianming.algorithm.algorithm.sort;

import java.util.Arrays;

/**
 * 交换排序-冒泡排序
 *  思路：外层循环从1到n-1，内循环从当前外层的元素的下一个位置开始，
 *  依次和外层的元素比较，出现逆序就交换，通过与相邻元素的比较和交换来把小的数交换到最前面。
 *
 *  时间复杂度：平均 O(n²)  最坏 O(n²) 最好O(n) 稳定
 * @author jianming
 * @create 2020-04-14-21:01
 */
public class BubbleSort {


    public static void main(String[] args) {
        // 待排序数组
        int[] array = new int[]{3,6,1,9,0,5,2,4,8,7};

        for(int i = 0,len = array.length - 1; i < len; i ++) {
            // 从第一个找到最后一个未排序元素的最大值，在每次交换的时候交换到已排序的前一个下标
            for(int j = 0; j < len - i; j ++) {
                if(array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        System.out.println("BubbleSort 冒泡排序效果 -- " + Arrays.toString(array));


    }



}
