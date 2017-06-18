package node;

import java.util.UUID;

import dispatcher.IDispatcher;

public class NodeThread extends Thread {

	IDispatcher dispatcher;
	
	public NodeThread(IDispatcher d,int i) {
		// TODO Auto-generated constructor stub
		super("[Thread Node "+i+"]");
		dispatcher=d;
	}
	
	public void run(){
		
		System.out.println(Thread.currentThread().getName()+" Avviato");
		String docName=UUID.randomUUID().toString();
		System.out.println(Thread.currentThread().getName()+"--> iviata Richiesta"+docName+" risposta" +dispatcher.printRequest(docName));

		
	}
	

}
