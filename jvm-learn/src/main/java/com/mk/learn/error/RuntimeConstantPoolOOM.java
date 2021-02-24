package com.mk.learn.error;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: learning-demo
 * @description: 运行时常量池异出
 * @author: mk_passby
 * @create: 2020-07-28 22:31
 **/
public class RuntimeConstantPoolOOM {


    /**注：1.8参数已失效
     * VM ARGS:-XX:PermSize=10M -XX:MaxPermsSize=10M
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i).intern());
        }

    }
}
