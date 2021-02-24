    package com.learn.mk.leetcode;
    
    import java.util.Arrays;

    public class Leetcode42 {
        public static void main(String[] args) {
            Leetcode42 leetcode42 = new Leetcode42();
            int[] params = new int[]{-2,1,-3,4,-1,2,1,-5,4};
            System.out.println(leetcode42.maxSubArray(params));;
        }
    
        //剑指 Offer 42. 连续子数组的最大和
        public int maxSubArray(int[] nums) {
            int[] sums = Arrays.copyOf(nums, nums.length);
            int result=sums[0];
            for (int i = 1; i < nums.length; i++) {
                int j = i - 1;
                if (sums[j] + sums[i] > sums[i]) {
                    sums[i] = sums[j] + sums[i];
                }
                if (result<sums[i]){
                    result=sums[i];
                }
            }
            //System.out.println(Arrays.toString(sums));
            return result;
        }
    }
