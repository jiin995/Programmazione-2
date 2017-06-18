package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import counter.*;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Registry registry=LocateRegistry.getRegistry();
			
			ICounter counter=new Counter();
			
			registry.rebind("counter",counter);
			
		}catch(RemoteException e){
			e.printStackTrace();
		}

	}

}
