package publisher;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import messageServer.IMessageServer;

public class Publisher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			
			Registry registry=LocateRegistry.getRegistry();
			
			IMessageServer msgServer=(IMessageServer)registry.lookup("MessageServer");

			for(int i=0;i<10;i++){
				PublisherThread thread=new PublisherThread(msgServer, args[0], String.valueOf(i));
				thread.start();
			}
			
		}catch(RemoteException e){
			e.printStackTrace();
		}catch(NotBoundException e){
			e.printStackTrace();
		}

	}

}
