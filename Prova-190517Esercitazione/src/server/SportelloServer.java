package server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


import banca.*;

public class SportelloServer {

	public SportelloServer(){
		try{
			
			ISportello sportello=new SportelloImpl();
			
			Registry rmireg=LocateRegistry.getRegistry();	
			IGestoreSportello gestore=(IGestoreSportello)rmireg.lookup("mygestore");
		
			gestore.sottoscrivi(sportello);
		
			System.out.println("Gestore UP AND RUNNING!!");
		
		}catch(RemoteException e){
			e.printStackTrace();
		}catch(NotBoundException l){
			l.printStackTrace();
		}
	}
}
