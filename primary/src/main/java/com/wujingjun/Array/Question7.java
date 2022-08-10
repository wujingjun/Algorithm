package com.wujingjun.Array;

public class Question7 {

    public static void main(String[] args) {
        int[] test = {9,9,9};
        Question7 question7 = new Question7();
        int[] ints = question7.plusOne(test);
        System.out.println("hello");
    }

    public int[] plusOne(int[] digits) {
        int length = digits.length;
        int index = length - 1;
        int increase = 1;

        while (index >= 0 && increase > 0) {
            int digit = digits[index];
            digit+=increase;
            increase = 0;
            if (digit == 10) {
                increase++;
                digits[index] = 0;
            } else {
                digits[index] = digit;
                break;
            }
            index --;
        }

        if (increase > 0) {
            int[] des = new int[length + 1];
            System.arraycopy(digits, 0, des, 1, length);
            des[0] = 1;
            return des;
        }
        return digits;
    }
}
