package client;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Client {
	public static void main(String args[]){
		
		Hashtable<String, String> jndiProperties=new Hashtable<String,String>();
		jndiProperties.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		jndiProperties.put("java.naming.provide.url", "tcp://127.0.0.1:61616");
		jndiProperties.put("queue.Request", "RequestQueue");
		jndiProperties.put("queue.Response", "ResponseQueue");
	
		try{
			
			Context context=new InitialContext(jndiProperties);
			QueueConnectionFactory connFactory=(QueueConnectionFactory) context.lookup("QueueConnectionFactory");
			QueueConnection connection=connFactory.createQueueConnection();
			connection.start();
						
			Queue request=(Queue)context.lookup("Request");
			Queue response=(Queue)context.lookup("Response");
			
			for(int i=0;i<10;i++){
				ClientThread thread=new ClientThread(connection,request,response,i);
				thread.start();
			}
		}catch(NamingException e){
			e.printStackTrace();
		}catch(JMSException e){
			e.printStackTrace();
		}
	}
}
