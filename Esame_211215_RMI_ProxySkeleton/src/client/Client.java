package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IDispatcher;

public class Client {
	
	public static void main(String args[]){
		
		try{
			Registry registry=LocateRegistry.getRegistry();
			
			IDispatcher dispatcher=(IDispatcher)registry.lookup("Dispatcher");
			
			ClientThread c[]=new ClientThread[15];
			
			for(int i=0;i<15;i++){
				c[i]=new ClientThread(dispatcher, i);
				c[i].start();
			}
			
		}catch(RemoteException e){
			e.printStackTrace();
		}catch(NotBoundException e){
			e.printStackTrace();
		}
		
	}

}
