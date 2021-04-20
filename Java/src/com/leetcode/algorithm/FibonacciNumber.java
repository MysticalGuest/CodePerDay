package com.leetcode.algorithm;

/// Fibonacci Number

/*
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * */

public class FibonacciNumber {
	
	// 递归
	public int fib(int n) {
		if (n==0||n==1) {
			return n;
		}
		return fib(n-1)+fib(n-2);
    }
	
	/*
	 * 循环的时间复杂度和空间复杂度都要优于递归，但递归的优越性在于条理清晰，
	 * 可读性强，比较适宜于问题本身是递归性质的、用循环难于解决的问题。
	 * 在二者都不难的情况下，一般都是优先选用循环来解决问题的。
	 * */
	public int fibWithLoop(int n) {
		if (n==0||n==1) {
			return n;
		}
		int previous=0, next=1; // 分别记录前一个数和后一个数，即分别记录f(n-2)和f(n-1)
		int sum=0;
		for (int i = 2; i <= n; i++) {
			sum = previous + next;
			previous = next;
			next = sum;
		}
		return sum;
		
		/*
		 * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
		 * 内存消耗：35.1 MB, 在所有 Java 提交中击败了75.36%的用户
		 * */
    }
	
	public int fibWithViolence(int n) {
        int []nums = {0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765,10946,
        		17711,28657,46368,75025,121393,196418,317811,514229,832040};
        return nums[n];
    }

	public static void main(String[] args) {
		FibonacciNumber fb = new FibonacciNumber();
		System.out.println(fb.fib(4));
		System.out.println(fb.fib(9));
		System.out.println(fb.fibWithLoop(4));
		System.out.println(fb.fibWithLoop(9));
	}

}
