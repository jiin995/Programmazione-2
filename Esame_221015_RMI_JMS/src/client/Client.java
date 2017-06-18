package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import frontend.IFrontend;


public class Client {
	
	public static void main(String args[]){
		
	    System.setProperty("java.rmi.server.codebase","file://./");
		
		try{
			Registry registry=LocateRegistry.getRegistry();
			IFrontend frontend=(IFrontend)registry.lookup("Frontend");
			
			ClientThread thread[]=new ClientThread [10];

			for(int i=0;i<10;i++){
				thread[i]=new ClientThread(frontend, i);
				thread[i].start();
				
			}
			
		}catch(NotBoundException e){
			e.printStackTrace();
		}catch(RemoteException e){
			e.printStackTrace();
		}
	}

}
