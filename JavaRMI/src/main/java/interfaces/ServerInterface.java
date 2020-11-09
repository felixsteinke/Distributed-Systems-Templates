package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    void sendString(String string) throws RemoteException;

    void storeCallback(Callback clientStub) throws RemoteException;
}
