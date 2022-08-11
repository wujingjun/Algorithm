package com.wujingjun.Array;

import java.util.*;

public class Question9 {

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        Question9 question9 = new Question9();
        int[] ints = question9.twoSum(arr, 5);
        System.out.println("hello");
    }

    public int[] twoSum(int[] nums, int target) {
        List<Integer> res = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])){
                res.add(map.get(target-nums[i]));
                res.add(i);
            }
            map.put(nums[i],i);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
