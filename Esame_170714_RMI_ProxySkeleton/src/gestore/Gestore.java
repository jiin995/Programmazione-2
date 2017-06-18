package gestore;

import java.rmi.*;
import java.rmi.registry.*;

public class Gestore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			
			Registry registry=LocateRegistry.getRegistry();
			
			IGestore gestore=new GestoreImpl();
			
			registry.rebind("Gestore", gestore);
			System.out.println("Gestore pronto e in esecuzione");
			
		}catch(RemoteException e){
			e.printStackTrace();
		}
	}

}
