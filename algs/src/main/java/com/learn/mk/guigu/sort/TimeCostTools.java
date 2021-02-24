package com.learn.mk.guigu.sort;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-11-29 17:33
 **/
public abstract class TimeCostTools {

    /**
     * 默认80000个int的耗时
     */
    public static void testTimeCost(Consumer<int[]> sortConsumer) {
        testTimeCost(sortConsumer, 80000);
    }


    public static void testTimeCost(Consumer<int[]> sortConsumer, int arraySize) {
        System.out.println();
        System.out.println("TimeCostTools开始测试耗时...");
        int[] arr = new int[arraySize];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0,800000) 的一个数
        }
        System.out.println("排序前~");
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("排序前时间=" + simpleDateFormat.format(now)); // 输出时间
        sortConsumer.accept(arr);
        System.out.println("排序后~");
        Date now2 = new Date();
        System.out.println("排序后时间=" + simpleDateFormat.format(now2)); // 输出时间

    }

}
