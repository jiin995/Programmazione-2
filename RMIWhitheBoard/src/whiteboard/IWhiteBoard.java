package whiteboard;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import observer.IObserver;

public interface IWhiteBoard extends Remote {
	public boolean Subscribe(IObserver i) throws RemoteException;
	public void addForme(IShape i) throws RemoteException;
	public Vector<IShape> getForme() throws RemoteException;
}
