package com.wujingjun.String;

public class Question2 {

    public static void main(String[] args) {
        Question2 question2 = new Question2();
        int reverse = question2.reverse(-123);
        System.out.println(reverse);
    }

    public int reverse(int x) {
        int digit, res = 0;
        while (x!=0){
            if (res < Integer.MIN_VALUE/10 || res > Integer.MAX_VALUE/10){
                return 0;
            }
            digit = x % 10;
            x /= 10;
            res = res * 10  + digit;
        }
        return res;
    }
}
