package gestore;

import java.rmi.*;

public interface IGestore extends Remote {

	public boolean richiestaDeposito(int id_articolo) throws RemoteException;
	public boolean sottoscrivi(String add,int p) throws RemoteException;
	
}
