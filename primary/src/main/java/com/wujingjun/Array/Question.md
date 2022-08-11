# 数组

## 删除有序数组的重复项

给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。

由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。

将最终结果插入 nums 的前 k 个位置后返回 k 。

不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

示例 1：
```
输入：nums = [1,1,2]
输出：2, nums = [1,2,_]
解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
```

示例 2：
```
输入：nums = [0,0,1,1,1,2,2,3,3,4]
输出：5, nums = [0,1,2,3,4]
解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
```

解题方法
```java
public class Question1 {

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
```


## 买卖股票的最佳时机II

给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。

在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。

返回 你能获得的 最大 利润 。

示例 1：
```
输入：prices = [7,1,5,3,6,4]
输出：7
解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
总利润为 4 + 3 = 7 。
```

示例 2：
```
输入：prices = [1,2,3,4,5]
输出：4
解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
总利润为 4 。
```
示例 3：
```
输入：prices = [7,6,4,3,1]
输出：0
解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。
```


### 解法思路

```
股票在当天交易完之后只有两种情况
1. 没有股票
2. 有股票

会导致没有股票的情况是
1. 昨天本来就没有股票的情况下，继续不买股票，而这时候的利润是昨天没有买股票的时候的利润
2. 昨天在有股票的情况下，将股票卖出，而这时候的利润是昨天有股票的时候的利润，再加上今天卖出得到的利润

会导致有股票的情况是
1. 昨天本来就有股票，并且今天进行交易的时候不卖出，而这时候的利润是昨天有股票的时候的利润
2. 昨天在没有股票的情况下，买入股票，而这时候的利润是昨天没有股票的时候的利润，再减去今天买入股票花费的资金的利润

```

```java
public class Question2 {

    public int maxProfit2(int[] prices) {
        int[][] twoArray = new int[prices.length][2];
        twoArray[0][0] = 0;
        twoArray[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            twoArray[i][0] = Math.max(twoArray[i - 1][0], twoArray[i - 1][1] + prices[i]);
            twoArray[i][1] = Math.max(twoArray[i - 1][1], twoArray[i - 1][0] - prices[i]);
        }
        return Math.max(twoArray[prices.length - 1][0], twoArray[prices.length - 1][1]);
    }

}
```


## 旋转数组
给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。

 

示例 1:
```
输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右轮转 1 步: [7,1,2,3,4,5,6]
向右轮转 2 步: [6,7,1,2,3,4,5]
向右轮转 3 步: [5,6,7,1,2,3,4]
```

示例 2:
```
输入：nums = [-1,-100,3,99], k = 2
输出：[3,99,-1,-100]
解释:
向右轮转 1 步: [99,-1,-100,3]
向右轮转 2 步: [3,99,-1,-100]
```

解决思路
1. 环形后退
```java
class Question3 {
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
                index = (index + 1) % length;
                hold = nums[index];
                i--;
            } else {
                visited[index] = true;
                int temp = nums[index];
                nums[index] = hold;
                hold = temp;
            }
        }
    }
}
```

2. 翻转数组
```java
class Question3{
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
```


## 存在重复元素
给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。


示例 1：

```
输入：nums = [1,2,3,1]
输出：true
```

示例 2：

```
输入：nums = [1,2,3,4]
输出：false
```

示例 3：

```
输入：nums = [1,1,1,3,3,4,3,2,4,2]
输出：true
```

```java
class Question4{
    public boolean containsDuplicate(int[] nums) {
        return IntStream.of(nums).distinct().count() != nums.length;
    }

    public boolean containsDuplicate1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)){
                return true;
            }
            set.add(num);
        }
        return false;
    }
}
```

## 只出现一次的数字

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

输入: [2,2,1]
输出: 1
示例 2:

输入: [4,1,2,1,2]
输出: 4


```
异或运算（java表示为 ^ ），异或运算有以下三个性质
1. 任何数和0做异或运算，结果仍然是原来的数，即 a ^ 0 = a。
2. 任何数和其自身做异或运算，结果是0，即 a ^ a = 0。
3. 异或运算满足交换律和结合律。
```

解决思路

```java
class Questions5{

    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
```

## 两个数组的交集 II

给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。

 

示例 1：

输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2,2]
示例 2:

输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[4,9]



解决思路

1. 哈希表法
+ 首先遍历长度较小的数组，将数字和数字出现的次数存储其中
+ 最后遍历长度大的数组，如果遇到含有相同的数字，哈希表中对应的数字需要减去1，并将数字存入结果数组中

```java
package com.wujingjun.Array;

import java.util.*;
import java.util.stream.Collectors;

public class Question6 {

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length < nums2.length){
            this.intersect(nums2,nums1);
        }
        ArrayList<Integer> res = new ArrayList<>();

        Map<Integer,Integer> map = new HashMap<>();
        for (int i : nums1) {
            this.put(map,i);
        }

        for (int i : nums2) {
            this.compare(map,i,res);
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public void put(Map<Integer,Integer> map,Integer key){
        if (map.containsKey(key)){
            map.put(key,map.get(key)+1);
            return;
        }
        map.put(key,1);
    }

    public void compare(Map<Integer,Integer> map, Integer key, List<Integer> res){
        if (map.containsKey(key) && !map.get(key).equals(0)) {
            res.add(key);
            map.put(key,map.get(key)-1);
        }
    }

}

```

2. 使用双指针+排序

如果两个数组是有序的，则可以使用双指针的方法得到两个数组的交集。

首先对两个数组进行排序，然后使用两个指针遍历两个数组。

初始时，两个指针分别指向两个数组的头部。每次比较两个指针指向的两个数组中的数字，如果两个数字不相等，则将指向较小数字的指针右移一位，如果两个数字相等，将该数字添加到答案，并将两个指针都右移一位。当至少有一个指针超出数组范围时，遍历结束。

```java
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
```

## 加一
给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

 

示例 1：

输入：digits = [1,2,3]
输出：[1,2,4]
解释：输入数组表示数字 123。
示例 2：

输入：digits = [4,3,2,1]
输出：[4,3,2,2]
解释：输入数组表示数字 4321。
示例 3：

输入：digits = [0]
输出：[1]

解决思路
需要考虑是增加因子的问题，判断增加因子是0还是1即可

```java
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

```

## 移动零

给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

请注意 ，必须在不复制数组的情况下原地对数组进行操作。

 

示例 1:

输入: nums = [0,1,0,3,12]
输出: [1,3,12,0,0]
示例 2:

输入: nums = [0]
输出: [0]

解决思路

使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
注意到以下性质：
左指针左边均为非零数；
右指针左边直到左指针处均为零。
因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。


```java
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
```


## 两数之和
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

 

示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
示例 2：

输入：nums = [3,2,4], target = 6
输出：[1,2]
示例 3：

输入：nums = [3,3], target = 6
输出：[0,1]

```java
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
```



## 有效的数独
请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）


注意：

一个有效的数独（部分已被填充）不一定是可解的。
只需要根据以上规则，验证已经填入的数字是否有效即可。
空白格用 '.' 表示。


示例 1：


输入：board =
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：true
示例 2：

输入：board =
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：false
解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。

```java

```

