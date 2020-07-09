package com.learn.mike.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2019-09-04 23:13
 **/
public class RMIServer {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(8001);
            Fruit fruit = new RedFruit();
            Naming.bind("rmi://localhost:8001/test", fruit);
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("RMI SERVER START----");

    }

}

