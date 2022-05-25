package interface_module;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICallback extends Remote {

    void call(String string) throws RemoteException;
}
