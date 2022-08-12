package com.wujingjun.Array;

public class Question10 {

    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                char c = board[i][j];
                if (c != '.') {
                    //这个索引表示数组的下标
                    int index = c-'0'-1;
                    rows[i][index]++;
                    columns[j][index]++;
                    subboxes[i/3][j/3][index]++;
                    if (rows[i][index]>1 || columns[j][index]>1 || subboxes[i/3][j/3][index]>1){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
