package com.leetcode.algorithm;

/// Complement of Base 10 Integer

/*
 * 二进制与十进制间的转换方法
 * 
 * 一、正整数的十进制转换二进制
 *   要点：除2取余，倒叙排列
 * 二、负整数转换为二进制
 *   要点：取反加一
 *   解释：将该负整数对应的正整数先转换成二进制，然后对其“取补”，在再对取补后的结果加1即可
 * 三、小数转换为二进制
 *   要点：乘二取整，正序排列
 * */

public class Solution3 {
	
	// 暴力解法，我现在也不知道有没有库
	public int bitwiseComplement(int N) {
		/*
		 * int十进制转字符串二进制，然后取反，然后转十进制
		 * */
		if (N==0) {
			return 1;
		}
		StringBuilder sb = new StringBuilder("");
		int n=N, remainder=0;
		while (n>0) {
			remainder = n%2;
//			sb.append(remainder); // 向字符串末添加字符
			// 顺便取反
			if (remainder==0) {
				remainder=1;
			} else {
				remainder=0;
			}
			sb.insert(0, remainder); // reverse order
			n=n/2;
		}
//		System.err.println(sb);
		// 转十进制
		int num_base10=0;
		for (int i = sb.length()-1, index=0; i >=0 ; i--, index++) {
			int num=sb.charAt(i)-'0';
			num_base10 += num*Math.pow(2, index);
		}
		return num_base10;
    }
	
	/*
	 * 原理!!!!!!!!!!：原码+反码=(2^n)-1 n为原码二进制位数
	 * 循环条件num<=N 即可求出2^n
	 * */
    public int bitwiseComplement1(int N) {
        if(N==0){
            return 1;
        }
        int num=1;
        /*
         * << 表示左移，不分正负数，低位补0；　
         *   注：以下数据类型默认为byte-8位，左移时不管正负，低位补0
         *   例：
         *     正数r = 20 << 2；
         *       20的二进制补码：0001 0100；
         *       向左移动两位后：0101 0000；
         *       结果：r = 80；
         *     负数r = -20 << 2；
         *       -20的二进制原码：1001 0100；
         *       -20的二进制反码：1110 1011；
         *       -20的二进制补码：1110 1100；
         *       左移两位后的补码：1011 0000；反码：1010 1111；原码：1101 0000；结果：r = -80
         *     
         * >> 表示右移，如果该数为正，则高位补0，若为负数，则高位补1；
         *   注：以下数据类型默认为byte-8位
         *   例：
         *     正数r = 20 >> 2
         *       20的二进制补码：0001 0100
         *       向右移动两位后：0000 0101
         *     结果：r = 5
         *       负数：r = -20 >> 2
         *       -20 的二进制原码 ：1001 0100
         *       -20 的二进制反码 ：1110 1011
         *       -20 的二进制补码 ：1110 1100 
         *       右移两位后的补码：1111 1011 
         *       反码：1111 1010
         *       原码：1000 0101
         *       结果：r = -5
         * 
         * >>> 表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0
         *   例：
         *     正数r = 20 >>> 2的结果与 r = 20 >> 2 相同；
         *     负数r = -20 >>> 2
         *       注：以下数据类型默认为int 32位
         *       -20:源码：10000000 00000000 00000000 00010100
         *       反码：11111111  11111111   11111111   11101011
         *       补码：11111111  11111111   11111111   11101100
         *       右移：00111111  11111111   11111111   11111011
         *       结果：r = 1073741819
         * 
         * 总结：左移乘2，左移n位，相当于乘Math.pow(2, n); 右移除2，右移n位，相当于除以Math.pow(2, n);
         * */
        while(num<=N){
        	/*
        	 * 由于：原码+反码=(2^n)-1
        	 * 这里N为原码，num为(2^n)，则num>原码，左移while条件是这个
        	 * */
//            num<<=1;
        	num = num << 1;
        	// 相当于：num*=2;
        }
        return num-1-N;
    }
    
    /*
     * 根据反码的定义 等于原码取反
     * 原码与对应二进制位数的全1异或就是反码
     * 还是求反码？
     * */
    public int bitwiseComplement2(int N) {
        int num=1;
        while(num<N){
            num = (num<<1) + 1; // 全1!!!!!!!，巧妙
        }
        return num ^ N;
    }
    
    public int bitwiseComplement3(int N) {

        //对于0需要单独讨论，因为log(x) 的定义域不包括0
        if(N == 0) return 1;

        /* java中没有log2(x)函数，默认log以e为底，log10以10为底
         * 所以要用换底公式loga(x) / loga(y) = logy(x)
         * 所以loge(N) / loge(2) = log2(N)
         * 
         * log2(N)=num, 2^num=N
         */
        
        // 这是要求出N这个数的二进制位数，例N=8,log2(N)=3,length=3+1=4; N=7,log2(N)=2,length=2+1=3;
        int length = (int)(Math.log(N) / Math.log(2)) + 1;
        
        // 和bitwiseComplement1(int N)一样，求出(2^n), Math.pow(2,  length)=2^n
        return (int)(Math.pow(2,  length)) - 1 - N;
    }
	
	public static void main(String[] args) {
		/*
		 * 测试用例：
		 * Input: 5
		 * Output: 2
		 * Explanation: 5 is "101" in binary, with complement "010" in binary, which is 2 in base-10.
		 * 
		 * Input: 7
		 * Output: 0
		 * Explanation: 7 is "111" in binary, with complement "000" in binary, which is 0 in base-10.
		 * 
		 * Input: 10
		 * Output: 5
		 * Explanation: 10 is "1010" in binary, with complement "0101" in binary, which is 5 in base-10.
		 * 
		 * */
		
		Solution3 s3 = new Solution3();
		System.out.println(s3.bitwiseComplement(5));
		System.out.println(s3.bitwiseComplement(7));
		System.out.println(s3.bitwiseComplement(10));
		
		System.out.println(s3.bitwiseComplement(0));
		System.out.println("------------");
		System.out.println(s3.bitwiseComplement1(5));
		System.out.println(s3.bitwiseComplement1(7));
		System.out.println(s3.bitwiseComplement1(10));
		System.out.println(s3.bitwiseComplement1(0));
	}

}
