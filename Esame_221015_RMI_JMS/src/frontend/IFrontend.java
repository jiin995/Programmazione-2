package frontend;

import java.rmi.Remote;
import java.rmi.RemoteException;
import costumerData.*;

public interface IFrontend extends Remote {
	
	public boolean saveData(CostumerData data) throws RemoteException;

}
