package com.wujingjun.Array;

public class Question5 {

    public static void main(String[] args) {
        int[] nums = {1,1,2,2,3,4,5,4,5};
        Question5 question5 = new Question5();
        int i = question5.singleNumber(nums);
        System.out.println(i);
    }

    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
