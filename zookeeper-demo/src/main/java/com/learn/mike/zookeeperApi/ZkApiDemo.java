package com.learn.mike.zookeeperApi;

import com.learn.mike.util.StringConst;
import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2019-09-08 10:57
 **/
public class ZkApiDemo {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper(StringConst.zkAddress, 5000, new ZkWatcher());
        countDownLatch.await();
        zooKeeper.wait();
        System.out.println("---开始API操作");
        System.out.println(zooKeeper.exists("/test",true));
        if (zooKeeper.exists("/test",true)!=null) {
            zooKeeper.delete("/test",-1);
        }
        zooKeeper.create("/test","AA".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println(new String(zooKeeper.getData("/test",true,new Stat())));
        zooKeeper.setData("/test","BB".getBytes(),-1);
        System.out.println(new String(zooKeeper.getData("/test",true,new Stat())));
        zooKeeper.delete("/test",-1);
    }

    static class ZkWatcher implements Watcher {

        @Override
        public void process(WatchedEvent watchedEvent) {
            if (watchedEvent.getState()== KeeperState.SyncConnected){
                if (watchedEvent.getPath()==null&&watchedEvent.getType()== EventType.None){
                    countDownLatch.countDown();
                    System.out.println("连接成功-----");
                    return;
                }
                switch (watchedEvent.getType()){
                    case NodeDataChanged:
                        System.out.println("数据改变:"+watchedEvent.getPath());
                        break;
                    case NodeCreated:
                        System.out.println("数据新建:"+watchedEvent.getPath());
                        break;
                    case NodeDeleted:
                        System.out.println("数据删除:"+watchedEvent.getPath());
                        break;
                    case NodeChildrenChanged:
                        System.out.println("子NODE改变:"+watchedEvent.getPath());
                        break;
                }
            }
        }
    }
}
