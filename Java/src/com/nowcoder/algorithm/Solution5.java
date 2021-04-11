package com.nowcoder.algorithm;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Stack;

/*
 * 以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
 * （字符串长度不大于100000，保证字符串仅由'0'~'9'这10种字符组成）
 * */

public class Solution5 {
	
	/**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算两个数之和
     * @param s string字符串 表示第一个整数
     * @param t string字符串 表示第二个整数
     * @return string字符串
     */
    public String solve (String s, String t) {
//    	for (int i = 0; i < s.length(); i++) {
//			System.err.println(s.charAt(i));
//		}
//    	for (int i = 0; i < t.length(); i++) {
//			System.err.println(t.charAt(i));
//		}
    	// 字符串要动态扩展的话建议使用StringBuilder或者是StringBuffer，前者非线程安全，后者线程安全
    	StringBuilder sb = new StringBuilder("");
    	int s_index=s.length()-1, t_index=t.length()-1;
    	int sum=0, carry=1, addend=0, summand=0;
    	boolean flag=false; // 下次是否需要进位
    	while(s_index>=0&&t_index>=0) {
    		addend = Integer.parseInt(s.charAt(s_index)+"");
    		summand = Integer.parseInt(t.charAt(t_index)+"");
    		if(flag) {
    			sum = addend+summand+carry;
    		}
    		else {
    			sum = addend+summand;
			}
    		if(sum>=10) {
    			sum=sum-10;
    			flag=true; // 下次需要进位
    		}
    		else {
				flag=false;
			}
//    		sb.append(sum);
    		sb.insert(0, sum);
    		s_index--;
    		t_index--;
    	}
    	while (s_index>=0) { // 第一个数还有剩余位
			addend = Integer.parseInt(s.charAt(s_index)+"");
			if (flag) {
				sum=addend+carry;
			}
			else {
				sum=addend;
			}
			if (sum>=10) {
				sum=sum-10;
				flag=true; // 下次需要进位
			}
			else {
				flag=false;
			}
//			sb.append(sum);
			sb.insert(0, sum);
			s_index--;
		}
    	
    	while (t_index>=0) { // 第二个数还有剩余位
			addend = Integer.parseInt(t.charAt(t_index)+"");
			if (flag) {
				sum=addend+carry;
			}
			else {
				sum=addend;
			}
			if (sum>=10) {
				sum=sum-10;
				flag=true; // 下次需要进位
			}
			else {
				flag=false;
			}
//			sb.append(sum);
			sb.insert(0, sum);
			t_index--;
		}
    	if(flag) { // 还要进一位
//    		sb.append(carry); // append方法向后添加
    		sb.insert(0, carry);
    		flag=false;
    	}
        return sb.toString();
    }
    
    // 其他方法
    public String solve1 (String s, String t) {
        BigInteger bigInteger1 = new BigInteger(s);
        BigInteger bigInteger2 = new BigInteger(t);
 
        return bigInteger1.add(bigInteger2).toString();
    }
    
    public String solve2(String s, String t) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = s.length() - 1, j = t.length() - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int x = i < 0 ? 0 : s.charAt(i--) - '0';
            int y = j < 0 ? 0 : t.charAt(j--) - '0';
            int sum = x + y + carry;
            stringBuilder.append(sum % 10); //添加到字符串尾部
            carry = sum / 10;
        }
        return stringBuilder.reverse().toString(); //对字符串反转
    }
    
    public String solve3(String s, String t) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        int i = s.length() - 1, j = t.length() - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            carry += i >= 0 ? s.charAt(i--) - '0' : 0;
            carry += j >= 0 ? t.charAt(j--) - '0' : 0;
            stack.push(carry % 10);
            carry = carry / 10;
        }
        while (!stack.isEmpty())
            stringBuilder.append(stack.pop());
        return stringBuilder.toString();
    }
    
    public static void main(String[] args) {
		/*
		 * 输入："1","99"
		 * 输出："100"
		 * 说明：1+99=100 
		 * 
		 * java中int的取值范围为-2147483648到+-2147483648
		 * 字符串长度不到100000，为什么叫大数相加
		 * */
    	String a="1", b="99";
    	Solution5 s5 = new Solution5();
    	System.out.println(a+"+"+b+"="+s5.solve(a, b));
    	
    	
    	String str1="56865812312331212312312312";
        String str2="13212123123123131";
        System.out.println(str1+"+"+str2+"="+s5.solve(str1, str2));
        System.out.println(str1+"+"+str2+"="+s5.solve1(str1, str2));
        System.out.println(str1+"+"+str2+"="+s5.solve2(str1, str2));
        System.out.println(str1+"+"+str2+"="+s5.solve3(str1, str2));
    	
	}

}
