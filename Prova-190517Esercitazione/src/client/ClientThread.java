package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import banca.IGestoreSportello;

public class ClientThread extends Thread {

	public void run(){
		try{
			Registry rmireg=LocateRegistry.getRegistry();	
			IGestoreSportello gestore=(IGestoreSportello)rmireg.lookup("mygestore");
	
			int id= 1+(int)Math.random()*100;
			
			System.out.println("Inizio richiesta "+id);
			
			boolean res=gestore.inoltraRichiesta(id);
			
			System.out.println("inviato "+id+" ricevuto "+res);
		
		}catch(RemoteException e){
			e.printStackTrace();
		}catch(NotBoundException l){
			l.printStackTrace();
		}
	}
}
