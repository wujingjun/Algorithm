package com.wujingjun.Array;

public class Question2 {

    public static void main(String[] args) {
        Question2 question2 = new Question2();
        int profit = question2.maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        System.out.println(profit);
    }

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
}
