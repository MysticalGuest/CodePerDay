package com.nowcoder.acm;

// LinkedList类是双向列表,列表中的每个节点都包含了对前一个和后一个元素的引用
import java.util.LinkedList;

// 链表实现栈？
public class MyStack {
	
	// 泛型的用法
	// LinkedList是原始类型。 泛型类型LinkedList <E>的引用应参数化
	private LinkedList<Object> ll=new LinkedList<>();
	
	public void push(Object o)
	{
		ll.addFirst(o);
	}
	
	public Object pop()
	{
		return ll.removeFirst();
	}
	
	public Object peek()
	{
		return ll.getFirst();
	}
	
	public boolean empty()
	{
		return ll.isEmpty();
	}
	
	
	public static void main(String[] args)
	{
		MyStack ms=new MyStack();
		ms.push("one");
		ms.push("two");
		ms.push("three");
		
		System.out.println(ms.pop());
		System.out.println(ms.peek());
		System.out.println(ms.pop());
		System.out.println(ms.empty());
	}

}
