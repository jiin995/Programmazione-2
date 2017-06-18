package counter;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICounter extends Remote  {
	
	public void set() throws RemoteException;
	public int  get() throws RemoteException;

}
