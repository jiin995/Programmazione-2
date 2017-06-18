package controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IController extends Remote {

	public boolean sendReading(int sensorID,int temp) throws RemoteException;
	
	public boolean subscribe(int sensorID,int p) throws RemoteException;
}
