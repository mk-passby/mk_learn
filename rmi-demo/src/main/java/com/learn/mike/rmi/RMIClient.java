package com.learn.mike.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2019-08-19 22:21
 **/
public class RMIClient {

    public static void main(String[] args)
        throws RemoteException, NotBoundException, MalformedURLException {
        Fruit fruit= (Fruit) Naming.lookup("rmi://localhost:8001/test");
        System.out.println(fruit.getFruit());
    }

}
