package server;

import javax.jms.*;

import computeData.ComputeData;

public class ListnerThread extends Thread{
	
	private QueueConnection  connection;
	private ObjectMessage msg;
	private Compute compute;
	
	public ListnerThread(QueueConnection c,ObjectMessage message,int i,Compute comp){
		super("[Thread Listner "+i+" ]");
		connection=c;
		msg= message;
		compute=comp;
	}
	
	public void run(){
		
		try{
			
			QueueSession session=connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			ComputeData data=(ComputeData) msg.getObject();
			
			TextMessage msgReply=session.createTextMessage();
			msgReply.setJMSCorrelationID(msg.getJMSMessageID());
			
			
			String s=new String(String.valueOf(compute.getResult(data.getOperazione(),data.getOperando1(), data.getOperando2())));
			
			System.out.println(Thread.currentThread().getName()+//" Elaboro richiesta di "+msg.getJMSMessageID()+
									"\nOperazione "+data.getOperazione()+" su "+data.getOperando1()+" "+data.getOperando2()+
									" Risultato "+s);
			
			msgReply.setText(s);			
			msgReply.setStringProperty("id", msg.getStringProperty("id"));
			QueueSender sender=session.createSender((Queue)msg.getJMSReplyTo());
			
			sender.send(msgReply);
			System.out.println(msg.getJMSMessageID()+" \n"+msgReply.getJMSCorrelationID());
				
			session.close();
			sender.close();
			
			}catch(JMSException e){
				e.printStackTrace();
			}
		
	}

}
