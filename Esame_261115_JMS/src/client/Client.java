package client;

import javax.jms.*;
import java.util.Hashtable;
import javax.naming.*;


public class Client {

	public static void main(String[] args) {
	    System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");

		
		// TODO Auto-generated method stub
		Hashtable<String, String> p=new Hashtable<String,String>();
		p.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory" );
		p.put("java.naming.provider.url", "tcp://127.0.0.1:61616" );
		
		p.put("queue.REQ","RequestQueue");
		p.put("queue.RPLSUM", "ReplySum");
		p.put("queue.RPLMUL", "ReplyMul");
		try{
			Context contex=new InitialContext(p);
			
			QueueConnectionFactory connFactory=(QueueConnectionFactory)contex.lookup("QueueConnectionFactory");
			QueueConnection connection=connFactory.createQueueConnection();
			Queue reqQueue=(Queue)contex.lookup("REQ");
			Queue replySum=(Queue)contex.lookup("RPLSUM");
			Queue replyMul=(Queue)contex.lookup("RPLMUL");
			
			connection.start();
			
			ClientThread2 Thread[]=new ClientThread2[10];
			for(int i=0;i<10;i++){
				Thread[i]=new ClientThread2(connection,i,reqQueue,replySum,replyMul);
				Thread[i].start();
			}
			
		}catch(NamingException e){
			e.printStackTrace();
		}catch(JMSException j){
			j.printStackTrace();
		}
	}

}
