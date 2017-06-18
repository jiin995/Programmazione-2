package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDispatcher extends Remote {
	
	public void sottoscrivi(String address, int port) throws RemoteException;

	public boolean sendCmd(String command) throws RemoteException;

}
