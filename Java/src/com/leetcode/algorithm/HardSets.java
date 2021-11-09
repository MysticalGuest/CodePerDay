 package com.leetcode.algorithm;

import org.junit.Test;

/**
 * @author MysteryGuest
 * @date 2021/11/07
 * 困难题
 */
public class HardSets {
    
    @Test
    public void testIsMatch() {
        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("aab", "c*a*b"));
        System.out.println(isMatch("aab", "a*b"));
        System.out.println(isMatch("mississippi", "mis*is*p*."));
        // 错误用例：s="aaa",p="a.a",输出：false，预期输出：true
//        System.out.println(isMatch("aaa", "a.a"));
        // 错误用例：s="aaa",p="a*a",输出：false，预期输出：true
        System.out.println(isMatch("aaa", "a*a"));
        System.out.println(isMatch("aaaaaa", "a*aa"));
        // 未通过用例：s="aaa",p="ab*ac*a",输出：false，预期输出：true
        System.out.println(isMatch("aaa", "ab*ac*a"));
//         未通过用例：s="aaa",p="ab*ac*a",输出：false，预期输出：true
        System.out.println(isMatch("aaa", "ab*ac*a"));
        // 未通过用例：s="aaa",p="ab*ac*a",输出：false，预期输出：true
        System.out.println(isMatch("aaa", "ab*a*c*a"));
        // 未通过用例：s="aaca",p="ab*ac*a",输出：false，预期输出：true
        System.out.println(isMatch("aaca", "ab*a*c*a"));
        // 未通过用例：s="a",p="ab*",输出：false，预期输出：true
//        System.out.println(isMatch("a", "ab*"));
        // 未通过用例：s="a",p=".*a*a",输出：false，预期输出：true
//        System.out.println(isMatch("a", "a*a"));
//        System.out.println(isMatch("a", ".*a*a"));
     // 未通过用例：s="bbbba",p=".*a*a",输出：false，预期输出：true
//        System.out.println(isMatch("bbbba", ".*a*a"));
//        System.out.println(isMatch("ab", ".*"));
//        System.out.println(isMatch("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*c"));
//        System.out.println(isMatch("AAABCAAB", "A*B.A*B"));
    }

    /**
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * 
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     * */
    public boolean isMatchOut(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        System.out.println("s:  "+s+"   p:  "+p);
        if ((sLen==0 && pLen==0) || (sLen==0 && pLen==2 && p.charAt(1)=='*')) {
            return true;
        }
        if (pLen==2&&p.equals(".*")) {
            return true;
        }
        boolean res = false;
//        if (p.equals(".*")) {
        if (pLen>1 && p.substring(0, 2).equals(".*")) {
            
            for (int i = 0; i < sLen; i++) {
                res = res || isMatchOut(s.substring(i), p.substring(2));
            }
//            return true;
        }
        if (res) {
            return res;
        }
//        for (int i = 0,j = 0; i < sLen && j < pLen; i++, j++) {
//            
//        }
        if (sLen==1 && pLen==1 && ( p.equals(s) || p.equals(".") )) {
            return true;
        }
//         eg. s="aaaaa", p="a*"
        else if (sLen>=1 && pLen>1 && p.charAt(1)=='*') {
            if (s.charAt(0)==p.charAt(0)) {
                System.out.println(45);
                int i=0;
                boolean b = false;
                while (i<sLen && s.charAt(i)==s.charAt(0)) {
                    b = b || isMatchOut(s.substring(i), p.substring(2)) || isMatchOut(s.substring(i), p.charAt(0)+p.substring(2));
                    i++;
                }
                b = b || isMatchOut(s.substring(i), p.substring(2));
//                int j=2;
//                if (pLen>2 && p.charAt(0)==p.charAt(2)) {
//                    System.out.println("2");
//                    while (j<pLen && p.charAt(j)==p.charAt(0) && j<i) {
//                        j++;
//                    }
//                }
//                System.out.println(i+"  "+pLen);
                // s="aaaaa", p="a*"
                if (i==sLen && pLen==2) {
                    return true;
                }
                // s="aaaaabcd", p="a*bcd"
                else {
//                    return isMatch(s.substring(i), p.substring(j));
                    return b||res;
                }
            } else {
                // s="aaaab", p="c*a*b"
                System.out.println(34+"    "+s.substring(0)+"    "+p.substring(2));
                return isMatchOut(s.substring(0), p.substring(2));
            }
        }
        else if (sLen>=1 && pLen>1 && (p.charAt(0)==s.charAt(0) || p.charAt(0)=='.')) {
            return isMatchOut(s.substring(1), p.substring(1));
        }
        else {
            return false;
        }
    }
    
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        // 这里+1是为了在前面加一个空格的情况
        boolean dp[][] = new boolean[sLen+1][pLen+1];
        
        // 初始化
        dp[0][0] = true; // 空格和空格相等
        for (int i = 1; i < sLen+1; i++) {
            if (s.charAt(i-1)==' ') {
                dp[i][0] = true;
            }
            else {
                dp[i][0] = false;
            }
        }
        for (int i = 1; i < pLen+1; i++) {
            if (p.charAt(i-1)=='*') {
                dp[0][i] = dp[0][i-2];
            }
            else {
                dp[0][i] = false;
            }
        }
        for (int i = 1; i < sLen+1; i++) {
            for (int j = 1; j < pLen+1; j++) {
                if (s.charAt(i-1) == p.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else if (p.charAt(j-1) == '*') {
                    if (dp[i][j-2]) {
                        dp[i][j] = dp[i][j-2];
                    }
                    else {
                        // 判断s中当前元素是否与'*'之前的元素相同或者不相等，但'*'之前的元素是'.'
                        boolean temp = s.charAt(i-1)==p.charAt(j-2) || p.charAt(j-2)=='.'? true:false;
                        dp[i][j] = dp[i-1][j] && temp;
                    }
                    
                }
                else if (p.charAt(j-1) == '.') {
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j]=false;
                }
            }
        }
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[i].length; j++) {
//                System.out.print(dp[i][j] +"   ");
//            }
//            System.out.println();
//        }
        
        return dp[sLen][pLen];
    }
}
