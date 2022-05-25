package client_module;

import interface_module.ICallback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientStub extends UnicastRemoteObject implements ICallback {
    protected ClientStub(int port) throws RemoteException {
        super(port);
    }

    @Override
    public void call(String string) throws RemoteException {
        System.out.println("Received callback: " + string);
    }
}
