package messageServer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.concurrent.Semaphore;

import myMessage.MyMessage;
import subscriber.ISubscriber;

@SuppressWarnings("serial")
public class MessageServerImpl extends UnicastRemoteObject implements IMessageServer {
	
	private Vector<SubscriberT> subscribed;
	private Semaphore maxSub;
	private Semaphore maxReq;
	private BufferedWriter out;
	
	public MessageServerImpl() throws RemoteException{
		subscribed=new Vector<SubscriberT>();
		maxSub=new Semaphore(3);
		maxReq=new Semaphore(2);
		try{
			out=new BufferedWriter(new FileWriter("./log"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public boolean publish(MyMessage msg) throws RemoteException{
		
		try{
			if(!maxSub.tryAcquire()){
				if(!maxReq.tryAcquire()){
					System.out.println("[Message Server] gia' due richieste accodate questa non verra' servita");
					return false;
				}else{
					System.out.println("[Message Server] Richiesta accodata numero richieste ancora disponibili "+maxReq.availablePermits());
					maxSub.acquire();
					maxReq.release();
					System.out.println("[Message Server] Richiesta tolta dalla coda verra' servita, ancora disponibili "+maxReq.availablePermits());
				}
			}
			
			Thread.sleep((int )(Math.random()*6000));
			out.write("Topic - '"+msg.getTopic()+"'   ,msg -'"+msg.getMsg()+" '\n");
			out.flush();
		
			for(int i=0;i<subscribed.size();i++){
				if(subscribed.get(i).getTopic().equals(msg.getTopic())){
					System.out.println("[Message Server] Notifico --> sul topic "+msg.getTopic());
					subscribed.get(i).getSubscriber().onMessage(msg);
				}
				maxSub.release();
			}
		}catch(RemoteException e){
			e.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return true;
		
	}
	public boolean subscribe(String topic,ISubscriber ref) throws RemoteException{
		subscribed.add(new SubscriberT(ref, topic));
		System.out.println("[Message Server] Nuovo Subscriber per il topic "+topic+"\n Sono sottoscritti attualmete "+subscribed.size());
		return true;
	}

}
