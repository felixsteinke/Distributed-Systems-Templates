package server;

import interfaces.Callback;
import interfaces.ServerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerSkeleton extends UnicastRemoteObject implements ServerInterface {

    Callback clientStub;

    protected ServerSkeleton(int port) throws RemoteException {
        super(port);
    }

    @Override
    public void sendString(String string) throws RemoteException {
        System.out.println(string);
        clientStub.call("Fuck u.");
    }

    @Override
    public void storeCallback(Callback clientStub) throws RemoteException {
        this.clientStub = clientStub;
    }
}
