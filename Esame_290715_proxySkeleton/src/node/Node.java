package node;

import dispatcher.IDispatcher;

public class Node {

	public static void main(String args[]){
		
		IDispatcher dispatcher=new DispatcherProxy("127.0.0.1",23232);
		
		for(int i=0;i<10;i++){
			NodeThread thread=new NodeThread(dispatcher,i);
			thread.start();
		}
		
	}
}
