package client;

import javax.naming.*;

import java.util.Hashtable;
import java.util.UUID;

import javax.jms.*;


public class Client {
	
	public static void main(String[] args) {

		Hashtable<String, String> jndiProperties=new Hashtable<String,String>();
		jndiProperties.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		jndiProperties.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		jndiProperties.put("queue.Request", "RequestQueue");
		jndiProperties.put("queue.Response", "ResponseQueue");
		
		try{
			
			Context contex=new InitialContext(jndiProperties);
			
			QueueConnectionFactory connFactory=(QueueConnectionFactory)contex.lookup("QueueConnectionFactory");
			QueueConnection connection=connFactory.createQueueConnection();
			QueueSession session=connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			connection.start();
			
			Queue requestQueue=session.createQueue("Request");
			Queue responseQueue=session.createQueue("Response");
			
			QueueSender requestSender=session.createSender(requestQueue);
			QueueReceiver responseReceiver=session.createReceiver(responseQueue);
			
			MapMessage request=session.createMapMessage();
			
			request.setJMSReplyTo(responseQueue);
			//request.setJMSCorrelationID(UUID.randomUUID().toString());
			request.setString("request",UUID.randomUUID().toString());
			
			requestSender.send(request);
			
			MapMessage response=session.createMapMessage();
			
			response=(MapMessage)responseReceiver.receive();
			
			System.out.println("Ricevuto il messaggio di risposta "+request.getString("request")+"\n"+
								response.getString("response"));
			
			session.close();
			requestSender.close();
			
			
			
		}catch(NamingException n){
			n.printStackTrace();
		}catch(JMSException e){
			e.printStackTrace();
		}
		
	}

}
