package client;

import interfaces.ServerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    public void run() {
        try {
            Registry registry = LocateRegistry.getRegistry(23456);
            ServerInterface serverSkeleton = (ServerInterface) registry.lookup("serverName");

            ClientStub clientStub = new ClientStub(34567);
            serverSkeleton.storeCallback(clientStub);
            serverSkeleton.sendString("Client: Hello Server!");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
