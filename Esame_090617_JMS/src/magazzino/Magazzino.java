package magazzino;

import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;

import codaCircolare.CodaCircolare;

public  class Magazzino {
	
	public static void main(String args[]){
		Hashtable<String, String> jndiProperties=new Hashtable<String,String>();
		jndiProperties.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		jndiProperties.put("java.naming.provide.url", "tcp://127.0.0.1:61616");
		jndiProperties.put("queue.Request", "RequestQueue");
		jndiProperties.put("queue.Response", "ResponseQueue");
	
		try{
			CodaCircolare coda=new CodaCircolare(10);
			
			Context context=new InitialContext(jndiProperties);
			QueueConnectionFactory connFactory=(QueueConnectionFactory) context.lookup("QueueConnectionFactory");
			QueueConnection connection=connFactory.createQueueConnection();
			connection.start();
			
			QueueSession session=connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Queue request=(Queue)context.lookup("Request");
			
			QueueReceiver receiver=session.createReceiver(request);
			
			Listner listener=new Listner(connection, coda);
			
			receiver.setMessageListener(listener);
			
		}catch(NamingException e){
			e.printStackTrace();
		}catch(JMSException e){
			e.printStackTrace();
		}
	}

}
