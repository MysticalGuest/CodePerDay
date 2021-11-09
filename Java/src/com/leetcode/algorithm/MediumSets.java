 package com.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author MysteryGuest
 * @date 2021/05/12
 * 将几道中等难度题的解答放入一个文件
 */
public class MediumSets {

    @Test
    /*
     * !!!!
     * 一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * */
    public void testSum() {
        /*
         * 输入：nums = [-1,0,1,2,-1,-4]
         * 输出：[[-1,-1,2],[-1,0,1]]
         * */
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
        
        /*
         * 输入：nums = [0]
         * 输出：[]
         * */
        int[] nums1 = {0};
        System.out.println(threeSum(nums1));
        
        /*
         * 输入：nums = [-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0]
         * 输出：[[-4,1,3],[-4,4,0],[-2,1,1],[-2,4,-2],[1,-5,4]]
         * 预期输出：[[-5,1,4],[-4,0,4],[-4,1,3],[-2,-2,4],[-2,1,1],[0,0,0]]
         * */
        int[] nums2 = {-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        System.out.println(threeSum(nums2));
    }
    
    // 超出时间限制，舍弃
    public List<List<Integer>> threeSumOut(int[] nums) {
        if (nums.length<3) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        
        //int i=0, j=1, k=2; // 3个下标
        boolean repeat = false, zero_appear=false;
        for (int i=0; i < nums.length-2; i++) {
            for (int j=i+1; j < nums.length-1; j++) {
                for (int k=j+1; k < nums.length; k++) {
                    if (nums[i]==0&&nums[j]==0&&nums[k]==0 && !zero_appear) {
                        // 特殊情况：都是0
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        result.add(list);
                        zero_appear=true;
                    }
                    else if (nums[i]+nums[j]+nums[k]==0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        for (List<Integer> re : result) {
                            // 如果此列表包含指定的元素，则返回 true，避免这个3个数与之前重复
                            if (re.contains(nums[i]) && re.contains(nums[j]) && re.contains(nums[k])) {
                                repeat = true;
                                break;
                            }
                        }
                        if (!repeat) {
                            result.add(list);
                        }
                        repeat=false;
                    }
                }
            }
        }
        return result;
    }
    
    // 官方思路: 排序+双指针, 第一个指针指向i对应元素后面的元素, 第二个指针指向最后一个元素
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length<3) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        // 先排序
        Arrays.sort(nums);
        int left, right, sum;
        for (int i = 0; i < nums.length; i++) {
            // 避免重复
            if(nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if(i > 0 && nums[i] == nums[i-1]) continue; // 去重,i=0时，nums[i-1]不会报错吗？？？？

            left=i+1;
            right=nums.length-1;
            while(left < right) {

                sum = nums[i] + nums[left] + nums[right];
                if (sum<0) {
                    left++;
                    continue;
                }
                if (sum>0) {
                    right--;
                    continue;
                }
//                List<Integer> list = new ArrayList<>();
//                list.add(nums[left]);
//                list.add(nums[i]);
//                list.add(nums[right]);
//                result.add(list);
                // 以上代码一行代替
                result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                while (left<right && nums[left] == nums[left+1]) left++; // 去重
                while (left<right && nums[right] == nums[right-1]) right--; // 去重

                left++;
                right--;
                
            }
            
            
        }
        return result;
        
    }
    
    @Test
    /*
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * 0 <= digits.length <= 4
     * digits[i] 是范围 ['2', '9'] 的一个数字。
     * */
    public void testPhoneNum() {
        /*
         * 输入：digits = "23"
         * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
         * */
        String digits="23";
        System.out.println(letterCombinations(digits));
        
        /*
         * 输入：digits = ""
         * 输出：[]
         * */
        String digits1="";
        System.out.println(letterCombinations(digits1));
        
        /*
         * 输入：digits = "2"
         * 输出：["a","b","c"]
         * */
        digits1 += "2";
        System.out.println(letterCombinations(digits1));
        
        digits1 = "";
        digits1 += "27";
        System.out.println(letterCombinations(digits1));
        System.err.println(letterCombinationsOffical(digits1));
        
        digits1 = "";
        digits1 += "2745";
        System.out.println(letterCombinations(digits1));
        
        digits1 = "";
        digits1 += "9";
        System.out.println(letterCombinations(digits1));
    }
    
    /*
     * 执行用时：8 ms, 在所有 Java 提交中击败了9.85%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了7.48%的用户
     * */
    public List<String> letterCombinations(String digits) {
        if (digits.length()<1) {
            return new ArrayList<>();
        }
        /*
         * 数字2对应下标0, 数字9对应下标7
         * */
        final char table[][] = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
        };
        
        List<String> result = new ArrayList<>();
        int index;
        for (int i = 0; i < digits.length(); i++) {
            index = digits.charAt(i) - '0' - 2; // 对应table下标
            if (i<1) {
                for (int j = 0; j < table[index].length; j++) {
                    String str = table[index][j]+"";
                    result.add(str);
                }
            }
            else {
//                for (String string : result) {
//                    for (int j = 0; j < table[index].length; j++) {
//                        String str = table[index][j] + string;
//                        /*
//                         * 遍历result的同时修改了result, 会报错
//                         * if (modCount != expectedModCount)
//                         *     throw new ConcurrentModificationException();
//                         * */
//                        result.add(str);
//                    }
//                }
                List<String> temp = new ArrayList<>();
                for (String string : result) {
                    for (int j = 0; j < table[index].length; j++) {
                        String str = string + table[index][j] ;
                        temp.add(str);
                    }
                }
                result=temp;
            }
        }
        
        return result;
    }
    
    /*
     * 回溯：回溯过程中维护一个字符串，表示已有的字母排列（如果未遍历完电话号码的所有数字，则已有的字母排列是不完整的）。
     * 该字符串初始为空。每次取电话号码的一位数字，从哈希表中获得该数字对应的所有可能的字母，
     * 并将其中的一个字母插入到已有的字母排列后面，然后继续处理电话号码的后一位数字，直到处理完电话号码中的所有数字，
     * 即得到一个完整的字母排列。然后进行回退操作，遍历其余的字母排列。
     * 回溯算法用于寻找所有的可行解，如果发现一个解不可行，则会舍弃不可行的解。
     * 在这道题中，由于每个数字对应的每个字母都可能进入字母组合，因此不存在不可行的解，直接穷举所有的解即可。
     * 
     * 复杂度分析：
     * 时间复杂度：O(3^m * 4^n)，其中 m 是输入中对应 3 个字母的数字个数（包括数字 2、3、4、5、6、8），
     * n 是输入中对应 4 个字母的数字个数（包括数字 7、9），m+n是输入数字的总个数。
     * 当输入包含 m 个对应 3 个字母的数字和 n 个对应 4 个字母的数字时，不同的字母组合一共有 3^m * 4^n3 种，
     * 需要遍历每一种字母组合。
     * 
     * 空间复杂度：O(m+n)，其中 m 是输入中对应 3 个字母的数字个数，n 是输入中对应 4 个字母的数字个数，
     * m+n是输入数字的总个数。除了返回值以外，空间复杂度主要取决于哈希表以及回溯过程中的递归调用层数，
     * 哈希表的大小与输入无关，可以看成常数，递归调用层数最大为 m+n。
     * */
    public List<String> letterCombinationsOffical(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        //Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的
        Map<Character, String> phoneMap = new HashMap<Character, String>() {/**
             *
             */
            private static final long serialVersionUID = 1L;

        {
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
        
        /// 什么情况下用回溯呢？？？
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                // 这里为什么要删除？奥，因为这里的for循环,比如ap后，如果不删除，下一次就是apq
                combination.deleteCharAt(index);
            }
        }
    }
    
    @Test
    public void testJumpGame() {

        /*
         * 输入：nums = [2,3,1,1,4]
         * 输出：true
         * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
         * */
        int nums[]= {2,3,1,1,4,1};
        System.out.println(canJump(nums));
        
        /*
         * 输入：nums = [3,2,1,0,4]
         * 输出：false
         * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
         * */
        int nums1[]= {3,2,1,0,4};
        System.out.println(canJump(nums1));
        
        /*
         * 递归：
         * 未通过用例：[2,5,0,0]
         * 输出：false
         * 预期：true
         * */
        int nums2[]= {2,5,0,0};
        System.out.println(canJump(nums2));
        
        /*
         * 方法二：
         * 未通过用例：[0,1]
         * 输出：true
         * 预期：false
         * */
        int nums3[]= {0,1};
        System.out.println(canJump(nums3));
        
        /*
         * 方法二：
         * 未通过用例：[0]
         * 输出：false
         * 预期：true
         * */
        int nums4[]= {0};
        System.out.println(canJump(nums4));
        
        /*
         * 方法二：
         * 未通过用例：[2,0,0]
         * 输出：false
         * 预期：true
         * */
        int nums5[]= {2,0,0};
        System.out.println(canJump(nums5));
        
        
        
    }
    
    public boolean canJump(int[] nums) {
//        return canJump(nums, 0);

        if (nums.length<=1) {
            return true;
        }
        for (int i = 0; i < nums.length; i++) {
            int j;
            for (j = 0; j < i; j++) {
                // 该点可以到达
                if (j+nums[j]>i) {
                    break;
                }
            }
            if (j>=i&&nums[i]==0&&i<nums.length-1) {
                return false;
            }
        }
        return true;
        /*
         * 执行用时：600 ms, 在所有 Java 提交中击败了6.08%的用户
         * 内存消耗：39.4 MB, 在所有 Java 提交中击败了97.76%的用户
         * */
    }
    
    public boolean canJump(int[] nums, int startIndex) {
        if (startIndex>=nums.length) {
            return true;
        }
//        boolean b=false;
//        if (startIndex == nums.length-1) {
//            return true;
//        }
//        for (int i = 1; i <= nums[startIndex]; i++) {
//            b = b||canJump(nums, startIndex+i);
//            if (b) {
//                return b;
//            }
//        }
//        return false;
        
        boolean b=false;
        if (startIndex == nums.length-1) {
            return true;
        }
        for (int i = nums[startIndex]; i>0 ; i--) {
            b = b||canJump(nums, startIndex+i);
            if (b) {
                return b;
            }
        }
        return false;
        /*超出时间限制，不能用递归？*/
    }
    
    // 官方：贪心算法
    public boolean canJumpOffical(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Test
    /*
     * 给你一个整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     * */
    public void testTarget() {
        /*
         * 输入：nums = [1,1,1,1,1], target = 3
         * 输出：5
         * 解释：一共有 5 种方法让最终目标和为 3 。
         * -1 + 1 + 1 + 1 + 1 = 3
         * +1 - 1 + 1 + 1 + 1 = 3
         * +1 + 1 - 1 + 1 + 1 = 3
         * +1 + 1 + 1 - 1 + 1 = 3
         * +1 + 1 + 1 + 1 - 1 = 3
         * */
        int nums1[] = {1,1,1,1,1};
        System.out.println(findTargetSumWays(nums1, 3));
        
        /*
         * 输入：nums = [1], target = 1
         * 输出：1
         * */
        int nums2[] = {1};
        System.out.println(findTargetSumWays(nums2, 1));
        
    }
    
    // 回溯
    public int findTargetSumWays(int[] nums, int target) {
        return findTargetSum(nums, target, 0, 0);
        /*
         * 执行用时：694 ms, 在所有 Java 提交中击败了12.56%的用户
         * 内存消耗：35.6 MB, 在所有 Java 提交中击败了99.19%的用户
         * */
    }
    
    public int findTargetSum(int[] nums, int target, int index, int sum) {
        int num=0, len=nums.length;
        if (sum==target && index==len) {
            return 1;
        }
        if (index>=len) {
            return 0;
        }
        num += findTargetSum(nums, target, index+1, sum+nums[index]);
        num += findTargetSum(nums, target, index+1, sum-nums[index]);
        return num;
    }
    
    @Test
    public void testZigZagConversion() {
//        System.out.println(convert("PAYPALISHIRING", 2));
//        System.out.println(convert("PAYPALISHIRING", 3));
//        System.out.println(convert("PAYPALISHIRING", 4));
//        System.out.println(convert("Apalindromeisaword,phrase,number,orothersequenceofunitsthatcanbereadthesamewayineitherdirection,withgeneralallowancesforadjustmentstopunctuationandworddividers.", 3));
//        System.out.println(convert("AB", 5));
//        System.out.println(convert("PAYPALISHIRING", 5));
//        System.out.println(convert("PAYPALISHIRING", 7));
        int l=34;
        System.out.println(1&1);
        System.out.println(0&1);
        System.out.println(100&1);
        System.out.println(101&1);
        System.out.println(l&1);
    }
    
    // 内存超出限制，这个二维数组的大小控制不来
    public String myconvert(String s, int numRows) {
        int len = s.length();
        System.out.println("len:  "+len);
        if(len==1||numRows==1||len<numRows){
            return s;
        }
        StringBuilder res = new StringBuilder();
        int cols = len*numRows;//超出内存限制,Java动态二维数组比较麻烦,有时间可以尝试其他语言
        char[][] c=new char[numRows][cols];
//        ArrayList<Integer> c[] = new ArrayList[numRows];
        boolean flag = true;
        for(int i=0, m=0, n=0; i<len; i++){
            
            if(m<numRows && flag){
                System.out.println("1:::m: "+m+" n: "+n+"  i:"+i);
                c[m][n]=s.charAt(i);
                m++;
            }
            else{
                if (m==numRows) {
                    m--;
                }
                n++;
                m--;
                if (flag) {
                    flag=!flag;
                }
                if(m==0){
                    flag=!flag;
                    i--;
                    continue;
                }
                System.out.println("2:::m: "+m+" n: "+n+"  i:"+i);
                c[m][n]=s.charAt(i);
            }
            
        }
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < cols; j++) {
                if (c[i][j]=='\u0000') {
                    System.out.print(" ");
                }
                else {
                    res.append(c[i][j]);
                    System.out.print(c[i][j]);
                }
//                if (c[i][j]!='\u0000') {
//                    res.append(c[i][j]);
//                }
                
            }
            System.out.println();
        }
        return res.toString();
    }
    
    // 按行排序
    public String convert(String s, int numRows) {
        if (s.length()<2) {
            return s;
        }
        // 画图可以看出，字母的变化，行（横）坐标一会儿递增一会儿递减，flag在正负1之间变化来控制横坐标移动
        int flag=-1, r=0;
        ArrayList<StringBuilder> sort = new ArrayList<StringBuilder>();
        for (int i = 0; i < numRows; i++) {
            sort.add(new StringBuilder());
        }
        for (int i = 0; i < s.length(); i++) {
            sort.get(r).append(s.charAt(i));
            if (r==0||r==numRows-1) {
                flag=-flag;
            }
            r+=flag;
        }
        String res="";
        for (StringBuilder sb:sort) {
            res+=sb.toString();
        }
        return res;
    }
    
    @Test
    public void testMyAtoi() {
//        String s = "   as23      ::    ";
//        System.out.println(s.intern());
//        String s1= s.trim();
//        System.out.println(s1);
//        System.out.println(String.valueOf(false)+"  "+String.valueOf(true));
//        System.out.println(String.valueOf(123));
//        System.out.println(Integer.parseInt("001231"));
//        String s2 = "-91283472332";
//        System.out.println(s2.compareTo("-2147483648"));
//        String s3 = "-283472332";
//        System.out.println(s3.compareTo("-2147483648"));
//        String s4 = "41283472332";
//        System.out.println(s4.compareTo("2147483647"));
//        String s5 = "343434";
//        System.out.println(s5.compareTo("2147483647"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("+-12"));
    }
    
    public int myAtoi(String s) {
        String st = s.trim();
        boolean flag = true; // 默认为正数
        StringBuilder sb = new StringBuilder();
        char[] cs = st.toCharArray();
        int op=0;
        for(int i=0; i<cs.length; i++) {
            if(cs[i]=='-') {
                flag = false;
            }
            if (cs[i]!='+' && cs[i]!='-' && !Character.isDigit(cs[i])) {
                break;
            }
            // +-号相邻的情况
            while (i<cs.length && (cs[i]=='+' || cs[i]=='-')) {
                op++;
                i++;
            }
            if (op>1) {
                break;
            }
            while (i<cs.length && Character.isDigit(cs[i])) {
                sb.append(cs[i]);
                i++;
            }
            if (sb.length()>0) {
                break;
            }
        }
        if(sb.length()<1) {
            return 0;
        }
        try {
            int num = Integer.parseInt(sb.toString());
            return flag ? num : 0 - num;
        } catch (Exception e) {
            if (flag) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }
    }
    
}
