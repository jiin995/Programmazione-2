package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import gestore.IGestore;

public class Client {
	
	public static void main(String args[]){
		
		ClientThread thread[]=new ClientThread[10];
		
		try{
			Registry registry=LocateRegistry.getRegistry();
	
			IGestore gestore=(IGestore)registry.lookup("Gestore");
		
			for(int i=0;i<10;i++){
				thread[i]=new ClientThread(gestore, 5,i);
				thread[i].start();
			}
		
			for(int i=0;i<10;i++){
				thread[i].join();
			}
		}catch(RemoteException e){
			e.printStackTrace();
		}catch(NotBoundException e){
			e.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
