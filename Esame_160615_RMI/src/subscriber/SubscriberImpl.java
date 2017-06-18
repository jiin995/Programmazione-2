package subscriber;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import messageServer.IMessageServer;
import myMessage.MyMessage;

@SuppressWarnings("serial")

public class SubscriberImpl extends UnicastRemoteObject implements ISubscriber{
	
	IMessageServer msgServer;
	
	public SubscriberImpl(IMessageServer m) throws RemoteException{
		msgServer=m;
	}
	
	public boolean subscribe(String topic,ISubscriber ref) throws RemoteException{
		boolean x=msgServer.subscribe(topic, ref);;
		System.out.println("[Subscriber] Esito Sottoscrizione al topic "+topic+" :"+x);
		return x;
	}
	public void onMessage(MyMessage msg) throws RemoteException{
		System.out.println("[Subscriber] Ricevuto messaggio sul topic "+msg.getTopic()+" : "+msg.getMsg());
	}

}
