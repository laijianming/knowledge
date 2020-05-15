package com.jianming.algorithm.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序-堆排序
 *  思路：堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。
 *  1、构建堆：从最后一个父节点遍历构建堆，将大元素往上升。
 *  2、"拆堆"排序：将堆顶元素与堆最后一个元素交换位置，并从新构建"元素-1"的该堆（重新构建：只需将堆顶元素沉降到堆底即可）
 *  3、重复"2"操作，直到堆"拆完"
 *
 *  完全二叉树
 *      当根节点下标为0时，一个节点的左子节点下标为2i+1，右子节点下标为2i+2
 *
 *  时间复杂度：平均 O(nlogn)  最坏 O(nlogn) 最好O(nlogn) 不稳定
 * @author jianming
 * @create 2020-04-15-11:14
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = new int[]{3,6,1,9,0,5,2,4,8,7};
        heapSort(array);
        System.out.println("HeapSort 堆排序效果 -- " + Arrays.toString(array));
    }

    private static void heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        //建立最大堆
        buildMaxHeap(array);
        System.out.println("建立最大堆的数组： "  + Arrays.toString(array));
        for (int i = array.length - 1; i >= 1; i--) {
            //最大的在0位置，那么开始沉降，这样每交换一次最大的值就丢到最后了
            exchangeElements(array, 0, i);
            //继续获取0位置最大值
            // 由于将堆顶元素与堆最后一个元素交换了位置，堆的其他结构不变
            // 所以再次构建堆的时候只需将堆顶元素沉降下去即可
            maxHeap(array, i, 0);
        }
    }

    /**
     * 构建大堆
     * @param array
     */
    private static void buildMaxHeap(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        // 只需遍历一半的节点即可（一个节点的左右子节点为2i+1、2i+2）
        int half = (array.length-1) / 2;
        // i = half 从堆的最后一个父节点开始构建堆，遍历每个父节点，可逐渐将大的节点往上移，从而构建大堆
        for (int i = half; i >= 0; i--) {
            maxHeap(array, array.length, i);
        }
    }

    /**
     * 沉降节点
     * 该方法功能：将当前index节点沉降到比子节点都大的位置
     *  判断index节点和其子节点，找出最大的节点与父节点交换位置（沉降），并递归沉降该节点，直到比子节点都大的时候停止
     *  注：为什么要递归构建其找到最大节点原下标的堆？
     *      因为在拆堆的时候是堆顶的最大元素与堆的最后一个元素交换位置，交换位置后需要重新构建一次堆，由于该堆是已经构建过一次的，
     *      故可不用从最后一个父节点重新构造，直接将当前堆顶元素沉降下去即可
     * @param array
     * @param heapSize 堆的大小
     * @param index 构建的节点
     */
    private static void maxHeap(int[] array, int heapSize, int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;
        // 找出最大的节点与父节点交换位置
        if (left < heapSize && array[left] > array[index]) {
            largest = left;
        }
        if (right < heapSize && array[right] > array[largest]) {
            largest = right;
        }
        // 比子节点都大时停止
        if (index != largest) {
            exchangeElements(array, index, largest);
            // 递归沉降该节点，直到比子节点都大时停止
            maxHeap(array, heapSize, largest);
        }
    }

    private static void exchangeElements(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}
