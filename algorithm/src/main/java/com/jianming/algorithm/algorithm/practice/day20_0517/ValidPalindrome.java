package com.jianming.algorithm.algorithm.practice.day20_0517;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 力扣试题    https://leetcode-cn.com/problems/valid-palindrome/
 *
 *  验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 *
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 *
 *
 * @author jianming
 * @create 2020-05-17-21:36
 */
public class ValidPalindrome {

    /**
     * jianming
     */
    @Test
    public void test() {
        String str = "0P";
        isPalindrome(str);
    }

    public boolean isPalindrome(String s) {
        // 97 - 122  48 - 57
        String str = s.toLowerCase();
        char[] chars = str.toCharArray();
        // 先排除字母和数字以外的符号
        List<Character> list = new ArrayList<>();
        for(char ch : chars) {
            if((97 <= ch && 122 >= ch) || (48 <= ch && 57 >= ch)) {
                list.add(ch);
            }
        }
        for(int i = 0,len = list.size(),j = len - 1; i < len / 2; i++, j--) {
            if(!list.get(i).equals(list.get(j))){
                return false;
            }
        }
        return true;
    }

    /**
     * 题解方案
     * @param s
     * @return
     */
    public boolean isPalindrome1(String s) {
        String str = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for(char c : str.toCharArray()){
            if(Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        return sb.toString().equals(sb.reverse().toString());
    }


}
