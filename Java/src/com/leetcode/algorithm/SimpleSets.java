 package com.leetcode.algorithm;

import java.util.function.Predicate;

import org.junit.Test;

/**
 * @author MysteryGuest
 * @date 2021/05/11
 * 将几道简答题的解答放入一个文件
 */
public class SimpleSets {
    
    /*
     * 判断回文数
     * */
    @Test
    public void testPalindrome() {
        /*
         * -2^31 <= x <= 2^31 - 1
         * 这就是int的取值范围
         * */
        System.out.println(isPalindrome(123));
        System.out.println(isPalindrome(12321));
        System.out.println(isPalindrome(-123));
        System.out.println(isPalindrome(0));
        System.out.println(isPalindrome(2147483647));
        System.out.println(isPalindrome(-2147483648));
        
        Predicate<Integer> pre = new Predicate<Integer>() {
            @Override
            /*
             * 官方方法：反转一半数字，分两种情况，一种奇数，另一种偶数
             * */
            public boolean test(Integer x) {
                // 特殊情况：
                // 如上所述，当 x < 0 时，x 不是回文数。
                // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
                // 则其第一位数字也应该是 0
                // 只有 0 满足这一属性
                if (x < 0 || (x % 10 == 0 && x != 0)) {
                    return false;
                }

                int revertedNumber = 0;
                while (x > revertedNumber) {
                    revertedNumber = revertedNumber * 10 + x % 10;
                    x /= 10;
                }

                // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
                // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
                // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
                return x == revertedNumber || x == revertedNumber / 10;
            }
        };
        
        System.err.println(pre.test(1111));
        
    }
    
    /*
     * 第一种思路，将数字对应的回文数求出来
     * */
    public boolean isPalindrome(int x) {
        if (x<0) {
            return false;
        }
        int num=x, n, palindrome=0;
        while (num>0) {
            n = num%10;
            palindrome = n + palindrome*10;
            num = num/10;
        }
        if (palindrome==x) {
            return true;
        }
        return false;
    }

}
