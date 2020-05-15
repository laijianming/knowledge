package com.jianming.algorithm.algorithm.sort;

import java.util.Arrays;

/**
 * 交换排序-快速排序
 *  思路：通过一趟排序将待排记录分隔成独立的两部分，
 *  其中一部分记录的关键字均比另一部分的关键字小，
 *  则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 *
 *  以数组第一个元素为基数，找到该基数的位置并替换，然后将数组从上一个基数的位置分割为两个数组；
 *  再重复上一步，直到分割数组长度为1时停止
 *
 *
 *  时间复杂度：平均 O(nlogn)  最坏 O(n²) 最好O(nlogn) 不稳定
 * @author jianming
 * @create 2020-04-15-20:59
 */
public class QuickSort {

    public static void main(String[] args) {
        // 待排序数组
        int[] array = new int[]{3,6,1,9,0,5,2,4,8,7};
        // 快速排序
        quickSort(array,0,array.length - 1);
        System.out.println("QuickSort 快速排序效果 -- " + Arrays.toString(array));
    }

    /**
     * 一个迭代函数
     *  先将数组的第一个元素作为基数并替换至应在的位置
     *  然后将数组从上一个基数的位置分割并迭代此函数
     * @param array
     * @param low
     * @param high
     */
    private static void quickSort(int[] array, int low, int high) {
        // 此时分割数组长度为1，不可再分割
        if(low < high) {
            // 将数组的第一个元素作为基数并替换至应在的位置
            int mid = getMiddle(array,low,high);
            // 将数组以mid位置分割并迭代本身函数
            quickSort(array,low,mid);
            quickSort(array,mid+1,high);
        }
    }

    /**
     * 找到数组在low-high区间中low位置上的元素应在的位置并替换
     * @param array
     * @param low
     * @param high
     * @return
     */
    private static int getMiddle(int[] array, int low, int high) {
        int i = array[low];
        while(low < high) {
            // 从 low-high 区间中的右边开始找第一个比基数i元素小的元素，并将小的元素覆盖到low位置
            while (low < high && i < array[high]) {
                high--;
            }
            array[low] = array[high];
            // 从 low-high 区间中的左边开始找第一个比基数i元素大的元素，并将大的元素覆盖到high位置
            while (low < high &&i >= array[low]) {
                low++;
            }
            array[high] = array[low];
        }
        // 当循环跳出时，该位置（low或high）就是基数应在的位置
        // 可以直接覆盖在low位置的原因是：该位置的元素已覆盖在上一个空余位置
        array[low] = i;
        return low;
    }


}
