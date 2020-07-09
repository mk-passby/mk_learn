package com.learn.mike.curator;

import com.learn.mike.util.StringConst;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2019-09-08 12:38
 **/
public class CuratorDemo {

    public static void main(String[] args) {
        CuratorFramework curatorFrameWork = CuratorFrameworkFactory
            .newClient(StringConst.zkAddress, new ExponentialBackoffRetry(1000, 3));
        curatorFrameWork.start();//启动链接



    }

}
