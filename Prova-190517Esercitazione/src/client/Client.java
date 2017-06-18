package client;


public class Client {
	
	public static void main(String[] args) {
	
		int nthreads =5;
		
		ClientThread[] clients=new ClientThread[nthreads];
		
		for(int i=0;i<nthreads;i++){
			clients[i]=new ClientThread();
			clients[i].start();
		}
	
	}
}
