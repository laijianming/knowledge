package com.jianming.algorithm.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序-二路归并排序
 *  思路：将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。
 *  若将两个有序表合并成一个有序表，称为2-路归并。它使用了递归分治的思想；
 *  相当于：左半边用尽，则取右半边元素；右半边用尽，则取左半边元素；
 *  右半边的当前元素小于左半边的当前元素，则取右半边元素；
 *  右半边的当前元素大于左半边的当前元素，则取左半边的元素。
 *
 *
 * @author jianming
 * @create 2020-04-17-3:45
 */
public class MergeSort {

    private static double mid;

    public static void main(String[] args) {
        // 待排序数组
        int[] array = new int[]{3,6,1,9,0,5,2,4,8,7};

        mergeSort(array,0,array.length - 1);
        System.out.println("MergeSort 归并排序效果 -- " + Arrays.toString(array));

    }

    /**
     * 归并排序
     *  将数组平均分为两段来进行排序后再进行合并排好序的有序数组
     *  先递归分割数组，再进行排序
     * @param array
     * @param left 分割数组的左边下标位置
     * @param right 分割数组的右边下标位置
     */
    private static void mergeSort(int[] array, int left, int right) {
        // 这里的right是上一次分割的mid，若mid <= left 则表示不可分割了
        if(left < right) {
            int mid = (left + right) / 2;
            mergeSort(array,left,mid);
            mergeSort(array,mid + 1,right);
            // 合并有序数组
            merge(array,left,mid,right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int [] tmpArray = new int[array.length];
        int rightStart = mid + 1;
        int tmp = left;
        int third = left;
        //比较两个小数组相应下标位置的数组大小，小的先放进新数组
        while(left<= mid &&rightStart<=right){
            if(array[left]<=array[rightStart]){
                tmpArray[third++] = array [left++];
            }else{
                tmpArray[third++] = array[rightStart++];
            }
        }
        //如果左边还有数据需要拷贝，把左边数组剩下的拷贝到新数组
        while(left<= mid){
            tmpArray[third++] = array[left++];
        }
        //如果右边还有数据......
        while(rightStart<=right){
            tmpArray[third++] = array[rightStart++];
        }
        while(tmp<=right){
            array[tmp] = tmpArray[tmp++];
        }
    }


}
