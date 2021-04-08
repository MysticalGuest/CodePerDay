package com.nowcoder.algorithm;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * 给定一个数组，找出其中最小的K个数。
 * 例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 * 如果K>数组的长度，那么返回一个空的数组
 * */
public class Solution3 {
	
	// method one: Sort first, then find several smallest number
	public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
		ArrayList<Integer> al = new ArrayList<>();
		if(input.length<k || k<0 || input==null) { // 不包括k取负值，数组为空的情况(input==null ||input.length<k|| k<0)
			return al;
		}
		Arrays.sort(input);
		for (int i = 0; i < k; i++) {
			al.add(input[i]);
		}
		return al;
    }
	
	/*
	 * 算法时间复杂度：O(n),为什么？
	 * 快排是向下递归的，那么在平均或者说是期望情况下它找到的永远是中间位置，
	 * 这就有点类似折半查找了，所以第一次它会对n个数进行划分，第二次循环它只会进入一边，
	 * 然后对n/2个数进行划分，以此类推，总共需要对n+n/2+n/4+...+2+1=n(1+1/2+1/4+...)+2+1个数进行操作，
	 * 1+1/2+1/4+...<2，因此该表达式小于2n，所以它的期望时间复杂度为O(n)
	 * 
	 * 这种方法会改变原数组中元素的位置，如果面试官不允许改变数组元素的位置呢，
	 * 那么我们就只能使用另外一种算法了，这种算法可以处理大量数据
	 * */
	public ArrayList<Integer> GetLeastNumbers_Solution1(int [] input, int k) {
	    ArrayList<Integer> res = new ArrayList<Integer>();
	    if (input==null||input.length==0||input.length<k||k<=0) {
	        return res;
	    }
	    int start  = 0;
	    int end = input.length-1;
	    int index = partition(input, start, end);
	    while (index != k - 1) { // 只需得到排列好的前k个元素即可
	        if (index > k - 1) {
	            end = index-1;
	            index = partition(input, start, end);
	        } else {
	            start = index+1;
	            index = partition(input, start, end);
	        }
	    }
	    for (int i = 0; i < k; i++) {
	        res.add(input[i]);
	    }
	    return res;
	}
	
	/*
	 * 快排算法思想：
	 * 通常选取第一个为基准
	 * 从后往前遍历，将比这个基准小的往前放
	 * 然后再从前往后，将比这个基准大的往后放
	 * 最后这个基准会在最终位置上
	 * */
	static int partition(int input[], int start, int end) {
		int tmp = input[start];
		while (start < end) {
			while (start < end && input[end] >= tmp) {
				end--;
			}
			input[start] = input[end];
			while (start < end && tmp >= input[start]) {
	            start++;
	        }
	        input[end] = input[start];
	    }
	    input[start] = tmp;
	    return start; // 返回的是分界线元素的下标
	}
	
	/*
	 * 这种算法和堆有关了吧，我们可以先创建一个大小为k的数据容器来存储最小的k个数字，
	 * 接下来每次从输入的n个整数中读入一个数，如果容器中已有的数字少于k个，则直接把这次读入的整数放入容器之中，
	 * 如果容器中已有k个数字了，也就是容器已满，此时我们不能再插入新的数字而只能替换已有的数字，
	 * 找出这已有的k个数中的最大值，然后拿这次待插入的整数和最大值进行比较，小则替换当前最大值，
	 * 大则不可能是最小的k个整数之一，于是我们可以抛弃这个整数。
	 * 因此，当容器满了之后，我们需要进行3步操作：
	 *     一是在k个整数中找到最大数；
	 *     二是有可能从这个容器中删除最大值；
	 *     三是可能插入一个新的数字。
	 *   如果用一颗二叉树来实现这个数据容器，那么我们能在O(logk)时间内实现这三步操作，
	 *   因此，对于n个输入数字而言，总的时间效率就是O(nlogk)。
	 * 我们可以选择用不同的二叉树来实现这个数据容器。
	 * 由于每次都需要找到k个整数中的最大数字，我们很容易想到最大堆，在最大堆中，根节点的值总是大于它的子树中任意结点的值。
	 * 于是我们每次可以在O(1)时间内的到已有的k个数字的最大值，但需要O(logk)时间完成删除即插入操作。
	 * 
	 * 时间复杂度分析，从主函数中循环可以看出循环次数为n-k，当n特别大且k特别小时，n-k近似为n，
	 * 然后在每次循环中进行上述时间复杂度为O(logk)的三个步骤，所以总的时间复杂度为O(nlogk)
	 * */
	public ArrayList<Integer> GetLeastNumbers_Solution2(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (input==null||input.length==0||input.length<k) {
            return res;
        }
        int []maxHeap = new int[k];
        for (int i = 0; i < maxHeap.length; i++) {
            maxHeap[i] = input[i];
        }
        for (int i = (maxHeap.length-1)/2; i >=0 ; i--) {
            adjustHeap(maxHeap, i);
        }
        for (int i = k; i <input.length ; i++) {
            if (maxHeap[0]>input[i]) {
                maxHeap[0] = input[i];
                adjustHeap(maxHeap, 0);
            }
        }
        for (int i = 0; i < maxHeap.length; i++) {
            res.add(maxHeap[i]);
        }
        return res;
    }
	
    static void adjustHeap(int maxHeap[],int i){
        int index = i;
        int lchild=2*i+1;       //i的左孩子节点序号 
        int rchild=2*i+2;     //i的右孩子节点序号 
        if(index<=(maxHeap.length-1)/2) {
            //寻找子节点中最大的节点
            if (lchild<maxHeap.length&&maxHeap[index]<maxHeap[lchild]) {
                index = lchild;
            }
            if (rchild<maxHeap.length&&maxHeap[index]<maxHeap[rchild]) {
                index = rchild;
            }
            if (i!=index) {
                //将节点与最大的子节点交换
                int tmp = maxHeap[index];
                maxHeap[index] = maxHeap[i];
                maxHeap[i] = tmp;
                //交换后，子树可能不满足最大推，递归调整。
                adjustHeap(maxHeap, index);
            }
        }
    }
	
	public static void main(String[] args) {
//		int arr[] = {4};
		int arr[] = {4,5,1,6,2,7,3,8}; // 等同于 int[] arr= new []{4,5,1,6,2,7,3,8};
		
		System.out.println(Arrays.toString(arr));//遍历并输出整个数组
		
		ArrayList<Integer> arrList = new ArrayList<>();
		
		Solution3 s3 = new Solution3();
		
//		arrList = s3.GetLeastNumbers_Solution(arr, 4);
//		arrList = s3.GetLeastNumbers_Solution1(arr, 4);
		arrList = s3.GetLeastNumbers_Solution2(arr, 4);
//		for (Integer integer : arr) {
//			System.out.print(integer+" ");
//		}
		System.out.println(Arrays.toString(arr));//遍历并输出整个数组
		
		System.out.println("\n"+arrList.toString());
	}
}
