package client;

import javax.jms.*;
import javax.naming.*;
import java.util.Hashtable;

public class Sender {
		
	public static void main(String[] args){
		
		Hashtable <String,String> p=new Hashtable <String,String>();
		
		p.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		p.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		p.put("queue.test","mytestqueue");
		
		try{
			
			Context jndiContext =new InitialContext(p);
			QueueConnectionFactory queueConnFactory=(QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
			Queue queue=(Queue) jndiContext.lookup("test");
			
			QueueConnection queueConn=queueConnFactory.createQueueConnection();
			
			QueueSession queueSession=queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			QueueSender sender= queueSession.createSender(queue);
			TextMessage message= queueSession.createTextMessage();
			
			for(int i=0;i<5;i++){
				message.setText("hello_"+i);
				sender.send(message);
			}
			
			sender.close();
			queueSession.close();
			queueConn.close();
			
		}catch(NamingException n){
			n.printStackTrace();
		}catch(JMSException j){
			j.printStackTrace();
		}
		
		System.out.println("Terminato");
		
	}
	
}

