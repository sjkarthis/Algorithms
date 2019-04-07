package com.my.test;

public class MatrixRotation {
	public static void main(String a[]) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		int temp, temp1;
		for (int i = 0, x = matrix.length - 1; i < matrix.length - 1; i++, x--) {
			for (int j = i, y = x; j < x; j++, y--) {
				temp = matrix[j][x];
				matrix[j][x] = matrix[i][j];
				temp1 = matrix[x][y];
				matrix[x][y] = temp;
				temp = matrix[y][i];
				matrix[y][i] = temp1;
				matrix[i][j] = temp;
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
}
