package messageServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

import myMessage.MyMessage;
import subscriber.ISubscriber;

public interface IMessageServer  extends Remote{

	public boolean publish(MyMessage msg) throws RemoteException;
	public boolean subscribe(String topic,ISubscriber ref) throws RemoteException;

}
