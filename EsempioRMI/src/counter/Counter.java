package counter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Counter extends UnicastRemoteObject implements ICounter  {
	
	private int counter;
	private Object mutex;
	
	public Counter() throws RemoteException{
		mutex=new Object();
		counter=0;
	}
	
	public void set() throws RemoteException {
		synchronized (mutex)  {
			counter++;
		}
	}
	
	public synchronized int get() throws RemoteException{
		int x;
		synchronized (mutex) {
			x=counter;
		}
		return x;
	}
	
	
}
