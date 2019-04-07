package com.my.test;

import java.util.Random;

public class LinkedListTest {
	public static void main(String a[]) {
		LinkedListNode root = new LinkedListNode(1);
		Random random = new Random();
		LinkedListNode current = root;
		for(int i = 1; i < 10; i++) {
			LinkedListNode newn = new LinkedListNode(random.nextInt(10));
			current.setNext(newn);
			current = newn;
		}
		printLlist(root);
		removeDups(root);
		printLlist(root);
	}
	
	private static void removeDups(LinkedListNode llist) {
		LinkedListNode root = llist;
		while(null != root) {
			LinkedListNode child = root.getNext();
			LinkedListNode prev = root;
			while(null != child) {
				if(root.getValue() == child.getValue()) {
					prev.setNext(child.getNext());
					child = prev.getNext();
				}
				else {
					prev = prev.getNext();
					child = child.getNext();
				}
			}
			root = root.getNext();
		}
	}
	
	private static void printLlist(LinkedListNode list) {
		LinkedListNode llist = list;
		while(null != llist) {
			System.out.print(llist.getValue() + "-");
			llist = llist.getNext();
		}
		System.out.println("");
	}
}

class LinkedListNode
{
	Integer value;
	LinkedListNode next;
	public LinkedListNode(Integer value) {
		this.value =value;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public LinkedListNode getNext() {
		return next;
	}
	public void setNext(LinkedListNode next) {
		this.next = next;
	}
}
