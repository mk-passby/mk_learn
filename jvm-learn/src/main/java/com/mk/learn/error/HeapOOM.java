package com.mk.learn.error;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-06-21 11:37
 **/
public class HeapOOM {

    static class OOMObject {
    }

    /**
     * VM Args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObject> oomObjectList=new ArrayList<>();
        while(true){
            oomObjectList.add(new OOMObject());
        }
    }
}
