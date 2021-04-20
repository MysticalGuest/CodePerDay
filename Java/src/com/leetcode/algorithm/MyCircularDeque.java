package com.leetcode.algorithm;

/// Design Circular Deque设计循环双端队列

/*
 * 分析一下：
 * 既然是队列，就有队头指针和队尾指针
 * */

class MyCircularDeque {
	
	private int [] que;
	private int front;
	private int rear;
	private int size;
	
	/** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
    	// 底层用数组实现，根据方法的参数，判断数组中存储的元素类型应该是int类型的
    	this.que = new int[k];
    	// 初始化，到底等于-1还是0
    	this.front=0; // 队头指针应该指向队头元素的前一个，需要牺牲一个位置，我发现第一轮入队，0这个位置不会有元素
    	this.rear=0; // 队尾指针应该指向队尾的元素
    	this.size=k;
    	/*
    	 * 产生一个问题，普通顺序队要牺牲一个空间
    	 * 那么这题的双端队列要牺牲两个吗？前后各一个？
    	 * 
    	 * 首先想一下普通队列为什么要牺牲一个空间呢？
    	 * 将队列看成首尾相连，即循环队列(0..n-1),在循环队列下，
    	 * q[n]的队列中元素的个数可能为0,1,2…,n-2,n-1,n共n+1中可能性（0表示队空，n表示队满），
    	 * 但是rear-front(尾指针和头指针下标的差值)的结果只可能是0,1,2…,n-2,n-1共n种结果，
    	 * 因此，没办法使用n中结果去对应n+1中可能性，只能使用一个空的数据位来实现循环队列。
    	 * 我想双端队列不用牺牲两个，判空判满条件不变，只牺牲一个
    	 * 
    	 * 但根据这一题的用例进行分析：
    	 * 插入第3个元素时任然返回true，就是说不用牺牲空间？
    	 * */
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
    	if ((this.rear+1)%this.size==this.front) { // 判满
			return false;
		} else {
			// 队头入队，还是牺牲队头指向的空间把
			this.que[this.front]=value;
			if(this.front==0) {
				this.front=this.size-1; // front指向数组末
			}
			else {
				this.front=(this.front-1)%this.size;
			}
			return true;
		}

    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
    	if ((this.rear+1)%this.size==this.front) { // 判满
			return false;
		} else {
			// 队尾入队
			this.rear=(this.rear+1)%this.size;
			this.que[this.rear]=value;
			return true;
		}
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
    	if (this.rear==this.front) { // 判空
			return false;
		} else {  // 非空
			// 队头出队
			this.front=(this.front+1)%this.size;
			return true;
		}

    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
    	if (this.rear==this.front) { // Judge whether it is empty
			return false;
		} else { // is not empty
			// The elements at the end of the team are out of the team
			if(this.rear==0) {
				this.rear=this.size-1; // rear points to the end of the array
			}
			else {
				this.rear=(this.rear-1)%this.size; // 直接rear=rear-1可否？
			}
			return true;
		}
    }
    
    /** Get the front item from the deque.If the deque is empty, return -1. */
    public int getFront() {
//    	if (this.rear==this.front) {  // Judge whether it is empty
//    		return -1;
//		}
    	if (isEmpty()) {
    		return -1;
		}
    	// there is something different from the function getRear(), because it has expanded one cell of the array
    	return this.que[(this.front+1)%this.size];
    }
    
    /** Get the last item from the deque. If the deque is empty, return -1. */
    public int getRear() {
    	if (this.rear==this.front) {
    		return -1;
		}
    	return this.que[this.rear];
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
    	if (this.rear==this.front) {
    		return true;
		}
    	return false;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
    	if ((this.rear+1)%this.size==this.front) {
    		return true;
		}
    	return false;
    }

    /**
     * Your MyCircularDeque object will be instantiated and called as such:
     * MyCircularDeque obj = new MyCircularDeque(k);
     * boolean param_1 = obj.insertFront(value);
     * boolean param_2 = obj.insertLast(value);
     * boolean param_3 = obj.deleteFront();
     * boolean param_4 = obj.deleteLast();
     * int param_5 = obj.getFront();
     * int param_6 = obj.getRear();
     * boolean param_7 = obj.isEmpty();
     * boolean param_8 = obj.isFull();
     */
	public static void main(String[] args) {
		MyCircularDeque circularDeque = new MyCircularDeque(3); // set the size to be 3
		boolean b = circularDeque.insertLast(1);			// return true
		System.out.println(b);
		b = circularDeque.insertLast(2);			// return true
		System.out.println(b);
		b = circularDeque.insertFront(3);			// return true
		System.out.println(b);
		b = circularDeque.insertFront(4);			// return false, the queue is full
		System.out.println(b);
		int i = circularDeque.getRear();  			// return 2
		System.out.println(i);
		b = circularDeque.isFull();				// return true
		System.out.println(b);
		b = circularDeque.deleteLast();			// return true
		System.out.println(b);
		b = circularDeque.insertFront(4);			// return true
		System.out.println(b);
		i = circularDeque.getFront();			// return 4
		System.out.println(i);

	}

}
