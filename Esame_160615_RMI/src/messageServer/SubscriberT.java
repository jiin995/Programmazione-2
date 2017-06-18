package messageServer;

import subscriber.ISubscriber;

public class SubscriberT {
	
	private ISubscriber subscriber;
	private String Topic;
	
	public ISubscriber getSubscriber() {
		return subscriber;
	}
	public void setSubscriber(ISubscriber subscriber) {
		this.subscriber = subscriber;
	}
	public String getTopic() {
		return Topic;
	}
	public void setTopic(String topic) {
		Topic = topic;
	}
	
	public SubscriberT(ISubscriber subscriber, String topic) {
		super();
		this.subscriber = subscriber;
		Topic = topic;
	}
	
	

}
