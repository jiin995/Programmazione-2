package client;

import java.util.UUID;

import javax.jms.*;

public class ClientThread extends Thread {
	
	private QueueConnection connection;
	private Queue request;
	private Queue response;
	private int r;
	
	
	public ClientThread(QueueConnection conne,Queue Request,Queue Response,int i){
		super("[Thread Sender "+i+"]");
		connection=conne;
		request=Request;
		response=Response;
		r=i;
	}
	
	public void run(){
		
		try{
			QueueSession session=connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender=session.createSender(request);
			
			MapMessage requestMsg=session.createMapMessage();
			requestMsg.setJMSReplyTo(response);
			
			String id=UUID.randomUUID().toString();
			QueueReceiver receiver=session.createReceiver(response,"id ='"+id+"'");

			
	//		if((int)(Math.random()*2)==0){
			if((r%2)==0){
				System.out.println(Thread.currentThread().getName()+" Invio Richiesta di deposito");
				requestMsg.setString("TipoRichista", "deposita");
				requestMsg.setInt("id_articolo", (int)(Math.random()*11));				
			}else{
				System.out.println(Thread.currentThread().getName()+" Invio Richiesta di prelievo");
				requestMsg.setString("TipoRichista", "preleva");
			}
			
			requestMsg.setStringProperty("id", id);
			sender.send(requestMsg);
			
			TextMessage responseMsg=(TextMessage)receiver.receive();
			if(id.equals(responseMsg.getStringProperty("id")))
				System.out.println(Thread.currentThread().getName()+" Ottenuta Risposta Corretta \n"+responseMsg.getText());

		}catch(JMSException e){
			e.printStackTrace();
		}
	}


}
