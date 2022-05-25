package interface_module;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {

    void sendString(String string) throws RemoteException;

    void storeCallback(ICallback clientStub) throws RemoteException;
}
