package client;

import java.util.Hashtable;
import javax.jms.*;
import javax.naming.*;


public class Receiver {

	public static void main(String[] args) {
		
		Hashtable<String,String > p=new Hashtable<String, String>();
		
		p.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		p.put("java.naming.provider.url","tcp://127.0.0.1:61616");
		p.put("queue.test","mytestqueue");
		
		try{
			
			Context jndiContext=new InitialContext(p);
			
			QueueConnectionFactory queueConnFactory=(QueueConnectionFactory)jndiContext.lookup("QueueConnectionFactory");
			Queue queue=(Queue)jndiContext.lookup("test");
			
			QueueConnection queueConn=queueConnFactory.createQueueConnection();
			QueueSession queueSession=queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			QueueReceiver receiver=queueSession.createReceiver(queue);
			TextMessage message=queueSession.createTextMessage();
			
			queueConn.start();
			
			for(int i=0;i<5;i++){
				message=(TextMessage)receiver.receive();
				System.out.println(message.getText());
			}
			
			receiver.close();
			queueSession.close();
			queueConn.close();
			
		}catch(NamingException n){
			n.printStackTrace();
		}catch(JMSException j){
			j.printStackTrace();
		}
		
	}

}
