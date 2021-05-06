 package com.nowcoder.algorithm;

 /**
 * @author MysteryGuest
 * @date 2021/04/29
 */
public class LongestPalindrome {
    
    public int longest(String s) {
        int length = s.length();
        int pair=0; // 用来记录字符串中有多少对相同元素
        int j=length-1; // 第一次从末尾开始往前找
        for (int i = 0; i < length; i++) {
            for (; j > 0&&i<j; j--) {
                if (s.charAt(i)==s.charAt(j)) {
                    pair++;
                    break;
                }
            }
        }
        if (length%2==0) { // 字符串长度为偶数
            return length-pair*2;
        }
        else {
            return length-pair*2-1;
        }
    }
    
    public void longestPalindrome(String s[]) {
        for (int k = 0; k < s.length; k++) {
            int length=s[k].length();
            int pair=0; // 用来记录字符串中有多少对相同元素
            int j=length-1; // 第一次从末尾开始往前找
            for (int i = 0; i < length; i++) {
                for (; j > 0&&i<j; j--) {
                    if (s[k].charAt(i)==s[k].charAt(j)) {
                        pair++;
                        break;
                    }
                }
            }
            if (length%2==0) { // 字符串长度为偶数
//                return length-pair*2;
                System.out.println(length-pair*2);
            }
            else {
//                return length-pair*2-1;
                System.out.println(length-pair*2-1);
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        LongestPalindrome l = new LongestPalindrome();
        
        System.out.println(l.longest("abcda"));
        System.out.println(l.longest("google"));
    }

}
