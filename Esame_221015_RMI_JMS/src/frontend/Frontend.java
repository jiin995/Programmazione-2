package frontend;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import costumerData.CostumerData;

@SuppressWarnings("serial")
public class Frontend extends UnicastRemoteObject implements IFrontend {
	
	private TopicConnection connection;
	private TopicSession session;
	private Topic topic;
	private Semaphore request;
	private Semaphore queuedReq;
	private int maxReq;
	
	public Frontend(TopicConnection conn,int maxRe,Topic t)throws RemoteException{
		connection=conn;
		maxReq=maxRe;
		request=new Semaphore(maxReq); 
		/*
			Alternativa all'implementazione del prof.
			l'implementazione del prof prevede un sem con un numero di permessi pari a maxReq+2 su cui si effettua la try acquire e uno con maxReq su cui si effettua l'
			acquire ogni volta che va a buon fine la try acquire ovviamente non bisogna dimenticare le release
		*/
		queuedReq=new Semaphore(2);
		topic=t;
		try{
			session=connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		}catch(JMSException e){
			e.printStackTrace();
		}
	}
	
	public boolean saveData(CostumerData data) throws RemoteException{
		
		try{
			if(!request.tryAcquire()){									
				if(!queuedReq.tryAcquire()){
						return false;
					}else{
						System.out.println(Thread.currentThread().getName()+" Sospendo la richiesta");
						request.acquire();
						if(queuedReq.availablePermits()<2)
							queuedReq.release();
					}
				}
			
			TopicPublisher publisher=session.createPublisher(topic);
			ObjectMessage message=session.createObjectMessage();
			
			message.setObject(data);
			
			Thread.sleep(((int)Math.random()*6000)+5000);
			publisher.publish(message);
			
			if(request.availablePermits()<maxReq)
				request.release();
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(JMSException e){
			e.printStackTrace();
		}
		
		
		
		return true;
	}


}
