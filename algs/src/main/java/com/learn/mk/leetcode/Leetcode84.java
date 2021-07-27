package com.learn.mk.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode84 {
    public static void main(String[] args) {
        Leetcode84 leetcode84 = new Leetcode84();
        int[] param = {2,1,5,6,2,3};
        System.out.println(leetcode84.largestRectangleAreaByStack(param));

    }

    public int largestRectangleArea(int[] heights) {
        //遍历heights，当前元素作为构建形状的最低形状高度，由左和右分别判定可组成的长度。
        int areaMax = 0;
        for (int i = 0; i < heights.length; i++) {
            int length = 1;
            int leftIndex = i - 1;
            int rightIndex = i + 1;
            while (leftIndex >= 0 && heights[leftIndex] >= heights[i]) {
                length++;
                leftIndex--;
            }
            while (rightIndex < heights.length && heights[rightIndex] >= heights[i]) {
                length++;
                rightIndex++;
            }
            if (length * heights[i] > areaMax) {
                areaMax = length * heights[i];
            }
        }
        return areaMax;
    }


    public int largestRectangleAreaByStack(int[] heights) {
        //双端队列，当栈使用
        Deque<Integer> deque = new ArrayDeque<>(heights.length);
        int areaMax = 0;

        //使用heightsConvert填充头和尾
        int[] heightsConvert = new int[heights.length + 2];
        for (int i = 0; i < heights.length; i++) {
            heightsConvert[i + 1] = heights[i];
        }


        //遍历
        for (int i = 0; i < heightsConvert.length; i++) {
           while(heightsConvert[i]<heightsConvert[deque.peekLast()]){

           }
            deque.add(i);
        }
        return areaMax;

    }
}
