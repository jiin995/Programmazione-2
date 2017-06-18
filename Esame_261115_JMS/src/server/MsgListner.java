package server;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.QueueConnection;

public class MsgListner implements MessageListener {
	
	private QueueConnection connection;
	private Compute compute;
	private int numberOfThread;
	
	public MsgListner(QueueConnection c,Compute comp){
		connection=c;
		compute=comp;
		numberOfThread=0;
	}
	
	public void onMessage(Message m){
		ObjectMessage msg=(ObjectMessage)m;
		
		ListnerThread t=new ListnerThread(connection, msg, numberOfThread, compute);
		t.start();
		
		/*try{
			
			QueueSession session=connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			ComputeData data=(ComputeData) msg.getObject();
			
			TextMessage msgReply=session.createTextMessage();
			msgReply.setJMSCorrelationID(msg.getJMSMessageID());
			
			
			String s=new String(String.valueOf(compute.getResult(data.getOperazione(),data.getOperando1(), data.getOperando2())));
			
			System.out.println(Thread.currentThread().getName()+" Elaboro richiesta di "+msg.getJMSMessageID()+
									"\nOperazione "+data.getOperazione()+" su "+data.getOperando1()+" "+data.getOperando2()+
									" Risultato "+s);
			
			msgReply.setText(s);			
			QueueSender sender=session.createSender((Queue)msg.getJMSReplyTo());
			
			sender.send(msgReply);
				
			session.close();
			sender.close();
			
			}catch(JMSException e){
				e.printStackTrace();
			}*/
	}

}
