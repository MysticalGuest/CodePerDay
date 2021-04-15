package com.nowcoder.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/// 数组中出现次数超过一半的数字

/*
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
 * 如果不存在则输出0。
 * */
public class Solution1 {
	
	// 最简单暴力统计
	public int MoreThanHalfNum_Solution(int [] array) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        
        int time;
        for (int i = 0; i < array.length; i++) {
			if (!hm.containsKey(array[i])) {
				hm.put(array[i], 1);
			}
			else {
				time = hm.get(array[i]);
				hm.replace(array[i], time+1);
			}
		}
        
        for (int i = 0; i < array.length; i++) {
			if (hm.get(array[i])>array.length/2) {
				return array[i];
			}
		}
        return 0;
        /*
         * 运行时间：10ms超过89.85%用Java提交的代码
         * 占用内存：9828KB超过2.47%用Java提交的代码
         * */
    }

	public static void main(String[] args) {
		int [] arr = {1,2,3,2,2,2,5,4,2};
		int [] arr1 = {1,2,3,2,4,2,5,2,3};
		
		Solution1 s1 = new Solution1();
		System.out.println(s1.MoreThanHalfNum_Solution(arr));
		System.out.println(s1.MoreThanHalfNum_Solution(arr1));
	}

}
