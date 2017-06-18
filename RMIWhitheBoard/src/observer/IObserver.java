package observer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObserver extends Remote {
	public boolean CallBack() throws RemoteException;
	public void attachObserver() throws RemoteException;
}
