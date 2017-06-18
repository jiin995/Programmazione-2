package frontend;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;

public class FrontendMain {

	public static void main(String[] args) {
	    System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
	    System.setProperty("java.rmi.server.codebase","file:\\C:\\Usersjiin995\\Dropbox\\Workspace\\Esame_221015_RMI_JMS\\bin\\");



		Hashtable<String , String > jndiProperties=new Hashtable<String,String>();
		
		jndiProperties.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		jndiProperties.put("java.naming.provide.url","tcp://127.0.0.1:61616");
		jndiProperties.put("topic.Disk", "DiskTopic");
		
		
		try{
			
			Context context=new InitialContext(jndiProperties);
			
			TopicConnectionFactory connFactory=(TopicConnectionFactory)context.lookup("TopicConnectionFactory");
			TopicConnection connection=(TopicConnection)connFactory.createConnection();
			
			Topic topic=(Topic)context.lookup("Disk");
			
			IFrontend frontend=new Frontend(connection, 5, topic);

			Registry registry=LocateRegistry.getRegistry();
			registry.rebind("Frontend", frontend);
			
			System.out.println("Frontend UP AND RUNNING");
			
		}catch(RemoteException e){
			e.printStackTrace();
		}catch(JMSException e){
			e.printStackTrace();
		}catch(NamingException n){
			n.printStackTrace();
		}
	}

}
