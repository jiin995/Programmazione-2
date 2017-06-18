package server;

import java.rmi.RemoteException;
import java.rmi.registry.*;

import banca.*;

public class GestoreSportelloServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			
			IGestoreSportello gestore=new GestoreSportelloImpl();
			
			Registry rmireg=LocateRegistry.getRegistry();
			ISportello i=new SportelloImpl();
			
			gestore.sottoscrivi(i);
			rmireg.rebind("mygestore", gestore);
			
			System.out.println("Gestore UP AND RUNNING!!");
			
		}catch(RemoteException e){
				e.printStackTrace();
		}
	}

}
