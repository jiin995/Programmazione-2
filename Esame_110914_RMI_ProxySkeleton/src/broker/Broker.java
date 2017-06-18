package broker;

import java.rmi.RemoteException;
import java.rmi.registry.*;

public class Broker {

	public static void main(String[] args) {


		try{
			
			Registry registry=LocateRegistry.getRegistry();
			IBroker broker=new BrokerImpl();
			
			registry.rebind("Broker", broker);
			System.out.println("Broker registrato");
			
		}catch(RemoteException e){
			e.printStackTrace();
		}
	}

}
