package com.learn.mk.guigu.sort;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

/**
 * @program: learning-demo
 * @description: 冒泡排序
 * @author: mk_passby
 * @create: 2020-11-29 16:16
 **/
public class BubbleDemo {

    /**
     * 思路： 第一次排序，把最大的数放到最后面 第二次排序，把第二大的数放到倒数第二位 ....
     */
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 20};
        bubbleSort(arr);
        System.out.println("冒泡排序后的结果为：" + Arrays.toString(arr));

        //测试耗时
        TimeCostTools.testTimeCost((param)->bubbleSort(param));
    }

    private static void bubbleSort(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            //第一次需要遍历arr.length-0-1次
            //第二次需要遍历arr.length-1-1次，因为最后一位已经有序
            //第二次需要遍历arr.length-2-1次，因为最后两位已经有序
            for (int j = 0; j < arr.length - i - 1; j++) {
                //如果前面比后面大，就交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
