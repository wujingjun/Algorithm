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