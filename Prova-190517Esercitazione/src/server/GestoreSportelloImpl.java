package server;

import java.rmi.RemoteException;
import java.rmi.server.*;
import banca.*;
import java.util.Vector;;

@SuppressWarnings("serial")
public class GestoreSportelloImpl extends UnicastRemoteObject implements IGestoreSportello{
	
	private Vector <ISportello> lista;
	
	public GestoreSportelloImpl() throws RemoteException{
		lista=new Vector <ISportello>();
	}
	
	public boolean inoltraRichiesta(int idClient)throws RemoteException{
		
		int l=lista.size();
		int i=0;
		boolean esito=false;
		
		do{
			esito=lista.get(i).serviRichiesta(idClient);
			i=i+1;
		}while(esito==false && i<l);
			
		return esito;
	}
	
	public void sottoscrivi(ISportello sportello)throws RemoteException{
		
		lista.add(sportello);
		System.out.println("Sottoscrizione Rifermento "+ sportello);
	}

}
