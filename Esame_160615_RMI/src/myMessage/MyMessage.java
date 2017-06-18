package myMessage;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MyMessage implements Serializable {
	
	private String topic;
	private String msg;
	
	
	public MyMessage(String t,String mess){
		topic=new String(t);
		msg=new String(mess);
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
