package server;

import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;


public class ComputeUnit {

	public static void main(String[] args) {
		
	    System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");

		
		Hashtable<String,String> p=new Hashtable<String,String>();
		p.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		p.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		
		p.put("queue.REQ", "RequestQueue");
		p.put("queue.RPLSUM","ReplySum");
		p.put("queue.RPLMUL", "ReplyMul");
		try{
			Context contex=new InitialContext(p);
			
			QueueConnectionFactory connFactory=(QueueConnectionFactory)contex.lookup("QueueConnectionFactory");
			QueueConnection connection=connFactory.createQueueConnection();
			QueueSession session=connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
		
			connection.start();
		
			Queue reqQueue=(Queue)contex.lookup("REQ");
			QueueReceiver receiver=session.createReceiver(reqQueue);
		
			Compute comp=new Compute(5);
		
			MsgListner listner=new MsgListner(connection, comp);
			receiver.setMessageListener(listner);

		
		}catch(NamingException e){
			e.printStackTrace();
		}catch(JMSException e){
			e.printStackTrace();
		}
		
	}
}
