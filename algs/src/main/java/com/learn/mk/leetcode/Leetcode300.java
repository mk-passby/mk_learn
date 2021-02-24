package com.learn.mk.leetcode;

import java.util.Arrays;

public class Leetcode300 {
    public static void main(String[] args) {
        Leetcode300 leetcode300 = new Leetcode300();
        //int[] param=new int[]{10,9,2,5,3,7,101,18};
        int[] param=new int[]{10,9,2,5,3,7,101,18,19,20};
        System.out.println(leetcode300.lengthOfLIS(param) );;
    }

    public int lengthOfLIS(int[] nums) {
        int[] countArray=new int[nums.length];
        Arrays.fill(countArray,1);
        int result=1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i]>nums[j]&&countArray[i]<=countArray[j]){
                    countArray[i]=countArray[j]+1;
                    if (countArray[i]>result){
                        result=countArray[i];
                    }
                }
            }
        }
        return result;
    }
}
