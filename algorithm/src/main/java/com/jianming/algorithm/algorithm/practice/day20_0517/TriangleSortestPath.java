package com.jianming.algorithm.algorithm.practice.day20_0517;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 力扣试题    https://leetcode-cn.com/problems/triangle/
 *
 *给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *  
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 * @author jianming
 * @create 2020-05-17-20:15
 */
public class TriangleSortestPath {

    /**
     * jianming
     */
    @Test
    public void test() {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(new ArrayList<>(Arrays.asList(2)));
        triangle.add(new ArrayList<>(Arrays.asList(3,4)));
        triangle.add(new ArrayList<>(Arrays.asList(6,5,7)));
        triangle.add(new ArrayList<>(Arrays.asList(4,1,8,3)));
        System.out.println(minimumTotal(triangle));
    }


    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        if(len ==  1) {
            return triangle.get(0).get(0);
        }
        // 记录每种走法的路径长度
        Integer[] sortestPaths = new Integer[len];
        // 保存第一步
        int first = triangle.get(0).get(0);
        for(int i = 0; i < len; i++) {
            sortestPaths[i] = first;
        }
        List<Integer> nodes;
        List<Integer> preodes;
        Integer remove;
        // 遍历每一个节点
        for(int i = 1; i < len; i++) {
            preodes = triangle.get(i - 1);
            nodes = triangle.get(i);
            for(int j = 0; j <= i; j++) {
                // 求当前节点连接过来的最小值
                if(0 == j || i == j) {
                    // 边界的两个点，只能连边界的点
                    if(0 == j) {
                        remove = nodes.remove(j);
                        nodes.add(j,preodes.get(j) + remove);
                    }else {
                        remove = nodes.remove(j);
                        nodes.add(preodes.get(j - 1) + remove);
                    }

                }else {
                    // 可连接当前下标及当前下标-1的上一位节点
                    int compare = Integer.compare(preodes.get(j - 1), preodes.get(j));
                    remove = nodes.remove(j);
                    if(compare >= 0) {
                        // j 小
                        nodes.add(j,preodes.get(j) + remove);
                    }else {
                        // j - 1 小
                        nodes.add(j,preodes.get(j - 1) + remove);
                    }
                }
            }
        }
        return triangle.get(len - 1).stream().min((x, y) -> x > y ? 1 : -1).get();

    }

    /**
     * 题解方案1
     *  自顶向下, 记忆化搜索 【AC】
     */
    int row;
    Integer[][] memo;
    public int minimumTotal1(List<List<Integer>> triangle) {
        row = triangle.size();
        memo = new Integer[row][row];
        return helper(0,0, triangle);
    }
    private int helper(int level, int c, List<List<Integer>> triangle){
        // System.out.println("helper: level="+ level+ " c=" + c);
        if (memo[level][c]!=null) {
            return memo[level][c];
        }
        if (level==row-1){
            return memo[level][c] = triangle.get(level).get(c);
        }
        int left = helper(level+1, c, triangle);
        int right = helper(level+1, c+1, triangle);
        return memo[level][c] = Math.min(left, right) + triangle.get(level).get(c);
    }


    /**
     * 题解方案2
     *  自底向上, DP 【AC】
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[] minlen = new int[row+1];
        for (int level = row-1;level>=0;level--){
            //第i行有i+1个数字
            for (int i = 0;i<=level;i++){
                minlen[i] = Math.min(minlen[i], minlen[i+1]) + triangle.get(level).get(i);
            }
        }
        return minlen[0];
    }


}
