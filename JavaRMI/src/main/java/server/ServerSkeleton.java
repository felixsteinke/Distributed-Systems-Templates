package server;

import interfaces.Callback;
import interfaces.ServerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerSkeleton extends UnicastRemoteObject implements ServerInterface {

    private Callback clientStub;

    protected ServerSkeleton(int port) throws RemoteException {
        super(port);
    }

    @Override
    public void sendString(String string) throws RemoteException {
        System.out.println("Received message: " + string);

        String acknowledgement = "Server received the message!";
        clientStub.call(acknowledgement);
    }

    @Override
    public void storeCallback(Callback clientStub) throws RemoteException {
        this.clientStub = clientStub;

        String acknowledgement = "Client is registered for callback!";
        clientStub.call(acknowledgement);
    }
}
