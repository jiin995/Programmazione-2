package server;

import javax.jms.*;

public class MessageListner implements MessageListener {
	
	private QueueConnection connection;
	
	public MessageListner(QueueConnection c){
		connection=c;
	}
	
	public void onMessage(Message m){
		
		MapMessage request=(MapMessage)m;
		try{
			QueueSession session=connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		
		
			QueueSender responseSender=session.createSender((Queue)request.getJMSReplyTo());

			MapMessage response=session.createMapMessage();
			response.setString("response", request.getString("request"));
		
			responseSender.send(response);
			responseSender.close();
			session.close();
		}catch(JMSException e){
			e.printStackTrace();
		}
	}

}
