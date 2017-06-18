package magazzino;

import javax.jms.MapMessage;

import java.util.concurrent.Semaphore;

import javax.jms.*;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;

import codaCircolare.CodaCircolare;

public class ListnerThread extends Thread {
	
	private MapMessage msg;
	private QueueConnection connection;
	private CodaCircolare coda;
	
	private Semaphore isFull;
	private Semaphore isEmpty;
	
	public ListnerThread(MapMessage m,QueueConnection c,CodaCircolare cod){
		msg=m;
		connection=c;
		coda=cod;
		
		isFull=new Semaphore(0);
		isEmpty=new Semaphore(0);
	}

	public void run(){
		
		try{
			QueueSession session=connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue responseQueue=(Queue)msg.getJMSReplyTo();
			
			QueueSender sender=session.createSender(responseQueue);
			TextMessage reply=session.createTextMessage();
			reply.setStringProperty("id", msg.getStringProperty("id"));
			
			if(msg.getString("TipoRichista").equals("deposita")){
				System.out.println("Ricevuta richiesta di deposito da"+ msg.getStringProperty("id"));
				if(coda.isFull())
					isFull.acquire();
				coda.addElem(msg.getInt("id_articolo"));
				reply.setText("Elemento "+msg.getInt("id_articolo")+" inserito in coda");
				
				if(isEmpty.availablePermits()<0)
					isEmpty.release();
				
			}else if(msg.getString("TipoRichista").equals("preleva")){
				System.out.println("Ricevuta richiesta di preleva da"+ msg.getStringProperty("id"));
				if(coda.isEmpty())
					isEmpty.acquire();
				reply.setText(String.valueOf(coda.getElem()));
				
				if(isFull.availablePermits()<0)
					isFull.release();
			
			}
			Thread.sleep((int)(1000+Math.random()*5000));
			
			sender.send(reply);
			
		}catch(JMSException e){
			e.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
}
