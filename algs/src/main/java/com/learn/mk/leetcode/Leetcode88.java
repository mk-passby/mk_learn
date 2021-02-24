package com.learn.mk.leetcode;

import java.util.Arrays;

public class Leetcode88 {
    public static void main(String[] args) {
        Leetcode88 leetcode88 = new Leetcode88();
//        int[] num1 = new int[]{1, 2, 3, 0, 0, 0};
//        int[] num2 = new int[]{2, 5, 6};
//        leetcode88.merge(num1, 3, num2, 3);
//        System.out.println(Arrays.toString(num1));
        int[] num1 = new int[]{2,0};
        int[] num2 = new int[]{1};
        leetcode88.merge(num1, 1, num2, 1);
        System.out.println(Arrays.toString(num1));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int lastIndex = m + n - 1;
        while (n>0) {
            if (m==0||nums1[m - 1] <= nums2[n - 1]){
                nums1[lastIndex]=nums2[--n];
            }else{
                nums1[lastIndex]=nums1[--m];
            }
            lastIndex--;
        }

    }
}
