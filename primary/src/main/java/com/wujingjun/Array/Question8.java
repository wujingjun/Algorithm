package com.wujingjun.Array;

public class Question8 {

    public static void main(String[] args) {
        int[] nums = {1,0,2,3,0,0};
        Question8 question8 = new Question8();
        question8.moveZeroes(nums);
        System.out.println("hello");
    }

    public void moveZeroes(int[] nums) {
        int length = nums.length, left = 0, right = 0;
        while (right < length) {
            //如果两个指针指向的数都不是0，那么这两个指针就是要同时走的
            if (nums[right] != 0) {
                swap(nums,left,right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums,int left,int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
