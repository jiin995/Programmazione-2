package dispatcher;

import java.net.*;
import java.io.*;

public class DispatcherSkeletonD implements IDispatcher {
	
	private Dispatcher dispatcher;
	private int port;
	
	public DispatcherSkeletonD( Dispatcher d,int p){
		dispatcher=d;
		port=p;
	}
	
	public void runSkeleton(){
		int i=0;
		
		try{
			System.out.println("[Skeleton Dispatcher] Avviato e posto in ascolto sulla porta "+port);
			@SuppressWarnings("resource")
			ServerSocket server=new ServerSocket(port);
			
			while(true){
				Socket s=server.accept();
				
				SkeletonThread thread=new SkeletonThread(dispatcher, s,i++);
				thread.start();
			}			
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public boolean addPrinter(String add,int p){
		return dispatcher.addPrinter(add, p);
	}
	
	public boolean printRequest(String docName){
		return dispatcher.printRequest(docName);
	}
	
	

}
