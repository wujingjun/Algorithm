package com.wujingjun.swordOffer;

import java.util.Arrays;

public class Qestion03 {

    public static void main(String[] args) {
//        int[] nums = {1,2,3,4,5,6,7,7};

        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        int repeatNumber = findRepeatNumberV2(nums);
        System.out.println(repeatNumber);
    }

    /**
     * 方案一：创建一个相同长度的空数组，并填满0，遍历数组，将遍历数字找到空数组所对应的索引位值置为1，如果遇到1则说明是重复数字，返回即可
     */
    public static int findRepeatNumber(int[] nums) {
        int[] temp = new int[nums.length];

        for (int num : nums) {
            if (temp[num] == 1) {
                return num;
            }
            temp[num] = 1;
        }
        return 0;
    }

    /**
     * 题目说明尚未被充分使用，即 在一个长度为 n 的数组 nums 里的所有数字都在 0 ~ n-1 的范围内 。 此说明含义：数组元素的 索引 和 值 是 一对多 的关系。
     * 因此，可遍历数组并通过交换操作，使元素的 索引 与 值 一一对应（即nums[i]=i ）。因而，就能通过索引映射对应的值，起到与字典等价的作用。
     */
    public static int findRepeatNumberV2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                } else {
                    int temp = nums[i];
                    nums[i] = nums[temp];
                    nums[temp] = temp;
                }
            }
        }
        return -1;
    }

    public static int findRepeatNumberV3(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i) {
                i++;
                continue;
            }
            if(nums[nums[i]] == nums[i]) return nums[i];
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }


}
