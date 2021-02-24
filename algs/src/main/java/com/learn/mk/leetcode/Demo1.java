package com.learn.mk.leetcode;

import java.util.Arrays;

public class Demo1 {
    /*
    求某一个数组的最大升序子序列的个数
    如：[4,7,1,6,11,8,9]
    输出：4
     */
    public static void main(String[] args) {
        int[] params = {4, 7, 1, 6, 11, 8, 9, 13, 16};
        int result = getResult(params);
        System.out.println(result);
    }

    private static int getResult(int[] params) {
        //构建一个数组，存储每个index位置之前的最大升序子串个数，默认填充1
        int[] countArray = new int[params.length];
        Arrays.fill(countArray, 1);

        int result = Integer.MIN_VALUE;
        for (int i = 1; i < params.length; i++) {
            int maxCountI = 1;
            int temp = 0;
            for (int j = 0; j < i; j++) {
//                if (params[i] > params[j]) {
//                    if (countArray[j] + 1 > maxCountI) {
//                        countArray[i] = countArray[j] + 1;
//                    }
//                }
                temp=params[i] > params[j]?countArray[j]+1:temp;
                maxCountI =maxCountI<temp?temp:maxCountI;
            }
            countArray[i]=maxCountI;
            if (countArray[i] > result) {
                result = countArray[i];
            }
        }
        System.out.println(Arrays.toString(countArray));
        return result;
    }

}
