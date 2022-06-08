package com.wujingjun.Array;


public class Question1 {

    public static void main(String[] args) {
        Question1 question1 = new Question1();
        int i = question1.removeDuplicates(new int[]{1, 1, 2, 2, 3, 4, 5, 5});
        System.out.println(i);
    }

    public int removeDuplicates(int[] nums) {
        int index = 0;
        int record = nums[index];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != record) {
                index++;
                nums[index] = nums[i];
                record = nums[i];
            }
        }
        return index+1;
    }
}
