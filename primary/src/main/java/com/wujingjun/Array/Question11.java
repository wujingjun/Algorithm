package com.wujingjun.Array;

public class Question11 {

    public void rotate1(int[][] matrix) {
        int length = matrix.length;
        int[][] newMatrix = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                newMatrix[j][length-i-1] = matrix[i][j];
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = newMatrix[i][j];
            }
        }
    }

    public void rotate2(int[][] matrix){
        
    }
}
