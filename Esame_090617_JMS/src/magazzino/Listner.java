package magazzino;

import javax.jms.*;
import codaCircolare.CodaCircolare;

public class Listner implements MessageListener{

	private QueueConnection connection;
	private CodaCircolare coda;
	
	public Listner(QueueConnection c,CodaCircolare cod) {
		// TODO Auto-generated constructor stub
		connection=c;
		coda=cod;
	}
	
	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		
		ListnerThread thread=new ListnerThread((MapMessage)arg0,connection,coda);
		thread.start();
		
	}
	

}
