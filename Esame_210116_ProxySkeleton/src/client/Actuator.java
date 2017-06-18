package client;

public class Actuator {

	public static void main(String[] args) {
		DispatcherProxy dispatcher=new DispatcherProxy("127.0.0.1", 23233);
		try{
				while(true){
					
					Thread.sleep(1000);			
					System.out.println("[Actuator ]-->"+dispatcher.esegui());
				}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
