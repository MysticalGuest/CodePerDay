 package com.leetcode.algorithm;

 /**
 * @author MysteryGuest
 * @date 2021/04/24
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 */
public class ReverseInteger {
    
    public int reverse(int x) {
//        long upper = (long) Math.pow(2, 31) - 1;     // 上限
//        long loewr = -(long) Math.pow(2, 31);          // 下限
        if (x==0) {
            return x;
        }
        String s;
        if (x>0) {
            s = String.valueOf(x); // 其中 x 可以是任意一种数字类型
            StringBuilder sb = new StringBuilder(s);
            s = sb.reverse().toString();
            try {
                return Integer.parseInt(s);
            } catch (Exception e) {
                return 0;
            }
            
        }
        else {
            s = String.valueOf(x);
            s=s.substring(1);
            StringBuilder sb = new StringBuilder(s);
            s = sb.reverse().toString();
            try {
                return 0-Integer.parseInt(s);
            } catch (Exception e) {
                return 0;
            }
        }
        
        /*
         * 执行用时：2 ms, 在所有 Java 提交中击败了29.07%的用户
         * 内存消耗：35.6 MB, 在所有 Java 提交中击败了46.15%的用户
         * */

    }
    
    /*
     * 官方思路：我们可以一次构建反转整数的一位数字。在这样做的时候，我们可以预先检查向原整数附加另一位数字是否会导致溢出。
     * 算法：反转整数的方法可以与反转字符串进行类比。
     * 我们想重复“弹出” xx 的最后一位数字，并将它“推入”到 rev 的后面。最后，rev 将与 xx 相反。
     * 要在没有辅助堆栈 / 数组的帮助下 “弹出” 和 “推入” 数字，我们可以使用数学方法。
     * 
     * 但是，这种方法很危险，因为当 temp=rev*10+pop 时会导致溢出。
     * 幸运的是，事先检查这个语句是否会导致溢出很容易。
     * 为了便于解释，我们假设 rev 是正数。
     * 如果 temp=rev*10+pop 导致溢出，那么一定有 rev>=INTMAX/10。
     * 如果 rev > INTMAX/10，那么 temp=rev*10+pop 一定会溢出。
     * 如果 rev== INTMAX/10，那么只要 pop>7，temp=rev*10+pop 就会溢出。
     * 当 rev 为负时可以应用类似的逻辑。
     * */
    public int reverseOfficial(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ReverseInteger ri = new ReverseInteger();
        int a = 123;
        System.out.println(ri.reverse(a));
        
        int b = -123;
        System.out.println(ri.reverse(b));
        
        int c = 0;
        System.out.println(ri.reverse(c));
        
        int d = 120;
        System.out.println(ri.reverse(d));
        
        /*
         * java.lang.NumberFormatException: For input string: "9646324351"
         * at line 68, java.base/java.lang.NumberFormatException.forInputString
         * at line 658, java.base/java.lang.Integer.parseInt
         * at line 776, java.base/java.lang.Integer.parseInt
         * at line 11, Solution.reverse
         * at line 54, __DriverSolution__.__helper__
         * at line 84, __Driver__.main
         * 
         * public class NumberFormatException extends IllegalArgumentException
         * 数字格式异常    非法数据异常
         * 
         * The literal 9646324351 of type int is out of range 
         * 错误原因：1534236469并未超出int界限，但逆转后的9646324351超出了界限，应该返回0，错误在这里
         * */
        int e = 1534236469;
        System.out.println(ri.reverse(e));
        
        /*
         * java.lang.NumberFormatException: For input string: "8463847412"
         * at line 68, java.base/java.lang.NumberFormatException.forInputString
         * at line 658, java.base/java.lang.Integer.parseInt
         * at line 776, java.base/java.lang.Integer.parseInt
         * at line 22, Solution.reverse
         * at line 54, __DriverSolution__.__helper__
         * at line 84, __Driver__.main
         * 
         * 同理：转化后8463847412越界
         * */
        int f = -2147483648;
        System.out.println(ri.reverse(f));

    }

}
