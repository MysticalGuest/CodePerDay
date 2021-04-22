 package com.leetcode.algorithm;

import java.util.Collections;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * @author MysteryGuest
 * @date 2021/04/20
 * 最大连续1的个数
 * 
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * 返回仅包含 1 的最长（连续）子数组的长度。
 */
public class Solution4 {
    
    // 根据提示，用滑动窗口法
    public int longestOnes(int[] nums, int k) {
        if(k<0){//判断传入数是否为负数
            throw new IllegalArgumentException();//抛出不合理参数异常
        }
        if (k==0) {
            return findMaxConsecutiveOnes2(nums);
        }
//        // 1的个数
//        int ones=0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i]==1) {
//                ones++;
//            }
//        }
//        // 要替换的0的个数比数组中0的个数多，返回数组长度
//        if (nums.length-ones<k) {
//            return nums.length;
//        }
        // 将滑动窗口值设为: 最大连续1的个数 + k，这是滑动窗口最小的长度
        int slidewin = findMaxConsecutiveOnes2(nums)+k;
        // p是滑动窗口起始地址，q是滑动窗口结束地址
        int p=0, q=slidewin-1, zeros=0;
        while (q<nums.length) {
            zeros=zerosInSlideWin(nums, p, q+1);
            // k比滑动窗口中0的个数要多，那么扩充滑动窗口
            if (k>=zeros) {
                q++;
                slidewin++;
            }
            else { // 滑动窗口后移
                p++;
                q++;
            }
        }
        // 由于上面=号的原因，这里要-1
        return slidewin-1;
        
        /*
         * 超出时间限制
         * */
    }
    
    /* 官方解题思路
     * 前言
     * 对于数组 A 的区间 [left,right] 而言，只要它包含不超过 k 个 0，我们就可以根据它构造出一段满足要求，
     * 并且长度为 right−left+1 的区间。
     * 因此，我们可以将该问题进行如下的转化，即：
     * 对于任意的右端点 right，希望找到最小的左端点 left，使得 [left,right] 包含不超过 k 个 0。
     * 只要我们枚举所有可能的右端点，将得到的区间的长度取最大值，即可得到答案。
     * 要想快速判断一个区间内 0 的个数，我们可以考虑将数组 A 中的 0 变成 1，1 变成 0。
     * 此时，我们对数组 A 求出前缀和，记为数组 P，那么[left,right] 中包含不超过 k 个 1（注意这里就不是 0 了），
     * 当且仅当二者的前缀和之差：
     * P[right] - P[left - 1]
     * P[right]−P[left−1]
     * 小于等于 kk。这样一来，我们就可以容易地解决这个问题了。
     */
    
    
    // 计算滑动窗口中0的个数
    public static int zerosInSlideWin(int []nums, int start, int end) {
        int zeros=0;
        for (int i = start; i < end; i++) {
            if (nums[i]==0) {
                zeros++;
            }
        }
        return zeros;
    }
    
    // longestOnes方法需要调用的函数
    public static int findMaxConsecutiveOnes2(int[] nums) {
        int maxCount = 0, count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        maxCount = Math.max(maxCount, count);
        return maxCount;
    }
    
    /**
     * 在找这道题的过程中发现类似的题
     * 给定一个二进制数组， 计算其中最大连续 1 的个数。
     * 
     * 用例：
     * 输入：[1,1,0,1,1,1]
     * 输出：3
     * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
     * */
    public int findMaxConsecutiveOnes(int[] nums) {
        // Set 集合存和取的顺序不一致，不允许重复元素
        HashSet<Integer> set = new HashSet<>();
        int max=0;
        for (int i = 0; i < nums.length; i++) {
            // nums[i]==0表示一段1计数结束
            if (nums[i]==0) {
                if (max!=0) {
                    // 重复的元素set会自动将其去掉
                    set.add(max);
                }
                max=0;
            }
            else {
                max++;
            }
            
            /*
             * 考虑这个用例：[0,0,1,1,(1),(1),1,1,1,(1),1,1,0,0,0,1,1,1,1]
             * 上面max加完，1加到4，由于结束时没有0，这个4不会add进set
             * i==nums.length-1表示数组结束
             */
            if (i==nums.length-1&&max!=0) {
                set.add(max);
            }
        }
        if (set.isEmpty()) {
            /*
             * NoSuchElementException extends RuntimeException
             * 没有控制语句导致的迭代器的越界
             * */
//            throw new NoSuchElementException();
            return max;
        }
        /* 
         * Collections.max(set)会抛出java.util.NoSuchElementException异常
         * max源码取最大值时用到了Iterator迭代器
         */
        return Collections.max(set);
        
        /*
         * 执行用时：5 ms, 在所有 Java 提交中击败了6.90%的用户
         * 内存消耗：39.5 MB, 在所有 Java 提交中击败了99.44%的用户
         * */
    }
    
    public int findMaxConsecutiveOnes1(int[] nums) {
        int maxCount = 0, count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        maxCount = Math.max(maxCount, count);
        return maxCount;
        
        /*
         * 时间复杂度：O(n)，其中 n 是数组的长度。需要遍历数组一次。
         * 空间复杂度：O(1)。
         * */
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        /*
         * 用例1：
         * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
         * 输出：6
         * 解释： [1,1,1,0,0,(1),1,1,1,1,(1)]
         * 括号里的数字从 0 翻转到 1，最长的子数组长度为 6。
         * */
        int [] A1= {1,1,1,0,0,0,1,1,1,1,0};
        Solution4 s4 = new Solution4();
        System.out.println("I: "+s4.findMaxConsecutiveOnes(A1));
        System.out.println("III: "+s4.longestOnes(A1, 2));
        
        /*
         * 用例2：
         * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
         * 输出：10
         * 解释：
         * [0,0,1,1,(1),(1),1,1,1,(1),1,1,0,0,0,1,1,1,1]
         * 括号里的数字从 0 翻转到 1，最长的子数组长度为 10。
         * */
        int[] A2 = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        System.out.println("I: "+s4.findMaxConsecutiveOnes(A2));
        System.out.println("III: "+s4.longestOnes(A2, 3));
        
        /* leetcode用例遇到以下错误：
         * java.util.NoSuchElementException
         *   at line 1497, java.base/java.util.HashMap$HashIterator.nextNode
         *   at line 1518, java.base/java.util.HashMap$KeyIterator.next
         *   at line 674, java.base/java.util.Collections.max
         *   at line 28, Solution.findMaxConsecutiveOnes
         *   at line 54, __DriverSolution__.__helper__
         *   at line 84, __Driver__.main
         */
        int[] A3 = {0,0};
        System.out.println("I: "+s4.findMaxConsecutiveOnes(A3));
        int[] A4 = {0,0,1};
        System.out.println("I: "+s4.findMaxConsecutiveOnes(A4));
        int[] A5 = {};
        System.out.println("I: "+s4.findMaxConsecutiveOnes(A5));
        

    }

}
