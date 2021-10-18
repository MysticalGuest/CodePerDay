package com.leetcode.algorithm;

/*
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 
 * 不带头结点的单链表
 * */

/**
 * Definition for singly-linked list.
 */
//class ListNode {
//	int val;
//	ListNode next;
//	ListNode() {}
//	ListNode(int val) { this.val = val; }
//	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//}

public class Solution1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	if (l1==null || l2==null) {
			return null;
		}
    	ListNode p=l1, q=l2, l=new ListNode(-1, null); // 用带有头结点的l存放最终结果链
    	ListNode r=l, n;
    	int carry=1, sum;
    	boolean flag=false; // 表示下一次不用进位 
    	while(p!=null && q!=null) {
    		if(flag) { // 需要进位
    			sum=carry + p.val + q.val;
    			flag = false; // 进位后将标志至false
    		}
    		else {
    			sum=p.val+q.val;
			}
    		if(sum>=10) {
    			flag=true; // 下次要进位
    			sum = sum-10; // 实际当前位是数字几
    		}
			n=new ListNode(sum, null);
			r.next=n;
			r=r.next;
			p=p.next;
			q=q.next;
    	}
    	
    	/*
    	 * 接下来处理不同位数相加
    	 * l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
    	 * 输出：[8,9,9,9,0,0,0,1]
    	 * 解释：9999999+9999=10009998
    	 * */
    	
    	// 跳出循环只能是p或q有一个为空，选出这个非空链
    	if(p==null) {
    		p=q;
    	}
    	while(p!=null) {
    		if(flag) { // 需要进位
    			sum=p.val+carry;
    			flag=false;
    		}
    		else {
				sum=p.val;
			}
    		if(sum==10) {
    			flag=true; // 下次要进位
    			sum=sum-10;
    		}
    		n=new ListNode(sum, null);
			r.next=n;
			r=r.next;
			p=p.next;
    	}
    	
    	if(flag) { // 虽然p为null但还有一个进位
    		n=new ListNode(carry, null);
			r.next=n;
    	}
    	
    	return l.next;
    }

	public static void main(String[] args) {
		// 创建这两个单链表
		
		/*
		 * 输入：l1 = [2,4,3], l2 = [5,6,4]
		 * 输出：[7,0,8]
		 * 解释：342 + 465 = 807.
		 * */
		System.out.println("输入1----");
		ListNode ln1 = new ListNode(2, null);
		ListNode p = ln1;
		ListNode node = new ListNode(4, null);
		p.next=node; p=p.next;
		node = new ListNode(3, null);
		p.next = node;
		
		ListNode ln2 = new ListNode(5, null);
		p=ln2;
		node = new ListNode(6, null);
		p.next=node; p=p.next;
		node = new ListNode(4, null);
		p.next=node;
		
		for(p=ln1; p!=null; p=p.next) {
			System.out.print(p.val+" ");
		}
		System.out.println();
		for(p=ln2; p!=null; p=p.next) {
			System.out.print(p.val+" ");
		}
		System.out.println();
		
		Solution1 s1 = new Solution1();
		ListNode ln = s1.addTwoNumbers(ln1, ln2);
		for(p=ln; p!=null; p=p.next) {
			System.out.print(p.val+" ");
		}
		System.out.println();
		
		/*
		 * 输入：l1 = [0], l2 = [0]
		 * 输出：[0]
		 * */
		System.out.println("输入2----");
		ListNode ln3 = new ListNode(0, null);
		ListNode ln4 = new ListNode(0, null);
		ln = s1.addTwoNumbers(ln3, ln4);
		for(p=ln; p!=null; p=p.next) {
			System.out.print(p.val+" ");
		}
		System.out.println();
		
		/*
		 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
		 * 输出：[8,9,9,9,0,0,0,1]
		 * */
		System.out.println("输入3----");
		ListNode ln5 = new ListNode(9, null);
		ListNode ln6 = new ListNode(9, null);
		
		p=ln5;
		for(int i=0; i<6; i++) {
			node = new ListNode(9, null);
			p.next=node;
			p=p.next;
		}
		p=ln6;
		for(int i=0; i<3; i++) {
			node = new ListNode(9, null);
			p.next=node;
			p=p.next;
		}
		
		for(p=ln5; p!=null; p=p.next) {
			System.out.print(p.val+" ");
		}
		System.out.println();
		for(p=ln6; p!=null; p=p.next) {
			System.out.print(p.val+" ");
		}
		System.out.println();
		ln = s1.addTwoNumbers(ln5, ln6);
		for(p=ln; p!=null; p=p.next) {
			System.out.print(p.val+" ");
		}
	}
	

}
