package interfaces.module;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Callback extends Remote {

    void call(String string) throws RemoteException;
}
