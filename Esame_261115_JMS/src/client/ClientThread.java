package client;


import javax.jms.*;
import computeData.*;

class ClientThread extends Thread {
	
	private QueueConnection connection;
	
	public ClientThread(QueueConnection conn,int i) {
		// TODO Auto-generated constructor stub
		super("[Thread sender "+i+" ]");
		connection=conn;
		
	}
	
	public void run(){
		try{
			QueueSession session=connection.createQueueSession(false, QueueSession.CLIENT_ACKNOWLEDGE);
			Queue reqQueue=session.createQueue("REQ");
			Queue mulQueue=session.createQueue("RPLMUL");
			Queue sumQueue=session.createQueue("RPLSUM");
			
			QueueSender sender=session.createSender(reqQueue);
			QueueReceiver receiverSum=session.createReceiver(sumQueue);
			QueueReceiver receiverMul=session.createReceiver(mulQueue);
			ObjectMessage msg=session.createObjectMessage();
			TextMessage msgResponse=session.createTextMessage();
			
			int choice=(int)((Math.random()*2));
			ComputeData data=new ComputeData((int)(Math.random()*100),(int)(Math.random()*100),choice);
			msg.setObject(data);			
			Thread.sleep((int)(Math.random()*6000));

			msg.setJMSMessageID(Thread.currentThread().getName());
			if(choice==0){
				System.out.println(Thread.currentThread().getName()+" operazione somma op1:"+data.getOperando1()+" op2: "+data.getOperando2());
				msg.setJMSReplyTo(sumQueue);
				msg.setJMSCorrelationID(Thread.currentThread().getName());
				sender.send(msg);
				msgResponse=(TextMessage)receiverSum.receive();	
				msgResponse.acknowledge();
				
			}else if(choice==1){
				System.out.println(Thread.currentThread().getName()+" operazione moltiplicazione op1:"+data.getOperando1()+" op2:"+data.getOperando2());
				msg.setJMSReplyTo(mulQueue);
				msg.setJMSCorrelationID(Thread.currentThread().getName());
				sender.send(msg);
				msgResponse=(TextMessage)receiverMul.receive();
				msgResponse.acknowledge();
			}
			
			System.out.println(Thread.currentThread().getName()+" Mio ID "+msg.getJMSMessageID() +" \n"+msgResponse.getJMSCorrelationID()+" Risulato-->"+msgResponse.getText());
			
			receiverMul.close();
			receiverSum.close();
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
