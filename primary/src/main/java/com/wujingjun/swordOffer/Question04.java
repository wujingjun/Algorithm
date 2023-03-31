package com.wujingjun.swordOffer;

public class Question04 {

    public static void main(String[] args) {
        int[][] matrix =
//                {
//                {1,4,7,11,15},
//                {2,5,8,12,19},
//                {3,6,9,16,22},
//                {10,13,14,17,24},
//                {18,21,23,26,30}
//        };
                {{-5}};
        boolean numberIn2DArray = findNumberIn2DArray(matrix, -10);

        System.out.println(numberIn2DArray);
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length < 1) return false;
        int y = matrix[0].length-1, x = 0, height = matrix.length , width = matrix[0].length;

        while (x < height && y >= 0) {
            if (target == matrix[x][y]) return true;
            if (matrix[x][y] > target)
                y--;
             else if (matrix[x][y] < target) x++;
        }

        return false;
    }
}
