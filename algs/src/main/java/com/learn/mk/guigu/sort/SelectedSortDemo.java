package com.learn.mk.guigu.sort;

import java.util.Arrays;

/**
 * @program: learning-demo
 * @description: 选择排序
 * @author: mk_passby
 * @create: 2020-11-29 17:47
 **/
public class SelectedSortDemo {

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 20,5};
        selectedSort(arr);
        System.out.println("选择排序后的结果为：" + Arrays.toString(arr));


        TimeCostTools.testTimeCost((param)->selectedSort(param));
    }
    private static int[] selectedSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[minIndex] > a[j]) {
                    minIndex = j;
                }
            }
            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
        }
        return a;
    }
}


