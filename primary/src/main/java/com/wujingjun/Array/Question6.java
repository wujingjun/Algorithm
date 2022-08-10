package com.wujingjun.Array;

import java.util.*;
import java.util.stream.Collectors;

public class Question6 {

    public int[] intersectWay1(int[] nums1, int[] nums2) {
        if (nums1.length < nums2.length) {
            this.intersectWay1(nums2, nums1);
        }
        ArrayList<Integer> res = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            this.put(map, i);
        }

        for (int i : nums2) {
            this.compare(map, i, res);
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public void put(Map<Integer, Integer> map, Integer key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + 1);
            return;
        }
        map.put(key, 1);
    }

    public void compare(Map<Integer, Integer> map, Integer key, List<Integer> res) {
        if (map.containsKey(key) && !map.get(key).equals(0)) {
            res.add(key);
            map.put(key, map.get(key) - 1);
        }
    }

    public int[] intersectWay2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> res = new ArrayList<>();
        int index1 = 0, index2 = 0;
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] == nums2[index2]) {
                res.add(nums1[index1]);
                index1++;
                index2++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else if (nums1[index1] < nums2[index2]) {
                index1++;
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
