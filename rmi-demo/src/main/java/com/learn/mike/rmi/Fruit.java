package com.learn.mike.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Fruit extends Remote {

    public String getFruit() throws RemoteException;
}
