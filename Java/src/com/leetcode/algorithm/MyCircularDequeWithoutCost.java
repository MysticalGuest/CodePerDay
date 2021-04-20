package com.leetcode.algorithm;

// 有问题的，失败了
class MyCircularDequeWithoutCost {

	private int [] que;
	private int front;
	private int rear;
	private int size;
	private boolean flag;
	
	/** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDequeWithoutCost(int k) {
    	// 底层用数组实现，根据方法的参数，判断数组中存储的元素类型应该是int类型的
    	this.que = new int[k];
    	// 初始化
    	this.front=0; // 队头指针应该指向队头的元素
    	this.rear=0; // 队尾指针应该指向队尾的元素
    	this.size=k;
    	this.flag=true; // 说明front位置无元素
    	/*
    	 * 但根据这一题的用例进行分析：
    	 * 插入第3个元素时任然返回true，就是说不用牺牲空间？
    	 * 也就是说front和rear都指向的有元素，
    	 * 那么就有问题了，初始状态插入时，就要讨论一下了，并做好规定
    	 * 
    	 * */
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
    	if (isFull()) { // 判满条件不变？变了
			return false;
		} else {
			
			// 队头入队
			if(this.flag) { // 队空，还未插入任何元素
				// this.front==0&&this.flag表示front还有位置
				this.flag=false;// 直接进队，直接在0的位置进队，将flag置false表示front指向的位置有元素
				
			}
			else if(this.front==0) {
				this.front=this.size-1; // rear points to the end of the array
			}
			else {
				this.front=(this.front-1)%this.size;
			}
			this.que[this.front]=value;
			return true;
		}

    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
    	if (isFull()) { // 判满
			return false;
		} else {
			// 队尾入队
			this.rear=(this.rear+1)%this.size;
			this.que[this.rear]=value;
			// 第三个用例，发现及时插入满了，但flag没置false，程序就会认为队列非满
			if(this.rear==this.front) {
				this.flag=false;
			}
			return true;
		}
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
    	if (isEmpty()) { // 判空
			return false;
		}
    	else if (this.front==this.rear) { // 特殊情况
			this.flag=true;
			return true;
		}
    	else {  // 非空
			// 队头出队
			this.front=(this.front+1)%this.size;
			return true;
		}

    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
    	if (isEmpty()) { // Judge whether it is empty
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
    	if (isEmpty()) {
    		return -1;
		}
    	// there is something different from the function getRear(), because it has expanded one cell of the array
    	return this.que[this.front];
    }
    
    /** Get the last item from the deque. If the deque is empty, return -1. */
    public int getRear() {
    	if (isEmpty()) {
    		return -1;
		}
    	return this.que[this.rear];
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
    	if (this.rear==this.front&&this.flag) { // 判空多加了一个条件限制
    		return true;
		}
    	return false;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
//    	System.err.println("this.rear: "+this.rear+"this.front: "+this.front);
    	if (this.flag) {
			return false;
		}
    	if ((this.rear+1)%this.size==this.front) { // 判满也变了，例如，如果一直在队尾插入，则front始终空着
    		return true;
		}
    	if (!this.flag&&this.rear==this.front) { // 用例3遇到的情况
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
		MyCircularDequeWithoutCost circularDeque = new MyCircularDequeWithoutCost(3); // set the size to be 3
		boolean b = circularDeque.insertLast(1);			// return true
//		System.err.println(circularDeque.getFront());
//		System.err.println(circularDeque.getRear());
		System.out.print(b+" ");
		b = circularDeque.insertLast(2);			// return true
//		System.err.println(circularDeque.getFront());
//		System.err.println(circularDeque.getRear());
		System.out.print(b+" ");
		b = circularDeque.insertFront(3);			// return true
//		System.err.println(circularDeque.getFront());
//		System.err.println(circularDeque.getRear());
		System.out.print(b+" ");
		b = circularDeque.insertFront(4);			// return false, the queue is full
		System.out.print(b+" ");
		int i = circularDeque.getRear();  			// return 2
		System.out.print(i+" ");
		b = circularDeque.isFull();				// return true
		System.out.print(b+" ");
		b = circularDeque.deleteLast();			// return true
		System.out.print(b+" ");
		b = circularDeque.insertFront(4);			// return true
		System.out.print(b+" ");
		i = circularDeque.getFront();			// return 4
//		System.out.println(i);
		System.err.println("--------------");
		
		/*
		 * case1
		 * ["MyCircularDeque","insertFront","insertLast","getFront","insertLast","getFront","insertFront","getRear","getFront","getFront","deleteLast","getRear"]
		 * [[5],[7],[0],[],[3],[],[9],[],[],[],[],[]]
		 * 输出：[null,true,true,7,true,7,true,0,9,9,true,0]
		 * 预期结果：[null,true,true,7,true,7,true,3,9,9,true,0]
		 * */
		
		MyCircularDequeWithoutCost circularDeque1 = new MyCircularDequeWithoutCost(5);
		b = circularDeque1.insertFront(7);
		System.out.print(b+" ");
		b = circularDeque1.insertLast(0);
		System.out.print(b+" ");
		i=circularDeque1.getFront();  // 7
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
		 * ["MyCircularDeque","insertFront","getRear","insertFront","getRear","insertLast","getFront","getRear","getFront","insertLast","deleteLast","getFront"]
		 * [[3],[9],[],[9],[],[5],[],[],[],[8],[],[]]
		 * 输出：[null,true,9,true,9,true,9,5,9,true,true,9]
		 * 预期输出：[null,true,9,true,9,true,9,5,9,false,true,9]
		 * */
		MyCircularDequeWithoutCost circularDeque2 = new MyCircularDequeWithoutCost(3);
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
		 * ["MyCircularDeque","insertLast","insertLast","insertFront","insertFront","getRear","isFull","deleteLast","insertFront","getFront"]
		 * [[3],[1],[2],[3],[4],[],[],[],[4],[]]
		 * 输出：[null,true,true,true,true,4,false,true,false,4]
		 * 预期输出：[null,true,true,true,false,2,true,true,true,4]
		 * */
		MyCircularDequeWithoutCost circularDeque3 = new MyCircularDequeWithoutCost(3);
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
	}

}
