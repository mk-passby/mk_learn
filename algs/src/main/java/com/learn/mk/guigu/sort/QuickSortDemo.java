package com.learn.mk.guigu.sort;

import java.util.Arrays;

/**
 * @program: learning-demo
 * @description:快速排序
 * @author: mk_passby
 * @create: 2020-11-29 20:36
 **/
public class QuickSortDemo {

    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("快排结果：" + Arrays.toString(arr));

    }

    private static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        //中轴值
        int pivot = arr[(left + right) / 2];
        int temp;
        //比pivot大的放右边，小的放左边
        while (l < r) {
            while (arr[l] < pivot) {
                ++l;
            }
            while (arr[r] > pivot) {
                --r;
            }
            //if (l>=r)说明左边全部小于，右边全部大于，已经满足了
            if (l >= r) {
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 如果交换后，发现这个值和pivot相等，可以r--,前移一步
            if (arr[l] == pivot) {
                r -= 1;
            }
            // 如果交换后，发现这个值和pivot相等，可以l--,后移一步
            if (arr[r] == pivot) {
                l += 1;
            }

        }
// 如果 l==r 还可以让 l++， r--下.
        if (l == r) {
            l += 1;
            r -= 1;
        }
        // 向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        // 向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
