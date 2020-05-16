package com.jianming.algorithm.algorithm.practice.day20_0516;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 力扣试题    https://leetcode-cn.com/problems/intersection-of-two-arrays/
 *
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 *
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 *
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 * @author jianming
 * @create 2020-05-16-20:28
 */
public class ArraysMixed {

    public static void main(String[] args) {
        int[] nums1 = new int[]{4,9,5};
        int[] nums2 = new int[]{9,4,9,8,4};
        int[] intersection = intersection(nums1, nums2);
        System.out.println(Arrays.toString(intersection));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> collect = Arrays.stream(nums2)
                .boxed()
                .collect(Collectors.toSet());
        Stream<Integer> integerStream = Arrays.stream(nums1)
                .boxed()
                .collect(Collectors.toSet())
                .stream()
                .filter((x) -> {
                    if (collect.contains(x.intValue())) {
                        return true;
                    } else {
                        return false;
                    }
                });
        return integerStream.mapToInt(Integer::valueOf).toArray();
    }

}
