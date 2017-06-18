package disk;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Hashtable;

import javax.naming.*;

import javax.jms.*;

public class Disk {
	
	public static void main(String args[]){
	    System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
		
		Hashtable<String, String> jndiProperties=new Hashtable<String,String>();
		jndiProperties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		jndiProperties.put("java.naming.provide.url", "tcp://127.0.0.1:61616");
		jndiProperties.put("topic.Disk","DiskTopic");
		
		NumberOfDisk diskNumber=NumberOfDisk.getIstance();
		
		
		try{
			PrintStream out=new PrintStream(new BufferedOutputStream(new FileOutputStream("./Disk"+diskNumber.newDisk()+".txt")));

			Context context=new InitialContext(jndiProperties);
			
			TopicConnectionFactory connFactory=(TopicConnectionFactory)context.lookup("TopicConnectionFactory");
			TopicConnection connection=connFactory.createTopicConnection();
			
			connection.start();
			
			Topic topic=(Topic)context.lookup("Disk");
			TopicSession session=connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicSubscriber sub=session.createSubscriber(topic);
			
			DiskListner listner=new DiskListner(out);
			
			sub.setMessageListener(listner);
			
			
		}catch(NamingException e){
			e.printStackTrace();
		}catch(JMSException e){
			e.printStackTrace();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
	}

}

	