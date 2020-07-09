package com.learn.mike.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @program: learning-demo
 * @description: 红水果
 * @author: mk_passby
 * @create: 2019-08-19 21:02
 **/
public class RedFruit extends UnicastRemoteObject implements Fruit {

    public RedFruit() throws RemoteException {
    }

    @Override
    public String getFruit() {
        return "this is a red fruit";
    }
}
