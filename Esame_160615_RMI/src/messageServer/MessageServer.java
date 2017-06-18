package messageServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MessageServer {
	
	public static void main(String args[]){
		
		try{
			
			Registry registry=LocateRegistry.getRegistry();
			IMessageServer msgServer=new MessageServerImpl();
			registry.rebind("MessageServer", msgServer);
			
			System.out.println(" [Message Server] Avviato e pronto");
			
		}catch(RemoteException e){
			e.printStackTrace();
		}
		
	}

}
