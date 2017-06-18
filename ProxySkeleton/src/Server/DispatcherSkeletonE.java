package Server;

import java.io.IOException;
import java.net.*;
import Dispatcher.*;

public abstract class DispatcherSkeletonE implements IDispatcher{

	private int port;
	
	public DispatcherSkeletonE (int p){
		port=p;
	}
	
	public void runSkeleton(){
		
		try{
			@SuppressWarnings("resource")
			ServerSocket server=new ServerSocket(port);
			
			System.out.println("Server Attivato (*E*)");
			
			while(true){
				
				Socket s=server.accept();
				
				ServerThread t =new ServerThread(s,this);
				t.start();
				
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}	
	}

}
