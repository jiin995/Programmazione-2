package client;

public class Client {

	public static void main(String[] args) {
		
		DispatcherProxy dispatcher=new DispatcherProxy("127.0.0.1", 23233);
		ClientThread ct[]=new ClientThread[5];
		for(int i=0;i<5;i++){
			ct[i]=new ClientThread(dispatcher, i);
			ct[i].start();
		}
	}

}
