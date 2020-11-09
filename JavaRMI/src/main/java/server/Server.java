package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(23456);
            ServerSkeleton skeleton = new ServerSkeleton(23456);
            registry.rebind("serverName", skeleton);
            System.out.println("[INFO] Server is started!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
