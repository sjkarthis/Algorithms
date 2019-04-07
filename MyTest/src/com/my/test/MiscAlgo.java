package com.my.test;

public class MiscAlgo {

	public static void main(String a[]) {
		char str[] = {'M','r',' ','J','o','h','n',' ','S','m','i','t','h',' ',' ',' ',' ',' ',' '};
		replaceSpaces(str,13);
		
		oneUpdateStr("pale", "ple");
		oneUpdateStr("pales", "pale");
		oneUpdateStr("pale", "bale");
		oneUpdateStr("pale", "bae");
	}

	public static void replaceSpaces(char[] str, int trueLength) {
		int spaceCount = 0, index, i = 0;
		for (i = 0; i < trueLength; i++) {
			if (str[i] == ' ') {
				spaceCount++;
			}
		}
		
		index = trueLength + spaceCount * 2;
		if (trueLength < str.length)
			str[trueLength] = '\0';
		for (i = trueLength - 1; i >= 0; i--) {
			if (str[i] == ' ') {
				str[index - 1] = '0';
				str[index - 2] = '2';
				str[index - 3] = '%';
				index = index - 3;
			} else {
				str[index - 1] = str[i];
				index--;
			}
		}
		
		System.out.println(new String(str));
	}
	
	public static void oneUpdateStr(String str1, String str2) {
		if((str1.length() - str2.length()) > 1)
				System.out.println("Not Matching");
		
		String shortStr, longStr;
		if(str1.length() > str2.length()) {
			longStr = str1;
			shortStr = str2;
		}
		else {
			longStr = str2;
			shortStr = str1;
		}
		
		int i = 0 , j = 0 ;
		boolean diffFound = false;
		
		while (i < longStr.length() && j < shortStr.length()) {
			if (longStr.charAt(i) != shortStr.charAt(j)) {
				if(diffFound) {
					System.out.println("Not Matching");
					break;
				}
				if(i != j) {
					System.out.println("Not Matching");
					break;
				}
				if(longStr.length() == shortStr.length()) {
					diffFound = true;
					j++;
				}
				i++;
			}
			else {
				i++;
				j++;
			}
		}
	}
}
