package com.nowcoder.algorithm;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * 给定一个数组，找出其中最小的K个数。
 * 例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 * 如果K>数组的长度，那么返回一个空的数组
 * */
public class Solution3 {
	
	// method one: Sort first, then find several smallest number
	public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
		ArrayList<Integer> al = new ArrayList<>();
		if(input.length<k || k<0 || input==null) { // 不包括k取负值，数组为空的情况(input==null ||input.length<k|| k<0)
			return al;
		}
		Arrays.sort(input);
		for (int i = 0; i < k; i++) {
			al.add(input[i]);
		}
		return al;
    }
	
	public static void main(String[] args) {
//		int arr[] = {4};
		int arr[] = {4,5,1,6,2,7,3,8}; // 等同于 int[] arr= new []{4,5,1,6,2,7,3,8};
		
		System.out.println(Arrays.toString(arr));//遍历并输出整个数组
		
		ArrayList<Integer> arrList = new ArrayList<>();
		
		Solution3 s3 = new Solution3();
		
		arrList = s3.GetLeastNumbers_Solution(arr, 4);
//		for (Integer integer : arr) {
//			System.out.print(integer+" ");
//		}
		System.out.println(Arrays.toString(arr));//遍历并输出整个数组
		
		System.out.println("\n"+arrList.toString());
	}
}
