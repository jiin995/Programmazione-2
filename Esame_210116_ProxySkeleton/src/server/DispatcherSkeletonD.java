package server;

import dispatcher.*;
import java.io.IOException;
import java.net.*;

public class DispatcherSkeletonD implements IDispatcher {
	
	IDispatcher dispatcher;
	int port;
	
	public DispatcherSkeletonD(IDispatcher d,int porta) {
		// TODO Auto-generated constructor stub
		dispatcher=d;
		port=porta;
	}
	
	public void runSkeleton(){
		
		try{
			@SuppressWarnings("resource")
			ServerSocket server=new ServerSocket(port);
			System.out.println("[Skeleton Dispatcher] Avviato e posto in ascolto sulla porta "+port);
			
			int i=0;
			
			while(true){
				Socket s=server.accept();
				SkeletonThread thread=new SkeletonThread(this,i++,s);
				thread.start();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public String esegui(){
		return dispatcher.esegui();
	}
	
	public boolean invia(String cmd){
		return dispatcher.invia(cmd);
	}
	

}
