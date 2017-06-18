package banca;

import java.rmi.*;

public interface IGestoreSportello extends Remote {

	public boolean inoltraRichiesta (int idClient) throws RemoteException;
	
	public void sottoscrivi (ISportello i) throws RemoteException;
}
