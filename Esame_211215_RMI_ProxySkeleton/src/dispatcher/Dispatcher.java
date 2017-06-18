package dispatcher;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IDispatcher;

public class Dispatcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Registry registry=LocateRegistry.getRegistry();
			
			IDispatcher dispatcher=new DispatcherImpl(3);
			registry.rebind("Dispatcher", dispatcher);
			
			System.out.println("Dispatcher UP and RUNNING");

		}catch(RemoteException e){
			e.printStackTrace();
		}
	}

}
