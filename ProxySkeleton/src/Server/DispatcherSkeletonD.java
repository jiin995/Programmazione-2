package Server;

import Dispatcher.IDispatcher;

import java.io.IOException;
import java.net.*;

public class DispatcherSkeletonD implements IDispatcher{
	
	private IDispatcher dispatcher;
	private int port;
	
	public DispatcherSkeletonD(IDispatcher d,int p) {
		port=p;
		dispatcher=d;
		
	}
	
	public void runSkeleton(){
		
		try{
				@SuppressWarnings("resource")
				ServerSocket server=new ServerSocket(port);
				
				System.out.println("Server attivo (*D*)");
				
				while(true){
						Socket s=server.accept();
						
						ServerThread t=new ServerThread(s, this);
						t.start();
				}
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public int getCmd(){
		return dispatcher.getCmd();
	}
	
	public void sendCmd(int cmd){
		dispatcher.sendCmd(cmd);
	}

	
}
