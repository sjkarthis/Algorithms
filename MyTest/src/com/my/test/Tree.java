package com.my.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tree {

	public static void main(String a[]) {
		int arr[] = { 10, 30, 35, 43, 50, 67, 80, 88, 91, 97 };
		Tree tree = new Tree();
		Node treeNode = tree.createBST(arr, 0, arr.length - 1);
		ArrayList<LinkedList<Node>> lists = new ArrayList<LinkedList<Node>>();
		tree.printBST(treeNode, lists, 0);
		tree.printLlist(lists);
		System.out.println(tree.isBalanced(treeNode));
		System.out.println(tree.isBST(treeNode));
	}

	private Node createBST(int[] arr, int start, int end) {
		if (end < start)
			return null;
		int mid = (start + end) / 2;
		Node midNode = new Node(arr[mid]);
		midNode.setLeft(createBST(arr, start, mid - 1));
		midNode.setRight(createBST(arr, mid + 1, end));
		return midNode;
	}

	private void printBST(Node treeNode, List<LinkedList<Node>> lists, int level) {
		if (null == treeNode) {
			return;
		}
		LinkedList<Node> list;
		if (lists.size() == level) {
			list = new LinkedList<Node>();
			lists.add(list);
		} else {
			list = lists.get(level);
		}
		list.add(treeNode);

		printBST(treeNode.getLeft(), lists, level + 1);
		printBST(treeNode.getRight(), lists, level + 1);
	}

	private void printLlist(ArrayList<LinkedList<Node>> lists) {
		for (LinkedList<Node> llist : lists) {
			int counter = 0;
			while (counter < llist.size()) {
				System.out.print(llist.get(counter).getValue() + "\t");
				counter++;
			}
			System.out.println("");
		}
	}

	int getHeight(Node root) {
		if (root == null)
			return 0; // Base case
		return Math.max(getHeight(root.getLeft()), getHeight(root.getRight())) + 1;
	}

	boolean isBalanced(Node root) {
		if (root == null)
			return true;// Base case

		int heightDiff = getHeight(root.getLeft()) - getHeight(root.getRight());
		if (Math.abs(heightDiff) > 1) {
			return false;
		} else {// Recurse
			return isBalanced(root.getLeft()) && isBalanced(root.getRight());
		}
	}
	
	boolean isBST(Node root) {
		if(root == null) 
			return true;
		boolean isBST = true;
		if(null != root.getLeft() && root.getLeft().getValue() < root.getValue())
			isBST = false;
		if(null != root.getRight() && root.getValue() > root.getRight().getValue())
			isBST = false; 
		if( isBST && isBST(root.getLeft()) && isBST(root.getRight())) {
			return true;
		}
		return false;
	}
}

class Node {
	private int value;
	private Node left;
	private Node right;

	public Node(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
}
