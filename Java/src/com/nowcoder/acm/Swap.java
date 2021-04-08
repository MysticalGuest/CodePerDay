package com.nowcoder.acm;

public class Swap {

	public static void main(String[] args) {
		int a=1;
		int b=99;
		
		// 借助临时变量
		int c=a;
		a=b;
		b=c;
		
		System.out.println("a: "+a+" | b: "+b);
		
		// "异或" 数学思维
		a = a^b;
		System.out.println("After a = a^b; a: "+a+" | b: "+b);
		b = a^b;
		System.out.println("After b = a^b; a: "+a+" | b: "+b);
		a = a^b;
		System.out.println("a: "+a+" | b: "+b);
		
		// 有点像这个
		a = b-a;
		System.out.println("After a = b-a; a: "+a+" | b: "+b);
		b = b-a;
		System.out.println("After b = b-a; a: "+a+" | b: "+b);
		a = a+b;
		System.out.println("After a = a+b; a: "+a+" | b: "+b);
		
		/*
		 * 真实情况，借助临时变量的方案要比使用“异或”快得多。
		 * 汇编对比
		 * 借助临时变量的方案只涉及两次的内存读写
		 * 而在“异或”方案中除了要执行3次“异或”运算以外，还需进行6次读和3次写
		 * */
		
	}
}
