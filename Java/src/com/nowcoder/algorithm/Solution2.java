package com.nowcoder.algorithm;

//Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;
	ListNode() {
		next = null;
	}
	ListNode(int x) {
		val = x;
		next = null;
	}
}

/*
 * 判断给定的链表中是否有环。如果有环则返回true，否则返回false。
 * 你能给出空间复杂度O(1)的解法么？
 * */
public class Solution2 {
	
	// 快慢指针
	public boolean hasCycle(ListNode head) {
		if(head==null) // 空表
			return false;
        ListNode slow=head, fast=head;
        // I actually thought there would be something wrong...java.lang.NullPointerException
//        while(slow.next!=null && fast.next.next!=null) {
//        	slow=slow.next;
//        	fast=fast.next.next;
//        	if (slow==fast) {
//        		return true;
//        	}
//        }
        if(head.next==null)
        	return false;
        while(slow.next!=null && fast.next.next!=null) {
        	if(slow!=null) {
        		if (slow.next!=null) {
        			slow=slow.next;
				}
        		else {
					return false;
				}
        	}
        	if(fast!=null) {
        		if(fast.next!=null)
        			fast=fast.next.next;
        		else {
					return false;
				}
        	}
        	
        	if (fast==null || slow==null || fast.next==null) {
				return false;
			}
        		
        	if (slow==fast) {
				return true;
			}
        }
        return false;
    }

	public static void main(String[] args) {
		// 建立一个带头结点的有环链表
		ListNode ln=new ListNode();
		/*
		 * ListNode ln=null;
		 * 注意这里有个Null pointer access: The variable ln can only be null at this location问题的解决
		 * */
		ListNode l=new ListNode();
		ln=l; // l指向ln
		
		ListNode temp=new ListNode();
		for (int i = 1; i<=10; i++) {
			ListNode s=new ListNode(i);
			l.next=s;
			l=l.next;
			if(i==5) { // 记住中间一个节点，后面加环
				temp=l;
			}
		}
		
//		l.next=null; // 不加环
		l.next=temp; // 加环
		l=ln;

//		while (l!=null) {
//			System.out.println("ln.val : "+l.val);
//			l=l.next;
//		}

		
		for (int i = 1; i<=40; i++) {
			System.out.println("ln.val : "+l.val);
			l=l.next;
		}
		Solution2 s2 = new Solution2();
		System.out.println("Is there a ring? "+s2.hasCycle(ln));
		
		// pass
	}

}
