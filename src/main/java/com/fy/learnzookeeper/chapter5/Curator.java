package com.fy.learnzookeeper.chapter5;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class Curator {
    public static void main(String[] args) throws Exception {
        // 启动zookeeper
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("101.43.163.223:2181", retry);
        client.start();

        // 创建节点
        client.create().forPath("/myznode", "123".getBytes());

        // 关闭
        client.close();
    }
}
