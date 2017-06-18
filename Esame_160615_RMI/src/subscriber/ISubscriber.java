package subscriber;

import java.rmi.Remote;
import java.rmi.RemoteException;
import myMessage.*;

public interface ISubscriber extends Remote {
	
	public boolean subscribe(String topic,ISubscriber ref) throws RemoteException;
	public void onMessage(MyMessage msg) throws RemoteException;

}
