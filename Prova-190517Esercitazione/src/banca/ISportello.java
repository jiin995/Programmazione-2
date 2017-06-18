package banca;

import java.rmi.*;

public interface ISportello extends Remote {

		public boolean serviRichiesta(int idClient) throws RemoteException; 
}
