 package com.nowcoder.algorithm;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author MysteryGuest
 * @date 2021/04/29
 * 最长无重复字符串
 */
public class LongestNonRepeating {
    
    /**
     * 
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int maxLengthOut (int[] arr) {
        if (arr.length<=0) {
            return arr.length;
        }
        Arrays.sort(arr);
        int num=1; // 记录不重复的数字
        int standard=arr[0]; // 以第一个数为标准
        for (int i = 1; i < arr.length; i++) {
            while (arr[i]==standard && i < arr.length-1) {
                i++;
            }
            if (arr[i]!=standard) {
                num++;
                standard=arr[i];
            }
        }
        return num;
    }
    
    public int maxLengthError (int[] arr) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i:arr) {
            if (!hashSet.contains(i)) {
                hashSet.add(i);
            }
        }
        return hashSet.size();
    }
    /*
     * 以上两种算法都错了，为什么？
     * 示例 3:
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * 又是题目理解错了
     * */

    // hashset作为滑动窗口
    public int maxLength (int[] arr) {
        HashSet<Integer> hashSet = new HashSet<>();
        int max=0;
        for (int i=0,j=0; i<arr.length&&j<arr.length; ) {
            if (!hashSet.contains(arr[j])) {
                hashSet.add(arr[j]);
                j++;
                max=Math.max(max, hashSet.size());
            }
            else {
                hashSet.remove(arr[i]);
                i++;
            }
        }
        return max;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        LongestNonRepeating l = new LongestNonRepeating();
        
        int []array = {2,3,4,5};
        System.out.println(l.maxLength(array));
        
        int []array1 = {2,2,3,4,3};
        System.out.println(l.maxLength(array1));
        
        int []array2 = {};
        System.out.println(l.maxLength(array2));
        
        int []array3 = {0,0,0,0,0,0,1};
        System.out.println(l.maxLength(array3));
        
        int []array4 = {0};
        System.out.println(l.maxLength(array4));
        

    }

}
