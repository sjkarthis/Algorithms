package com.my.test;

public class UniqueString {
	public static void main(String a[]) {
		// System.out.println(isUniqueString("abcde"));
		System.out.println(isUniqueString("abc"));
	}

	private static boolean isUniqueString(String str) {
		int checker = 0;
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i) - 'a';
			if ((checker & (1 << val)) > 0) {
				return false;
			}
			checker |= (1 << val);
		}
		return true;
	}
}
