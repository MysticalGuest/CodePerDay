 package com.leetcode.algorithm;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.function.Predicate;

import org.junit.Test;

/**
 * @author MysteryGuest
 * @date 2021/05/11
 * 将9道简答题的解答放入一个文件
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
    
    /*
     * 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     * */
    @Test
    public void testLongestCommonPrefix() {
        /*
         * 输入：strs = ["flower","flow","flight"]
         * 输出："fl"
         * */
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
        
        /*
         * 输入：strs = ["dog","racecar","car"]
         * 输出：""
         * 解释：输入不存在公共前缀。
         * */
        String[] strs1 = {"dog","racecar","car"};
        System.out.println(longestCommonPrefix(strs1));
    }
    
    /*
     * 0 <= strs.length <= 200
     * 0 <= strs[i].length <= 200
     * strs[i] 仅由小写英文字母组成
     * */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length<=0) {
            return "";
        }
        // 以第一个字符串为基准，依次与后面的字符串作比较
        StringBuilder result = new StringBuilder(strs[0]);
        int len = result.length();
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for ( ; j < strs[i].length() && j < len; j++) {
                if (result.charAt(j)!=strs[i].charAt(j)) {
                    break;
                }
            }
            // 这里不是len-1, 左闭右开
            result.delete(j, len);
            len=result.length();
        }
        return result.toString();
        
        /*
         * 执行用时：2 ms, 在所有 Java 提交中击败了30.71%的用户
         * 内存消耗：36.4 MB, 在所有 Java 提交中击败了78.64%的用户
         * */
        
    }
    
    // 官方解答
    public String longestCommonPrefixOffical(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }
    
    /*
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     * */
    @Test
    public void testMergeTwoSortedLists() {
        /*
         * 输入：l1 = [1,2,4], l2 = [1,3,4]
         * 输出：[1,1,2,3,4,4]
         * */
        ListNode l1 = new ListNode(1, null);
        ListNode p = l1;
        ListNode node = new ListNode(2, null);
        p.next=node; p=p.next;
        node = new ListNode(4, null);
        p.next = node;
        
        ListNode ln2 = new ListNode(1, null);
        p=ln2;
        node = new ListNode(3, null);
        p.next=node; p=p.next;
        node = new ListNode(4, null);
        p.next=node;
        
        ListNode l = mergeTwoLists(l1, ln2);
        for(p=l; p!=null; p=p.next) {
            System.out.print(p.val+" ");
        }
        
        /*
         * 输入：l1 = [], l2 = []
         * 输出：[]
         * */
        
        /*
         * 输入：l1 = [], l2 = [0]
         * 输出：[0]
         * */
    }
    
    /*
     * 两个链表的节点数目范围是 [0, 50]
     * -100 <= Node.val <= 100
     * l1 和 l2 均按 非递减顺序 排列
     * */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null) {
            return l2;
        }
        if (l2==null) {
            return l1;
        }
        // 新的链表
        ListNode newlist = new ListNode();
        ListNode n = new ListNode();
        n=newlist;
        ListNode p = new ListNode();
        p=l1;
        ListNode q = new ListNode();
        q=l2;
        while (p!=null && q!=null) {
            if (p.val<q.val) {
                n.next=p;
                p=p.next;
            }
            else {
                n.next=q;
                q=q.next;
            }
            n=n.next;
        }
        // 单链还有剩余
        if (q!=null) {
            p=q;
        }
        n.next=p;
        return newlist.next;
        
        /*
         * 执行用时：1 ms, 在所有 Java 提交中击败了25.84%的用户
         * 内存消耗：38 MB, 在所有 Java 提交中击败了29.40%的用户
         * */
    }
    
    // 官方：迭代
    public ListNode mergeTwoListsRecursion(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoListsIteration(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsIteration(l1, l2.next);
            return l2;
        }
        /*
         * 时间复杂度：O(n+m), 其中 n 和 m 分别为两个链表的长度
         * 空间复杂度：O(n+m)
         * */
    }
    
    // 官方：迭代，和我一样
    public ListNode mergeTwoListsIteration(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }
    
    @Test
    public void testRemoveElement() {
        /*
         * 输入：nums = [3,2,2,3], val = 3
         * 输出：2, nums = [2,2]
         * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。
         * 例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
         * */
        int nums[]= {3,2,2,3};
        // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
        int len = removeElement(nums, 3);

        // 在函数里修改输入数组对于调用者是可见的。
        // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
        System.out.println(len+", nums = "+Arrays.toString(nums));
        
        /*
         * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
         * 输出：5, nums = [0,1,4,0,3]
         * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
         * 注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
         * */
        int nums1[]= {0,1,2,2,3,0,4,2};
        len = removeElement(nums1, 2);
        System.out.println(len+", nums = "+Arrays.toString(nums1));
    }
    
    public int removeElement(int[] nums, int val) {
        int len=nums.length;
        if (len<=0) {
            return 0;
        }
        int temp;
        int j=len-1;
        for (int i = 0; i < j; i++) {
            if (nums[i]==val) {
                while (nums[j]==val) {
                    j--;
                }
                temp=nums[i];
                nums[i]=nums[j];
                nums[j]=temp;
            }
        }
        if(j==len-1){
            /*
             * 如果是这种情况：nums=[2,2,2,2,2],val=3,j应该等于len
             * */
            j++;
        }
        return j;
    }
    
    @Test
    public void testValidParentheses() {
        /*
         * 输入：s = "()[]{}"
         * 输出：true
         * */
        String str = "(}";
        System.out.println(isValid(str));
        
        /*
         * 出错用例："(("
         * */
        String str1 = "((";
        System.out.println(isValid(str1));
        
        /*
         * 出错用例："))"
         * */
        String str2 = "))";
        System.out.println(isValid(str2));
        
        /*
         * 出错用例："(())))"
         * */
        String str3 = "(())))";
        System.out.println(isValid(str3));
    }
    
    /*
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     *   左括号必须用相同类型的右括号闭合。
     *   左括号必须以正确的顺序闭合。
     * 1 <= s.length <= 104
     * s consists of parentheses only '()[]{}'
     * */
    public boolean isValid(String s) {
        int len = s.length();
        if (len % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char temp, top = 0;
        for (int i = 0; i < len; i++) {
            temp = s.charAt(i);
            // 左括号进栈
            while ((temp=='('||temp=='{'||temp=='[')&&i<len-1) {
//                System.out.println(i+"----");
                stack.push(temp);
                i++;
//                if(i==len) {
//                    break;
//                }
                temp = s.charAt(i);
                
            }
            // 遇到"))"这种情况，不会有元素进栈
            
            if(!stack.empty()) {
                top=stack.peek();
            }
            if (ParenthesisMatching(top, temp)) {
                // 遇到右括号，对应消除栈中的左括号
                stack.pop();
                // 恢复初始化
                top=0;
            }
            else {
                return false;
            }
        }
        return stack.empty();
        /*
         * 执行用时：2 ms, 在所有 Java 提交中击败了79.22%的用户
         * 内存消耗：36.5 MB, 在所有 Java 提交中击败了77.38%的用户
         * */
        
    }
    
    boolean ParenthesisMatching(char leftBracket, char rightBracket) {
        if (leftBracket=='('&&rightBracket==')') {
            return true;
        }
        if (leftBracket=='{'&&rightBracket=='}') {
            return true;
        }
        if (leftBracket=='['&&rightBracket==']') {
            return true;
        }
        return false;
    }
    
    public boolean isValidOffical(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {
        {
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                // 对于这种情况"))"，
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void testSearchInsert() {
        /*
         * 输入: [1,3,5,6], 5
         * 输出: 2
         * */
        int nums[]={1,3,5,6};
        System.out.println(searchInsert(nums, 5));
    }
    
    /*
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
     * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 你可以假设数组中无重复元素。
     * */
    public int searchInsert(int[] nums, int target) {
        int len=nums.length;
        int i;
        for (i = 0; i < len; i++) {
            if (nums[i]>=target) {
                break;
            }
        }
        return i;
        /*
         * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
         * 内存消耗：38.1 MB, 在所有 Java 提交中击败了41.73%的用户
         * */
        //官方利用二分法提高查找效率
//        int n = nums.length;
//        int left = 0, right = n - 1, ans = n;
//        while (left <= right) {
//            int mid = ((right - left) >> 1) + left;
//            if (target <= nums[mid]) {
//                ans = mid;
//                right = mid - 1;
//            } else {
//                left = mid + 1;
//            }
//        }
//        return ans;
    }
    
    @Test
    public void testStr() {
        String s1="hello", s2="ll";
        System.out.println(strStr(s1, s2));
        s1="aaaaa";
        s2="bba";
        System.out.println(strStr(s1, s2));
        s1="";
        s2="";
        System.out.println(strStr(s1, s2));
        // 未通过用例
        s1="mississippi";
        s2="issip";
        System.out.println(strStr(s1, s2));
        s1="mississippi";
        s2="issi";
        System.out.println(strStr(s1, s2));
    }
    
    /*
     * 字符串匹配
     * */
    public int strStr(String haystack, String needle) {
        if (haystack.equals("") && needle.equals("")) {
            return 0;
        }
        int hlen=haystack.length(), nlen=needle.length(), index=0, start=-1;
        for (int i = 0; i < hlen; i++) {
            start=i;
            while (i<hlen && index<nlen && haystack.charAt(i)==needle.charAt(index)) {
                i++;
                index++;
            }
            if (index==nlen) {
                // 说明找到了
                break;
            }
            else {
                // 如果失败了，从才开始的下一个开始
                i=i-index;
                index=0;
                start=-1;
            }
        }
        return start;
        /*
         * 执行用时：2548 ms, 在所有 Java 提交中击败了5.13%的用户
         * 内存消耗：36.6 MB, 在所有 Java 提交中击败了99.00%的用户
         * */
    }
    
    @Test
    public void testRemoveDuplicates() {
        /*
         * 输入：nums = [1,1,2]
         * 输出：2, nums = [1,2]
         * */
        int nums1[]= {1,1,2};
        System.out.println(removeDuplicates(nums1));
        /*
         * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
         * 输出：5, nums = [0,1,2,3,4]
         * */
        int nums2[] = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums2));
        /*
         * 未通过用例
         * 输入：nums = [1,2,2,3,3,3,3,4,5,6,6]
         * 输出：[1,2,3,4,4,4,4,4,4,6]
         * 预期结果：[1,2,3,4,5,6]
         * */
        int nums3[] = {1,2,2,3,3,3,3,4,5,6,6};
        System.out.println(removeDuplicates(nums3));
        /*
         * 未通过用例
         * 输入：[1,1,2,3,3,3,3,8,9]
         * 输出：[1,2,3,8]
         * 预期结果：[1,2,3,8,9]
         * */
        int nums4[] = {1,1,2,3,3,3,3,8,9};
        System.out.println(removeDuplicates(nums4));
        /*
         * 未通过用例
         * 输入：[1]
         * 输出：java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1
         * 预期结果：[1]
         * */
        int nums5[] = {1};
        System.out.println(removeDuplicates(nums5));
        /*
         * 未通过用例
         * 输入：[1,1,1]
         * 输出：[1,1]
         * 预期结果：[1]
         * */
        int nums6[] = {1,1,1};
        System.out.println(removeDuplicates(nums6));
    }
    
    /*删除有序数组中的重复项*/
    public int removeDuplicates(int[] nums) {
        int len=nums.length;
        int target=1, next;
        for (int i = 0; i < len-1; i++) {
            next=i+1;
            if (nums[next]>nums[i]) {
                nums[target]=nums[next];
                target++;
            }
        }
        return target;
        /*
         * 执行用时：1 ms, 在所有 Java 提交中击败了83.63%的用户
         * 内存消耗：40.2 MB, 在所有 Java 提交中击败了61.98%的用户
         * */
    }
    
    /*
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * */
    @Test
    public void testMaxSubArray() {
        /*
         * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
         * 输出：6
         * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
         * */
        int nums[] = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
        
        /*
         * 输入：nums = [-1]
         * 输出：-1
         * */
        int nums1[] = {-1};
        System.out.println(maxSubArray(nums1));
        /*
         * 未通过用例：
         * 输入：[-2,1]
         * 输出：-1
         * 预期：1
         * */
        int nums2[]= {-2,1};
        System.out.println(maxSubArray(nums2));
        /*
         * 未通过用例：
         * 输入：[-2,1,-3]
         * 输出：-1
         * 预期：1
         * */
        int nums3[]= {-2,1,-3};
        System.out.println(maxSubArray(nums3));
    }
    
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        // max是最大值
        int max=nums[0], max1, max2;
        // reserve表示当前值与当前值的前一个值构成的连续子数组的最大和的和
        int reserve = nums[0];
        for (int i = 1; i < len; i++) {
            max1 = Math.max(reserve+nums[i], max);
            max2 = Math.max(nums[i], max);
            max = Math.max(max1, max2);
            reserve = Math.max(reserve+nums[i], nums[i]);
        }
        return Math.max(reserve, max);
        /*
         * 执行用时：2 ms, 在所有 Java 提交中击败了12.27%的用户
         * 内存消耗：38.2 MB, 在所有 Java 提交中击败了74.21%的用户
         * */
        
//        官方
//        int pre = 0, maxAns = nums[0];
//        for (int x : nums) {
//            pre = Math.max(pre + x, x);
//            maxAns = Math.max(maxAns, pre);
//        }
//        return maxAns;
    }
    
    @Test
    public void testLengthOfLastWord() {
        System.out.println(lengthOfLastWord(""));
//        System.out.println(lengthOfLastWord(null));
        System.out.println(lengthOfLastWord("luffy is still joyboy"));
        
        System.out.println(lengthOfLastWord(" fly me   to   the moon  "));
        System.out.println(lengthOfLastWord(" fly  "));
        System.out.println(lengthOfLastWord(" fly me   to   the m  "));
        /*
         * 报错用例："a"
         * java.lang.StringIndexOutOfBoundsException: String index out of range: -1
         * */
        System.out.println(lengthOfLastWord("a"));
    }
    
    public int lengthOfLastWord(String s) {
        int len = s.length();
        if(len==0) {
            return 0;
        }
        int p = len-1;
        while (p>=0 && s.charAt(p) == ' ') {
            p--;
        }
        int q = p;
        while (q>=0 && s.charAt(q) != ' ') {
            q--;
        }
        return p-q;
    }
    
    @Test
    public void testPlusOne() {
//        System.out.println(Arrays.toString( plusOne(new int[]{1,2,3}) ));
        /*
         * 未通过用例
         * 输入：[9]
         * 输出：[10]
         * 预期结果：[1,0]
         * */
        System.out.println(Arrays.toString( plusOne(new int[]{9}) ));
    }
    
    public int[] plusOne(int[] digits) {
        int last = digits.length - 1;
        if (last<0) {
            return digits;
        }
        // 只有一种情况需要新建数组；[9,9,9,9,...,9,9,9]
        boolean allNine = true;
        for (int i = 0; i <= last; i++) {
            if (digits[i] != 9) {
                allNine = false;
                break;
            }
        }
        digits[last]++;
        if (digits[last]<10) {
            return digits;
        }
        
        if (allNine) {
            int[] newDigits = new int[last+2];
            newDigits[0]=1;
            return newDigits;
        }
        while (digits[last]==10) {
            // 置0
            digits[last]=0;
            digits[--last]++;
        }
        return digits;
        /*
         * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
         * 内存消耗：36.8 MB, 在所有 Java 提交中击败了61.25%的用户
         * */
    }
    
    /**
     * 重要
     * 
     */
    @Test
    public void testClimbStairs() {
        System.out.println("7-----21------"+climbStairs(7));
        System.out.println("6-----13------"+climbStairs(6));
        System.out.println("5-----8------"+climbStairs(5));
        System.out.println("4-----5------"+climbStairs(4));
        System.out.println(climbStairs(3));
    }
    
    /**
     * 
     * @param n 正整数
     * @return
     * 动态规划方法
     */
    public int climbStairs(int n) {
        int dp[] = new int[n+1];
        dp[1]=1;
        dp[2]=2;
        for (int i = 3; i <= n; i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
        /**
         * 
         * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
         * 内存消耗：35.1 MB, 在所有 Java 提交中击败了69.06%的用户
         */
    }
    
    // 官方递归方法
    public int climbStairsOfficalRecur(int n) {
        if (n==1) {
            return 1;
        }
        if (n==2) {
            return 2;
        }
        return climbStairsOfficalRecur(n-1)+climbStairsOfficalRecur(n-2);
        // 树的深度为n
        // 时间复杂度O(2^n), 空间复杂度O(n)
    }
    
    // 递归树中有很多重复计算，所以改用记忆递归法
    public int climbStairsOfficalMemory(int n) {
        int[] memo = new int[n+1];
        return climbStairsMemo(n, memo);
        // 时间复杂度O(n)
        /**
         * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
         * 内存消耗：35.1 MB, 在所有 Java 提交中击败了70.62%的用户
         * 
         */
    }
    
    public int climbStairsMemo(int n, int[] memo) {
        if (memo[n]>0) {
            return memo[n];
        }
        if (n==1) {
            return 1;
        }
        else if (n==2) {
            return 2;
        }
        else {
            memo[n] = climbStairsMemo(n-1, memo)+climbStairsMemo(n-2, memo);
        }
        return memo[n];
    }
    
    @Test
    public void testAddBinary() {
        System.out.println(addBinary("0", "0"));
        System.out.println(addBinary("0", "1"));
        System.out.println(addBinary("1", "1"));
        System.out.println(addBinary("11", "1"));
        System.out.println(addBinary("1010", "1011"));
    }
    
    public String addBinary(String a, String b) {
        int aLen = a.length(), bLen = b.length();
        StringBuilder s = new StringBuilder();
        
        int p=aLen-1, q=bLen-1;
        // carry进位
        int aChar, bChar, carry=0;
        while (p>=0 && q>=0) {
            aChar = a.charAt(p) - '0';
            bChar = b.charAt(q) - '0';
//            System.out.println(aChar+"    "+bChar);
            if (aChar+bChar+carry==2) {
//                System.out.println("1----------");
                carry=1;
                s.insert(0, '0');
            }
            else if (aChar+bChar+carry==3) {
                carry=1;
                s.insert(0, '1');
            }
            else if (aChar+bChar+carry==1){
                carry=0;
                s.insert(0, '1');
            }
            else {
                s.insert(0, '0');
            }
            p--;
            q--;
        }
        while (p>=0) {
            aChar = a.charAt(p) - '0';
            if (aChar+carry==2) {
                carry=1;
                s.insert(0, '0');
            }
            else if(carry==1){
                carry=0;
                s.insert(0, '1');
            } 
            else {
                carry=0;
                s.insert(0, aChar);
            }
            p--;
        }
        while (q>=0) {
            bChar = b.charAt(q) - '0';
            if (bChar+carry==2) {
                carry=1;
                s.insert(0, '0');
            }
            else if(carry==1){
                carry=0;
                s.insert(0, '1');
            } 
            else {
                carry=0;
                s.insert(0, bChar);
            }
            q--;
        }
        if (carry==1) {
            s.insert(0, '1');
        }
        return s.toString();
        /**
         * 执行用时：3 ms, 在所有 Java 提交中击败了42.98%的用户
         * 内存消耗：38.2 MB, 在所有 Java 提交中击败了86.01%的用户
         */
    }
    
    public String addBinaryOfficalApi(String a, String b) {
        return Integer.toBinaryString(
            Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }
    
    public String addBinaryOffical(String a, String b) {
        StringBuffer ans = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();
    }
    
    /**
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
     * 返回同样按升序排列的结果链表
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null) {
            return null;
        }
        ListNode p = head, q=p.next;
        while (q!=null) {
            if (p.val==q.val) {
                p.next=q.next;
            }
            else {
                p=p.next;
                
            }
            q=q.next;
        }
        return head;
        /**
         * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
         * 内存消耗：38 MB, 在所有 Java 提交中击败了41.35%的用户
         */
    }
    
    /**
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * @param nums1
     * @param m 表示 nums1 中的元素数目
     * @param nums2
     * @param n 表示 nums2 中的元素数目
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=0, j=0;
        for (int temp; i < m && j < n; ) {
            if (nums1[i]<=nums2[j]) {
                i++;
            }
            else {
                temp = nums1[i];
                nums1[i] = nums2[j];
                nums2[j] = temp;
                i++;
                Arrays.sort(nums2);
            }
        }
        for ( ; j < n; i++, j++) {
            nums1[i]=nums2[j];
        }
        /**
         * 执行用时：1 ms, 在所有 Java 提交中击败了19.90%的用户
         * 内存消耗：38.3 MB, 在所有 Java 提交中击败了86.26%的用户
         */
    }
    
    /**
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null && q==null) {
            return true;
        }
        else if (p==null || q==null) {
            return false;
        }
        else if (p.val==q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        else {
            return false;
        }
        /**
         * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
         * 内存消耗：35.8 MB, 在所有 Java 提交中击败了43.32%的用户
         */
    }
    
    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        TreeNode mirror = mirror(root);
        return contrast(root, mirror);
        /**
         * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
         * 内存消耗：37.9 MB, 在所有 Java 提交中击败了7.51%的用户
         */
    }
    
    public TreeNode mirror(TreeNode root) {
        if (root==null) return null;
        TreeNode node = new TreeNode(root.val);
        node.right=mirror(root.left);
        node.left=mirror(root.right);
        return node;
    }
    
    public boolean contrast(TreeNode p, TreeNode q) {
        if (p==null && q==null) {
            return true;
        }
        else if (p==null || q==null) {
            return false;
        }
        else if (p.val==q.val) {
            return contrast(p.left, q.left) && contrast(p.right, q.right);
        }
        else {
            return false;
        }
    }
    
    public int maxDepth(TreeNode root) {
        if (root!=null) {
            int leftDepth = maxDepth(root.left)+1;
            int rightDepth = maxDepth(root.right)+1;
            return leftDepth > rightDepth ? leftDepth : rightDepth;
        }
        return 0;
        /**
         * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
         * 内存消耗：38.1 MB, 在所有 Java 提交中击败了90.51%的用户
         */
    }
    
    /**
     * 将有序数组转换为二叉搜索树,转换为一棵 高度平衡 二叉搜索树
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = new TreeNode(nums[0]);
        return root;
    }
    
    /**
     * 给定一个二叉树，找出其最小深度。最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * @param root
     * @return
     * 重要!当时没写出来
     */
//    [-9,-3,2,null,4,4,0,-6,null,-5]
    public int minDepth(TreeNode root) {
        if (root==null) {
            return 0;
        }
        if (root.left==null && root.right==null) {
            return 1;
        }
        int minDepth = Integer.MAX_VALUE;
        if (root.left!=null) {
            minDepth = Math.min(minDepth(root.left), minDepth);
        }
        if (root.right!=null) {
            minDepth = Math.min(minDepth(root.right), minDepth);
        }
        return minDepth+1;
        /**
         * 执行用时：5 ms, 在所有 Java 提交中击败了71.48%的用户内存消耗：
         * 58.8 MB, 在所有 Java 提交中击败了48.27%的用户
         */
    }
    
    public int singleNumber(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<len; i++){
            if(map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i])+1);
            }
            else {
                map.put(nums[i], 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue()==1) {
                return entry.getKey();
            }
        }
        return 0;
    }
    
    @Test
    public void testMinStack() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
//        System.out.println(minStack.toString());
        System.out.println(minStack.getMin());
        minStack.pop();
//        System.out.println(minStack.toString());
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
    
    @Test
    public void testConvertToTitle() {
        System.out.println(3+'A');
        System.out.println((char)68);
    }
}

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/**
 * Definition for a binary tree node.
 * LeafSimilarTrees.java中已定义
 */
//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode() {}
//    TreeNode(int val) { this.val = val; }
//    TreeNode(int val, TreeNode left, TreeNode right) {
//        this.val = val;
//        this.left = left;
//        this.right = right;
//    }
//}

class MinStack {

    private ArrayList<Integer> list;
    private int top=-1;

    /** initialize your data structure here. */
    MinStack() {
        list = new ArrayList<>();
    }
    
    public void push(int val) {
        this.top++;
        this.list.add(val);
    }
    
    public void pop() {
        if (top > -1) {
            this.list.remove(top);
            top--;
        }
    }
    
    public int top() {
        if(top > -1){
            return this.list.get(top);
        }
        return -1;
    }
    
    public int getMin() {
        if(top > -1){
            return Collections.min(list);
        }
        return -1;
    }
    
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */