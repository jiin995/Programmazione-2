package publisher;

import java.rmi.RemoteException;

import messageServer.IMessageServer;
import myMessage.MyMessage;

public class PublisherThread extends Thread{
	
	private IMessageServer msgServer;
	private String args[]=new String[2];
	
	public PublisherThread(IMessageServer m,String topic,String msg){
		msgServer=m;
		args[0]=new String(topic);
		args[1]=new String(msg);
	}
	
	public void run(){
		try{
			msgServer.publish(new MyMessage(args[0], args[1]));
		}catch(RemoteException e){
			e.printStackTrace();
		}
	}

}
