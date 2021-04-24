 package com.leetcode.algorithm;

 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author MysteryGuest
 * @date 2021/04/23
 * 368. 最大整除子集
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，
 * 子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 *   answer[i] % answer[j] == 0 ，或
 *   answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 */
public class LargestDivisibleSubset {
    
    // 每一元素对!!!暴力解法
    /*
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 2 * 109
     * nums 中的所有整数 互不相同
     * */
    public List<Integer> largestDivisibleSubset1(int[] nums) {
        List<List<Integer>> llist = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        for (Integer integer : nums) {
            System.err.print(integer+"  ");
        }
        System.out.println();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
            // l记录list中元素的下标
            for (int j = i+1, l=0; j < nums.length; j++) {
                /*
                 * 用例3通过不了的问题出在这里，最后看到提示，果然这是动态规划问题，这种解法淘汰
                 * */
                if (nums[j] % list.get(l) == 0) {
                    list.add(nums[j]);
                    l++;
                }
            }
            llist.add(list);
            System.out.println(list);
            list = new ArrayList<>();
        }
        int max_index=0, max_size=llist.get(0).size(), temp_size=0;
        for (int i = 1; i < llist.size(); i++) {
            temp_size=llist.get(i).size();
            if (temp_size > max_size) {
                max_index=i;
                max_size=temp_size;
            }
        }
        System.err.println(max_index);
        return llist.get(max_index);
    }
    
    public List<Integer> largestDivisibleSubset2(int[] nums) {
//        List<List<Integer>> llist = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        int size=nums.length;
//        int [][]arr = new int[size+1][size+1];
        
        list.add(nums[size-1]);
        for (int i = size-1, j=0; i > 0; i--) {
//            System.err.println("list.get(j): "+list.get(j)+" nums[i-1]: "+nums[i-1]+" //: "+list.get(j) % nums[i-1]);
            if (list.get(j) % nums[i-1]==0) {
                list.add(nums[i-1]);
                j++;
            }
        }
        Collections.reverse(list);     // 实现list集合逆序排列  
        return list;
    }
    
    /*
     * 看了官方的解题思路写的：
     * 分两个步骤：
     * 1.dp[i]记录的是当前值是多少个排在其前面的值的倍数；
     * 2.然后找出dp[]中最大的，找出这个序列
     * */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums); // 排序
        int []dp = new int[nums.length];
        List<Integer> list = new ArrayList<>();
        
        // 第一步
        for (int i = 1; i < dp.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (nums[i]%nums[j] == 0) {
//                    dp[i] = dp[j]+1;
//                    break;
//                    System.out.println("dp[i]:  "+dp[i]+"  dp[j] + 1:  "+(dp[j] + 1));
                    // !!!!!!!!关键还是这里，看看自己写的代码!!!!这是我看官方的代码
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
//        for (int i = 0; i < dp.length; i++) {
//            System.err.print(dp[i] + "  ");
//        }
//        System.out.println();
        
        // 第二步
        // 求dp[]中最大值的下标
        int max=0;
        for (int i = 1; i < dp.length; i++) {
            if (dp[i]>dp[max]) {
                max=i;
            }
        }
        list.add(nums[max]);
        // 从max下标开始往前找
        int l_index=max-1, l=dp[max]-1; // 寻找的长度下标
        for (int i = l_index, j=0; i >= 0; i--) {
            if(dp[i]==l&&list.get(j)%nums[i]==0) {
                list.add(nums[i]);
                l--;
                j++;
            }
        }
        Collections.reverse(list);
        return list;
        
        /*
         * 执行用时：61 ms, 在所有 Java 提交中击败了5.38%的用户
         * 内存消耗：38.5 MB, 在所有 Java 提交中击败了86.50%的用户
         * */
    }

    public List<Integer> largestDivisibleSubsetOffical(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);

        // 第 1 步：动态规划找出最大子集的个数、最大子集中的最大整数
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int maxSize = 1;
        int maxVal = dp[0];
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                // 题目中说「没有重复元素」很重要
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxVal = nums[i];
            }
        }

        // 第 2 步：倒推获得最大子集
        List<Integer> res = new ArrayList<Integer>();
        if (maxSize == 1) {
            res.add(nums[0]);
            return res;
        }
        
        for (int i = len - 1; i >= 0 && maxSize > 0; i--) {
            if (dp[i] == maxSize && maxVal % nums[i] == 0) {
                res.add(nums[i]);
                maxVal = nums[i];
                maxSize--;
            }
        }
        return res;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        /*
         * 用例1
         * 输入：nums = [1,2,3]
         * 输出：[1,2]
         * 解释：[1,3] 也会被视为正确答案。
         * */
        int[] nums = {1,2,3};
        LargestDivisibleSubset l = new LargestDivisibleSubset();
        System.out.println(l.largestDivisibleSubset(nums));
//        
//        /*
//         * 用例2
//         * 输入：nums = [1,2,4,8]
//         * 输出：[1,2,4,8]
//         * */
        int[] nums1 = {1,2,4,8};
        System.out.println(l.largestDivisibleSubset(nums1));
//        
        int[] nums2 = {3,2,9,27};
        System.out.println(l.largestDivisibleSubset(nums2));
        
        /*
         * 提交后不通过的用例：[5,9,18,54,108,540,90,180,360,720]
         * 输出：[5,90,180,360,720]
         * 预期结果：[9,18,90,180,360,720]
         * */
        int []nums3 = {5,9,18,54,108,540,90,180,360,720};
//        System.out.println(l.largestDivisibleSubset2(nums3));
        System.out.println(l.largestDivisibleSubset(nums3));
        
        /*
         * 提交后不通过的用例：[4,8,10,240]
         * 输出：[10,240]
         * 预期结果：[4,8,240]
         *    [4,8,10,240]
         * dp:[0,1, 0,2]
         * */
        int []nums4 = {4,8,10,240};
        System.out.println(l.largestDivisibleSubset(nums4));
    }

}
