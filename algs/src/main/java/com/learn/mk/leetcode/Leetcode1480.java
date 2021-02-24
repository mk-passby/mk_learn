package com.learn.mk.leetcode;

public class Leetcode1480 {
    public static void main(String[] args) {
        Leetcode1480 leetcode1480 = new Leetcode1480();
        int[] param = {3, 1, 2, 10, 1};
        leetcode1480.runningSum(param);
    }

    public int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] + nums[i];
        }
        return result;
    }


}
