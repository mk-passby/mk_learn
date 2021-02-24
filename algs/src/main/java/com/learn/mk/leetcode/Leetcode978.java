package com.learn.mk.leetcode;

import java.util.Arrays;

public class Leetcode978 {
    public static void main(String[] args) {
        Leetcode978 leetcode978 = new Leetcode978();
        //int[] arr = {9, 4, 2, 10, 7, 8, 8, 1, 9};
        int[] arr = {0, 1, 1, 0, 1, 0, 1, 1, 0, 0};


        System.out.println(leetcode978.maxTurbulenceSize(arr));
    }

    //
    public int maxTurbulenceSize(int[] arr) {
        return Math.max(extracted(arr,0,1),extracted(arr,1,0));
    }

    private int extracted(int[] arr,int a,int b) {
        int[] dp = new int[arr.length + 1];
        Arrays.fill(dp, 1);
        int result = 1;
        for (int k = 0; k < arr.length - 1; k++) {
            //偶数
            if (k % 2 == a && arr[k] < arr[k + 1]) {
                dp[k + 1] = dp[k] + 1;
            }
            //奇数
            if (k % 2 == b && arr[k] > arr[k + 1]) {
                dp[k + 1] = dp[k] + 1;
            }
            if (dp[k + 1] > result) {
                result = dp[k + 1];
            }
        }
        return result;
    }
}
