package client;

import interfaces.Callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientStub extends UnicastRemoteObject implements Callback {
    protected ClientStub(int port) throws RemoteException {
        super(port);
    }

    @Override
    public void call(String string) throws RemoteException {
        System.out.println(string);
    }
}
