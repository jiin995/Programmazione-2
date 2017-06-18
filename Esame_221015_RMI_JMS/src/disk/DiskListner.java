package disk;

import java.io.PrintStream;

import javax.jms.*;

import costumerData.CostumerData;

public class DiskListner implements MessageListener {
	
	private PrintStream out;

	public DiskListner(PrintStream o){
		out=o;
	}
	
	public void onMessage(Message m){
				
		
		try{
		
			ObjectMessage msg=(ObjectMessage)m;
				
			CostumerData data=(CostumerData)msg.getObject();
		
			System.out.println("Ricevuto "+data.getName()+" "+data.getSurname()+" "+data.getAge());
		
			out.println("Ricevuto "+data.getName()+" "+data.getSurname()+" "+data.getAge());
			out.flush();

		}catch(JMSException e){
			e.printStackTrace();
		}
		
	}


}
