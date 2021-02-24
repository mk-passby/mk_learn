package com.learn.mk.guigu.sort;

import java.util.Arrays;

/**
 * @program: learning-demo
 * @description: 归并排序
 * @author: mk_passby
 * @create: 2020-11-29 21:25
 **/
public class MergeSortDemo {

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        //在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);

        System.out.println(Arrays.toString(temp));


    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);// 左边归并排序，使得左子序列有序
            mergeSort(arr, mid + 1, right, temp);// 右边归并排序，使得右子序列有序
            merge(arr, left, mid, right, temp);// 将两个有序子数组合并操作
        }
    }

    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; //左序列指针/索引
        int j = mid + 1;//右序列指针
        int t = 0; //临时数组指针
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {

                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        while (i <= mid) {//将左边剩余元素填充进temp中
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {//将右序列剩余元素填充进temp中

            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        //因为left 是val 因此使用一个临时变量
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }

}
