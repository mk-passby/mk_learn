package com.learn.mk.guigu.sort;

import java.util.Arrays;

/**
 * @program: learning-demo
 * @description: 基数排序
 * @author: mk_passby
 * @create: 2020-11-29 21:57
 **/
public class RadixSortDemo {

    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 基数排序
     */
    public static void radixSort(int[] arr) {
        // 假定arr[0] 是最大数
        // 1. 通过遍历arr, 找到数组中真正最大值
        // 2. 目的是确定要进行多少轮排序
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 计算最大数字是几位数
        int maxLength = (max + "").length();
        // 定义一个二维数组， 就是10个桶
        // 1. 该二维数组有10个一维数组 0-9
        // 2. 为了防止溢出，每个一维数组(桶)，大小定为 arr.length
        // 3. 很明确, 基数排序是空间换时间
        int[][] bucket = new int[10][arr.length];
        // 用于记录在每个桶中，实际存放了多少个数据,这样才能正确的取出
        int[] bucketElementCounts = new int[10];
        // 根据最大长度的数决定比较的次数
        // 1. 大循环的次数就是 最大数有多少位,前面分析过
        // 2. n = 1, n *= 10 是为了每轮循环排序时，分别求出各个元素的 个位，十位，百位，千位 ...
        //    就是一个小算法
        // 3. 这个基础排序，完全可以使用 冒泡分步写代码来完成，比较简单!!
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 把每一个数字分别计算本轮循环的位数的值,比如第1轮是个位...
            for (int j = 0; j < arr.length; j++) {
                // 计算
                int digitOfElement = arr[j] / n % 10;
                // 把当前遍历的数据放入指定的数组中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                // 记录数量
                bucketElementCounts[digitOfElement]++;
            }
            // 记录取的元素需要放的位置
            int index = 0;
            // 把各个桶中(10个桶)存放的数字取出来, 放入到arr中
            for (int k = 0; k < bucketElementCounts.length; k++) {
                // 如果这个桶中，有数据才取，没有数据就不取了
                if (bucketElementCounts[k] != 0) {
                    // 循环取出元素
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        // 取出元素
                        arr[index++] = bucket[k][l];
                    }
                    // 把这个桶的对应的记录的数据个数置为0,注意,桶本身数据(前面存的数据还在)
                    bucketElementCounts[k] = 0; //
                }
            }
        }
    }

}
