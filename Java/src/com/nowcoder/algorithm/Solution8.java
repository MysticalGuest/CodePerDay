package com.nowcoder.algorithm;

import java.util.Stack;

/*
 * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
 * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
 * 
 * 题目分类：栈、字符串
 * 提示这题用栈实现，好像有关括号匹配的都用栈？
 * */

class Solution8 {

	/**
     * 
     * @param s string字符串 
     * @return bool布尔型
     */
    public boolean isValid (String s) {
    	if (s.length()<=1||s.length() % 2 != 0) {
			return false;
		}
        Stack<Character> st = new Stack<>();
        char c=s.charAt(0);
//        st.add(c); add()是父类Vector的方法
        st.push(c);
        // st.empty()：如果栈空返回true
        for (int i = 1; !st.empty(); i++) {
        	if (i==s.length()) { // "(("的情况
				return false;
			}
        	c=s.charAt(i);
			if (c=='('||c=='{'||c=='[') {
				st.push(c);
			}
			else if (c==')') {
				if (st.peek()=='(') {
					st.pop(); // 出栈
					continue;
				}
				else {
					return false;
				}
			}
			else if (c=='}') {
				if (st.peek()=='{') {
					st.pop(); // 出栈
					continue;
				}
				else {
					return false;
				}
			}
			else if (c==']') {
				if (st.peek()=='[') {
					st.pop(); // 出栈
					continue;
				}
				else {
					return false;
				}
			}
		}
        return true;
    }
    
    /*
     * A clever way
     * 字符替换
     * '()','[]','{}'替换为''，最后字符串为空就true
     * */
    public boolean isValid1 (String s) {
        boolean flag = true;
        while(flag){
            int len = s.length();
            s=s.replace("()","");
            s=s.replace("[]","");
            s=s.replace("{}","");
            if(len == s.length()){
                flag=false;
            }
        }
        return s.length() == 0;
    }
    
    // 左括号不进栈
    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        //遍历所有的元素
        for (char c : chars) {
            //如果是左括号，就把他们对应的右括号压栈
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                //否则就只能是右括号。
                //1，如果栈为空，说明括号无法匹配。
                //2，如果栈不为空，栈顶元素就要出栈，和这个右括号比较。
                //如果栈顶元素不等于这个右括号，说明无法匹配，
                //直接返回false。
                return false;
            }
        }
        //最后如果栈为空，说明完全匹配，是有效的括号。
        //否则不完全匹配，就不是有效的括号
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
    	Solution8 s8 = new Solution8();
		System.out.println(s8.isValid(""));
		System.out.println(s8.isValid("(("));
		System.out.println(s8.isValid("[]{}"));
		System.out.println(s8.isValid("[{}]"));
		System.out.println(s8.isValid("[{]}{}"));
		System.out.println(s8.isValid("[])"));
	}
}
