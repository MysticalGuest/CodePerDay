 package com.leetcode.algorithm;

 import java.util.ArrayList;
import java.util.Arrays;
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
    public List<Integer> largestDivisibleSubset(int[] nums) {
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
//        int[] nums1 = {1,2,4,8};
//        System.out.println(l.largestDivisibleSubset(nums1));
//        
//        int[] nums2 = {3,2,9,27};
//        System.out.println(l.largestDivisibleSubset(nums2));
        
        /*
         * 提交后不通过的用例：
         * [5,9,18,54,108,540,90,180,360,720]
         * 输出：[5,90,180,360,720]
         * 预期结果：[9,18,90,180,360,720]
         * */
        int []nums3 = {5,9,18,54,108,540,90,180,360,720};
        System.out.println(l.largestDivisibleSubset(nums3));
        
//        List<Integer> list = new ArrayList<>();
//        for (int i = 1; i < 1001; i++) {
//            list.add(i*2);
//        }
//        System.out.println(list);
    }

}
