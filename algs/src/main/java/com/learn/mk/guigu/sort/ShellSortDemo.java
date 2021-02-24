package com.learn.mk.guigu.sort;

import java.util.Arrays;

/**
 * @program: learning-demo
 * @description: 希尔排序
 * @author: mk_passby
 * @create: 2020-11-29 17:57
 **/
public class ShellSortDemo {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        //shellSortByChange(arr);
        System.out.println("ShellSort:" + Arrays.toString(arr));
//第一轮排序
        System.out.println("希尔排序第一轮后~~");

        //因为第一轮排序，将10个数据分成了5个组， 所以 i = 5 ; i < arr.length
        for (int i = 5; i < arr.length; i++) {
            // 遍历各组中所有的元素(共5组，每组2个元素), 步长为5
            for (int j = i - 5; j >= 0; j -= 5) {
                // 如果当前元素大于加上步长后的那个元素
                if (arr[j] > arr[j + 5]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
//
        System.out.println(Arrays.toString(arr));
//        //第二轮排序
        System.out.println("希尔排序第二轮后~~");

        //因为第二轮排序，将10个数据分成了5/2个组（看ppt图）， 所以 i = 2 ; i < arr.length
        for (int i = 2; i < arr.length; i++) {
            // 遍历各组中所有的元素(共2组)
            for (int j = i - 2; j >= 0; j -= 2) {
                // 如果当前元素大于加上步长后的那个元素
                if (arr[j] > arr[j + 2]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
//
//        //第三轮排序
//        System.out.println("希尔排序第三轮后~~");
//        //因为第三轮排序，将10个数据分成了2/2个组（看ppt图）， 所以 i = 1 ; i < arr.length
//        for (int i = 1; i < arr.length; i++) {
//            // 遍历各组中所有的元素(共1组)
//            for (int j = i - 1; j >= 0; j -= 1) {
//                // 如果当前元素大于加上步长后的那个元素
//                if (arr[j] > arr[j + 1]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
//
//        System.out.println(Arrays.toString(arr));

    }

    /**
     * 希尔排序交换法
     */
    private static void shellSortByChange(int[] arr) {
        //遍历步长，按每次除以2遍历
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素开始全部遍历
            for (int i = gap; i < arr.length; i++) {
                //当前元素以gap的步长进行遍历
                //与前面的元素比较，如果当前元素更小，则替换(此处类似冒泡的替换，即所谓的交换法)
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 希尔排序: 对有序序列在插入时采用移动法
     */
    public static void shellSort2(int[] arr) {
        // 增量gap，并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在组进行直接插入排序操作
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 移动法
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }
}


