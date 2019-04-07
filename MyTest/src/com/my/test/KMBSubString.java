package com.my.test;

public class KMBSubString {
	public static void main(String a[]) {
		char text[] = "abcabcabcaby".toCharArray();
		char substr[] = "abcaby".toCharArray();
		int[] sint = new int[substr.length];
		for(int i = 0, j = 0; i < substr.length; i++) {
			if(i > 0 && substr[i] == substr[j]) {
				j++;
			}
			else {
				j = 0;
			}
			sint[i] = j;
		}
		for(int i = 0, j = 0; i < text.length; i++) {
			if(text[i] == substr[j]) {
				j++;
			}
			else {
				if(j > 0) {
					j = sint[j - 1];
					j++;
				} else {
					j = 0;
				}
			}
			if(j == substr.length)
				System.out.println("Matched");
		}
	}
}
