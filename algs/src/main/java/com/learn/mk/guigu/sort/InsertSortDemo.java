package com.learn.mk.guigu.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * @program: learning-demo
 * @description: 插入排序
 * @author: mk_passby
 * @create: 2020-11-29 17:52
 **/
public class InsertSortDemo {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
         int[] arr = {101, 34, 119, 1};

        // 创建一个80000个随机数据的数组
        // 使用插入排序，大概3秒和选择排序差不多..
        insertSort(arr);
        System.out.println("插入排序后，得到数组为："+Arrays.toString(arr));

    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1; // 表示有序表的最后这个元素的下标
            // 还没找到位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex -= 1;
            }
            // 退出while 说明插入的位置就找到了
            arr[insertIndex + 1] = insertVal;
        }
    }
}
