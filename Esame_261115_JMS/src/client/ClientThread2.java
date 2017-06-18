package client;

import java.util.UUID;

import javax.jms.*;
import computeData.*;

class ClientThread2 extends Thread {
	
	private QueueConnection connection;
	private Queue reqQueue;
	private Queue replySum;
	private Queue replyMul;
	
	public ClientThread2(QueueConnection conn,int i,Queue rQ,Queue rS,Queue rM) {
		// TODO Auto-generated constructor stub
		super("[Thread sender "+i+" ]");
		connection=conn;
		reqQueue=rQ;
		replySum=rS;
		replyMul=rM;
		
		
	}
	
	public void run(){
		try{
			QueueSession session=connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			
			QueueSender sender=session.createSender(reqQueue);
			
			ObjectMessage msg=session.createObjectMessage();
			TextMessage msgResponse=session.createTextMessage();
			
			int choice=(int)((Math.random()*2));
			ComputeData data=new ComputeData((int)(Math.random()*100),(int)(Math.random()*100),choice);
			msg.setObject(data);			
			Thread.sleep((int)((Math.random()*5000)+1000));
			
			Queue answ=null;
			QueueReceiver receiver=null;

			String id=UUID.randomUUID().toString();
			msg.setStringProperty("id", id);
			
			if(choice==0){
				answ=replySum;
				receiver=session.createReceiver(answ,"id='"+id+"'");
			}else if(choice==1){
				answ=replyMul;
				receiver=session.createReceiver(answ,"id='"+id+"'");
			}
			
			msg.setJMSReplyTo(answ);
			sender.send(msg);
			
		//	while((msgResponse=(TextMessage)receiver.receive()).getJMSCorrelationID().compareTo(msg.getJMSMessageID())!=0);
			msgResponse =(TextMessage)receiver.receive();
			System.out.println(Thread.currentThread().getName()+(data.getOperazione().equals("SUM")?"Sommo ":"Moltiplico ")+"op1:"+data.getOperando1()+" op2: "+data.getOperando2()+
								"\n Risultato ->"+msgResponse.getText()+"\n"+msg.getJMSMessageID()+"\n"+msgResponse.getJMSCorrelationID()+"\n");
			
			receiver.close();
			sender.close();
			session.close();
			
		}catch(JMSException e){
			e.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
