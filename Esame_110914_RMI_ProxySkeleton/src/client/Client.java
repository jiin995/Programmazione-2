package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;

import broker.IBroker;

public class Client {
	
	public static void main(String[] args) {
		
		try{
			Registry registry=LocateRegistry.getRegistry();
			
			IBroker broker=(IBroker)registry.lookup("Broker");
			
			broker.sottoponi(IBroker.ACQUISTA, 3);
			
			broker.sottoponi(IBroker.VENDI, 3);

		}catch(RemoteException e){
			e.printStackTrace();
		}catch(NotBoundException e){
			e.printStackTrace();
		}
		
	}
	

}
