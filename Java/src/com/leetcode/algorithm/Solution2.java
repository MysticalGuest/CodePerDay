package com.leetcode.algorithm;

import java.util.Scanner;

import com.sun.org.apache.regexp.internal.recompile;

/// 罗马数字转整数

/*
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。
 * 但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
 * 同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 * */

public class Solution2 {

	// 七种基本字符的转换
	public int singleRomanToInt(char c) {
		switch (c) {
			case 'I': return 1;
			case 'V': return 5;
			case 'X': return 10;
			case 'L': return 50;
			case 'C': return 100;
			case 'D': return 500;
			case 'M': return 1000;
			default: return 0;
		}
	}
	
	// 递归的解法
	public int romanToInt(String s) {
		if (s.length()==0) {
			return 0;
		}
		int a=singleRomanToInt(s.charAt(0));
//		System.err.println(a);
		if (s.length()==1) {
			return a;
		}
		int b=singleRomanToInt(s.charAt(1));
//		System.err.println(b);
		if (a>=b) { // 不是特殊情况，直接转换之后相加即可
			return a+romanToInt(s.substring(1));
		}
		else { // 特殊情况
			return b-a+romanToInt(s.substring(2));
		}
    }
	
	// 不用递归
	public int romanToInt1(String s) {
		if (s.length()==0) {
			return 0;
		}
		int sum=0;
		int a=singleRomanToInt(s.charAt(0)), b;
		if (s.length()==1) {
			return a;
		}
		boolean flag=false; // 下面循环可能会漏加最后一位
		for (int i = 0; i < s.length()-1; i++) {
			a=singleRomanToInt(s.charAt(i));
			b=singleRomanToInt(s.charAt(i+1));
			if (a>=b) { // 不是特殊情况，直接转换之后相加即可
				sum+=a;
				if (i+1==s.length()-1) {
					flag=!flag;
				}
				
			}
			else { // 特殊情况
				sum+=b-a; i++;
			}
			
		}
		if (flag) {
			sum+=singleRomanToInt(s.charAt(s.length()-1));
		}
		return sum;
    }
	
	public int romanToInt2(String s) {
        int sum = 0;
        int preNum = singleRomanToInt(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = singleRomanToInt(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num; // 记忆功能，挺好
        }
        sum += preNum;
        return sum;
    }

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		Solution2 s2 = new Solution2();
		
		System.out.println(s2.romanToInt(input));
		System.out.println(s2.romanToInt1(input));
	}
}
