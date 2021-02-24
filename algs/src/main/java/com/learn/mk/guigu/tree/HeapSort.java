package com.learn.mk.guigu.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        //int[] arr = {4, 6, 8, 5, 9};

        int[] arr =new int[20];
        for (int i = 0; i < 20; i++) {
            double random = Math.random()*1000;
            arr[i]=(int) random;
        }
        heapSort(arr);
        System.out.println("排序后的结果为："+Arrays.toString(arr));
    }


    //堆排序的方法
    public static void heapSort(int[] param) {

//        adjustHeap(param,1,param.length);
//        System.out.println("i=1的结果："+ Arrays.toString(param));
//        adjustHeap(param,0,param.length);
//        System.out.println("i=2的结果："+ Arrays.toString(param));

        for (int i = param.length / 2 - 1; i >= 0; i--) {
            adjustHeap(param, i, param.length);
        }
        System.out.println("大顶堆的结果："+ Arrays.toString(param));

        //将堆顶元素沉到数组末端，不断交换堆顶和末端元素，完成排序
        for (int j = param.length - 1; j > 0; j--) {
            int temp = param[j];
            param[j] = param[0];
            param[0] = temp;
            // 注意这里为什么是i==0，因为adjustHeap已经调用过一次了，
            // 现有数据已经是一个大顶堆了，不需要每次去调整整个数组，直接调整顶部就行
            adjustHeap(param, 0, j);

        }
    }

    /**
     * 将一个数组(二叉树)调整成大顶堆
     * {4,6,8,5,9}(i==1)=>{4,9,8,5,6}(i==0)=>{9,4,8,5,6}(i==1)=>{9,6,8,5,4}
     *
     * @param arr    入参数组
     * @param i      非叶子节点在数组中的索引index
     * @param length 对多少个元素进行调整
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int currentVal = arr[i];
        //遍历左子节点
        for (int j = 2 * i + 1; j < length; j = 2 * j + 1) {
            //左子节点小区右子节点,则将j指向右子节点(指向更大的数)
            if (j + 1 < length && arr[j] < arr[j + 1]) {
                j++;
            }
            //子节点更大
            if (currentVal < arr[j]) {
                arr[i] = arr[j];
                //注意继续循环比较，可能j下面还有子节点
                i = j;
            } else {
                // 注意此处break，因为比较顺序是从左至右，从下至上，
                // 所以不存在当前节点的子节点还更大的情况
                break;
            }
        }
        //for结束后，最大值，已经放到了顶部
        //将currentVal放回调整后的位置
        arr[i] = currentVal;
    }
}
