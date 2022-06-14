package com.wujingjun.Array;

import java.util.Arrays;

public class Question3 {

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8};
        Question3 question3 = new Question3();
        question3.rotate1(array,3);
        System.out.println(Arrays.toString(array));
    }

    public void rotate(int[] nums, int k) {
        //首先将数组看成一个环，所有的值都向后退k格
        int length = nums.length;
        int index = 0;
        int hold = nums[0];
        boolean[] visited = new boolean[length];
        //k也可以看作是一个步长，如果步长增加到一个已经访问过的位置
        //那么就当作这次遍历是无效的，最终的目的是有效遍历数组元素个数的次数才是正确的
        //那么i其实是没有任何实际意义的
        for (int i = 0; i < nums.length; i++) {
            index = (index + k) % length;
            if (visited[index]) {
                index = (index+1) % length;
                hold = nums[index];
                i--;
            } else{
                visited[index] = true;
                int temp = nums[index];
                nums[index] = hold;
                hold = temp;
            }
        }
    }

    public void rotate1(int[] nums, int k) {
        int length = nums.length;
        k %= nums.length;
        exchange(nums,0,length-1);
        exchange(nums,0,k-1);
        exchange(nums,k,length-1);
    }

    public void exchange(int[] nums,int start,int end){
        while (start <= end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
