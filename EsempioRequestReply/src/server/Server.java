package server;

import javax.naming.*;

import java.util.Hashtable;

import javax.jms.*;


public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable<String, String> jndiProperties=new Hashtable<String,String>();
		jndiProperties.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		jndiProperties.put("java.naming.provide.url", "tcp://127.0.0.1:61616");
		jndiProperties.put("queue.Request", "RequestQueue");
		jndiProperties.put("queue.Response", "ResponseQueue");
		
		try{
			
			Context context=new InitialContext(jndiProperties);
			
			QueueConnectionFactory connFactory=(QueueConnectionFactory)context.lookup("QueueConnectionFactory");
			
			QueueConnection connection=connFactory.createQueueConnection();
			QueueSession session=connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			connection.start();

			Queue requestQueue=session.createQueue("Request");
			
			QueueReceiver requestReceiver=session.createReceiver(requestQueue);
			

			requestReceiver.setMessageListener(new MessageListner(connection));
			
			
			
		}catch(NamingException n){
			n.printStackTrace();
		}catch(JMSException e){
			e.printStackTrace();
		}
	}

}
