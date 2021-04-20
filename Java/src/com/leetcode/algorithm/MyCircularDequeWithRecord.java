package com.leetcode.algorithm;

// 利用每插入一个元素进行记录进行判空
class MyCircularDequeWithRecord {
	
	private int [] que;
	private int front;
	private int rear;
	private int size;
	private int num_of_element=0; // 初始队列中元素为0
	// 根据用例4，还是需要一个标记
	private boolean flag; // true表示this.front==this.rear时指向元素为空
	/**
	 * 作如下规定：
	 * front初始化为0，rear初始化为0
	 * 
	 * 通过用例3发现，有一种状态是第一次在队尾进队时，应该移动front也到这第一个元素；
	 * 应为有元素了为了不让getFront()返回-1，即不然front指向空，就将front也指向rear指向的地方
	 * */
	
	/** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDequeWithRecord(int k) {
    	this.que = new int[k];
    	// 初始化
    	this.front=0; // 队头指针应该指向队头的元素
    	this.rear=0; // 队尾指针应该指向队尾的元素
    	this.size=k;
    	this.flag=true;
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
    	if (isFull()) {
			return false;
		} else {
			if (this.front==this.rear&&this.flag) {
				// 直接插入
				this.flag=false;
			} else {
				this.front=(this.front-1)%this.size;
				if (this.front<0) {
					this.front=this.size-1; // rear points to the end of the array
				}
			}
			this.que[this.front]=value;
			this.num_of_element++;
			if (num_of_element==1) {
				this.rear=this.front;
			}
			return true;
		}
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
    	if (isFull()) {
			return false;
		} else {
			this.rear=(this.rear+1)%this.size;
			this.que[this.rear]=value;
			this.num_of_element++;
			if (num_of_element==1) {
				this.front=this.rear;
			}
			return true;
		}
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
    	if (isEmpty()) { // 判空
			return false;
		}
    	else {  // 非空
			// 队头出队
			this.front=(this.front+1)%this.size;
			this.num_of_element--;
			if (num_of_element==0) {
				this.rear=this.front;
			}
			return true;
		}
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
    	if (isEmpty()) { // Judge whether it is empty
			return false;
		} else { // is not empty
			// The elements at the end of the team are out of the team
				
			this.rear=(this.rear-1)%this.size; // 直接rear=rear-1可否？
			if (this.rear<0) {
				this.rear=this.size-1; // rear points to the end of the array
			}
			this.num_of_element--;
			if (num_of_element==0) {
				this.front=this.rear;
			}
			return true;
		}
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
    	if (isEmpty()) {
			return -1;
		} else {
			return this.que[this.front];
		}
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
    	if (isEmpty()) {
			return -1;
		} else {
			return this.que[this.rear];
		}
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
    	if (this.num_of_element==0) {
			return true;
		} else {
			return false;
		}
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
    	if (this.num_of_element==this.size) {
			return true;
		} else {
			return false;
		}
    }

	public static void main(String[] args) {
		/*
		 * case1
		 * ["MyCircularDeque","insertFront","insertLast","getFront","insertLast","getFront","insertFront","getRear","getFront","getFront","deleteLast","getRear"]
		 * [[5],[7],[0],[],[3],[],[9],[],[],[],[],[]]
		 * 输出：[null,true,true,7,true,7,true,3,9,9,true,0]
		 * 预期结果：[null,true,true,7,true,7,true,3,9,9,true,0]
		 * */
		
		MyCircularDequeWithRecord circularDeque1 = new MyCircularDequeWithRecord(5);
		boolean b = circularDeque1.insertFront(7);
		System.out.print(b+" ");
		b = circularDeque1.insertLast(0);
		System.out.print(b+" ");
		int i=circularDeque1.getFront();  // 7
		System.out.print(i+" ");
		b = circularDeque1.insertLast(3);  // true
		System.out.print(b+" ");
		i=circularDeque1.getFront(); // 7
		System.out.print(i+" ");
		b = circularDeque1.insertFront(9);  // true
		System.out.print(b+" ");
		i=circularDeque1.getRear(); // 3
		System.out.print(i+" ");
		i=circularDeque1.getFront();
		System.out.print(i+" ");
		i=circularDeque1.getFront();
		System.out.print(i+" ");
		b = circularDeque1.deleteLast();
		System.out.print(b+" ");
		i=circularDeque1.getRear();
		System.out.println(i);
		System.err.println("--------------");
		
		/*
		 * case2
		 * ["MyCircularDeque","insertFront","getRear","insertFront","getRear","insertLast","getFront","getRear","getFront","insertLast","deleteLast","getFront"]
		 * [[3],[9],[],[9],[],[5],[],[],[],[8],[],[]]
		 * 输出：[null,true,9,true,9,true,9,5,9,false,true,9]
		 * 预期输出：[null,true,9,true,9,true,9,5,9,false,true,9]
		 * */
		MyCircularDequeWithRecord circularDeque2 = new MyCircularDequeWithRecord(3);
		b = circularDeque2.insertFront(9);
		System.out.print(b+" ");
		i=circularDeque2.getRear();  // 9
		System.out.print(i+" ");
		b = circularDeque2.insertFront(9);  // true
		System.out.print(b+" ");
		i=circularDeque2.getRear();  // 9
		System.out.print(i+" ");
		b = circularDeque2.insertLast(5);  // true
		System.out.print(b+" ");
		i=circularDeque2.getFront();  // 9
		System.out.print(i+" ");
		i=circularDeque2.getRear();  // 5
		System.out.print(i+" ");
		i=circularDeque2.getFront();  // 9
		System.out.print(i+" ");
		b = circularDeque2.insertLast(8);  // false
		System.out.print(b+" ");
		b = circularDeque2.deleteLast();  // true
		System.out.print(b+" ");
		i=circularDeque2.getFront();  // 9
		System.out.println(i+" ");
		System.err.println("--------------");
		
		/*
		 * case3
		 * ["MyCircularDeque","insertLast","insertLast","insertFront","insertFront","getRear","isFull","deleteLast","insertFront","getFront"]
		 * [[3],[1],[2],[3],[4],[],[],[],[4],[]]
		 * 输出：[null,true,true,true,true,3,false,true,false,4]
		 * 预期输出：[null,true,true,true,false,2,true,true,true,4]
		 * */
		MyCircularDequeWithRecord circularDeque3 = new MyCircularDequeWithRecord(3);
		b = circularDeque3.insertLast(1);
		System.out.print(b+" ");
		b = circularDeque3.insertLast(2);
		System.out.print(b+" ");
		b = circularDeque3.insertFront(3);
		System.out.print(b+" ");
		b = circularDeque3.insertFront(4);
		System.out.print(b+" ");
		i=circularDeque3.getRear();
		System.out.print(i+" ");
		b = circularDeque3.isFull();
		System.out.print(b+" ");
		b = circularDeque3.deleteLast();
		System.out.print(b+" ");
		b = circularDeque3.insertFront(4);
		System.out.print(b+" ");
		i = circularDeque3.getFront();
		System.out.println(i+" ");
		System.err.println("--------------");
		
		/*
		 * case4
		 * ["MyCircularDeque","insertFront","deleteLast","getFront","insertLast","insertFront","getFront","getRear","getFront","getFront","getRear","insertLast"]
		 * [[2],[7],[],[],[5],[0],[],[],[],[],[],[0]]
		 * 输出：[null,true,true,-1,true,true,0,0,0,0,0,false]
		 * 预期输出：[null,true,true,-1,true,true,0,5,0,0,5,false]
		 * */
		
		MyCircularDequeWithRecord circularDeque4 = new MyCircularDequeWithRecord(2);
		b = circularDeque4.insertFront(7);System.out.print(b+" ");
		b=circularDeque4.deleteLast();System.out.print(b+" ");
		i=circularDeque4.getFront();System.out.print(i+" ");
		b=circularDeque4.insertLast(5);System.out.print(b+" ");
		b = circularDeque4.insertFront(0);System.out.print(b+" ");
		i=circularDeque4.getFront();System.out.print(i+" ");
		i=circularDeque4.getRear();System.out.print(i+" ");
		i=circularDeque4.getFront();System.out.print(i+" ");
		i=circularDeque4.getFront();System.out.print(i+" ");
		i=circularDeque4.getRear();System.out.print(i+" ");
		b=circularDeque4.insertLast(0);System.out.println(b+" ");
		System.err.println("--------------");

	}

}
