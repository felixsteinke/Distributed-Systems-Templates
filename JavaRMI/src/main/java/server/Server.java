package server;

import interfaces.ConnectionInfo;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Logger;

public class Server {
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(ConnectionInfo.SERVER_PORT);
            ServerSkeleton skeleton = new ServerSkeleton(ConnectionInfo.SERVER_PORT);
            registry.rebind(ConnectionInfo.SERVER_NAME, skeleton);

            logger.info("Server is started!");
        } catch (RemoteException e) {
            logger.severe("Failed to start Server!");
            e.printStackTrace();
        }
    }
}
