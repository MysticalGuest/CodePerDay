 package com.nowcoder.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MysteryGuest
 * @date 2021/04/20
 * 两数之和
 * 
 * 给出一个整数数组，请在数组中找出两个加起来等于目标值的数，
 * 你给出的函数twoSum 需要返回这两个数字的下标（index1，index2），需要满足 index1 小于index2.。注意：下标是从1开始的
 * 假设给出的数组中只存在唯一解
 * 例如：
 * 给出的数组为 {20, 70, 110, 150},目标值为90
 * 输出 index1=1, index2=2
 */
public class Solution9 {

    /**
     * 
     * @param numbers int整型一维数组 
     * @param target int整型 
     * @return int整型一维数组
     */
    public int[] twoSum (int[] numbers, int target) {
        int []index = {0, 0};
        int min=2;
        if (numbers.length > min) {
            for (int i = 0; i < numbers.length; i++) {
                index[0] = i+1;
                for (int j = i+1; j < numbers.length; j++) {
                    if (numbers[i]+numbers[j]==target) {
                        index[1]=j+1;
                        return index;
                    }
                }
            }
        }
        return index;
        /*
         * 时间复杂度：O(n^2)
         * 空间复杂度: O(1)
         * */
    }
    
    /**
     * 
     * @param numbers int整型一维数组 
     * @param target int整型 
     * @return int整型一维数组
     * 哈希法
     */
    public int[] twoSumHash (int[] numbers, int target) {
        // key: 是对应numbers中的值；value: 是对应值的下标
        int l=numbers.length;
        Map<Integer, Integer> map = new HashMap<>(l);
        for (int cur = 0, tmp; cur < l; cur++){
            tmp = numbers[cur];
            if (map.containsKey(target - tmp)){
                return new int[] {map.get(target - tmp) + 1, cur + 1};
            }
            map.put(tmp, cur);
        }
        throw new RuntimeException("results not exits");
    }
    
    public static void main(String[] args) {
        /*
         * 用例：
         * 输入：[3,2,4],6
         * 输出：[2,3]
         * */
        int [] arr = {3, 2, 4};
        Solution9 s9 = new Solution9();
        int []result = s9.twoSum(arr, 6); 
        for (int i : result) {
            System.out.print(i+" ");
        }
        System.out.println();
        result = s9.twoSumHash(arr, 6); 
        for (int i : result) {
            System.out.print(i+" ");
        }
    }
}
