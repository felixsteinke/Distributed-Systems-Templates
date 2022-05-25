package client;

import interfaces.ConnectionInfo;
import interfaces.ServerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Client {
    private static final Logger logger = Logger.getLogger(Client.class.getName());

    public static void main(String[] args) {
        try {
            ServerInterface server = connectClientToServer();
            runClientTask(server);
        } catch (RemoteException | NotBoundException e) {
            logger.severe("Failed to connect Client with Server!\n");
            e.printStackTrace();
        }
    }

    private static ServerInterface connectClientToServer() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(ConnectionInfo.SERVER_PORT);
        ServerInterface server = (ServerInterface) registry.lookup(ConnectionInfo.SERVER_NAME);

        ClientStub clientStub = new ClientStub(ConnectionInfo.CLIENT_PORT);
        server.storeCallback(clientStub);
        return server;
    }

    private static void runClientTask(ServerInterface server) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                server.sendString("Greetings from Client!");
            } catch (RemoteException e) {
                logger.severe("Failed to send message!");
                e.printStackTrace();
            }
        }, 0, 5L, TimeUnit.SECONDS);
    }
}
