package com.wujingjun.Array;

public class Question2 {

    public static void main(String[] args) {
        Question2 question2 = new Question2();
        int profit = question2.maxProfit2(new int[]{7, 1, 5, 3, 6, 4});
        System.out.println(profit);
    }

    /**
     * 自己思考解法
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int start = 0,profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (i == prices.length-1 && prices[i] > prices[i-1]){
                profit += prices[i] - prices[start];
            }
            if (prices[i] <= prices[i-1]) {
                profit += prices[i-1] - prices[start];
                start = i;
            }
        }
        return profit;
    }

    /**
     * 动态规划解法
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int[][] twoArray = new int[prices.length][2];
        twoArray[0][0] = 0;
        twoArray[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            twoArray[i][0] = Math.max(twoArray[i-1][0],twoArray[i-1][1]+prices[i]);
            twoArray[i][1] = Math.max(twoArray[i-1][1],twoArray[i-1][0]-prices[i]);
        }
        return Math.max(twoArray[prices.length-1][0],twoArray[prices.length-1][1]);
    }
}
