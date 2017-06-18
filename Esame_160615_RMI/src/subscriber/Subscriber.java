package subscriber;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import messageServer.IMessageServer;

public class Subscriber {

	public static void main(String args[]){

		if(args.length<1){
			System.out.println("Utilizzo 'java subscriber.Subscriber ''Topic'' '");
		}else{
			try{
				
				Registry registry=LocateRegistry.getRegistry();
				
				IMessageServer msgServer=(IMessageServer)registry.lookup("MessageServer");	
				ISubscriber subscriber=new SubscriberImpl(msgServer);
				
				subscriber.subscribe(args[0], subscriber);
								
			}catch(RemoteException e){
				e.printStackTrace();
			}catch(NotBoundException e){
				e.printStackTrace();
			}
		}
		
	}
	
}
