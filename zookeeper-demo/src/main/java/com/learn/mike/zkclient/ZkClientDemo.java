package com.learn.mike.zkclient;

import com.learn.mike.util.StringConst;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2019-09-08 11:23
 **/
public class ZkClientDemo {

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient=new ZkClient(StringConst.zkAddress,5000);
        zkClient.createEphemeral("/TEST","ZZZZ".getBytes());
        zkClient.subscribeDataChanges("/TEST", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("data changed:"+s+"-"+o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("data deleted:"+s);
            }
        });
        List<String> list=zkClient.getChildren("/TEST");
        System.out.println(list);
        zkClient.delete("/TEST");
        TimeUnit.SECONDS.sleep(3);
    }
}
