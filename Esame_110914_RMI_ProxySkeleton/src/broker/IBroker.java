package broker;

import java.rmi.*;

public interface IBroker extends Remote{
	
	public static final int ACQUISTA=1;
	public static final int VENDI=2;
	
	public boolean sottoscrivi(String address,int port) throws RemoteException;
	public boolean sottoponi(int tipoOperazione,int quantita) throws RemoteException;

}
