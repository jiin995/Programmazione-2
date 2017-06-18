package agenzia;

import java.io.IOException;
import java.net.*;

public class AgenziaSkeletonD implements IAgenzia {
	
	private IAgenzia agenzia;
	private int port;
	
	public AgenziaSkeletonD(IAgenzia agen,int p){
		port=p;
		agenzia=agen;
	}
	
	public void runSkeleton(){
		int i=0;
		try{
			@SuppressWarnings("resource")
			ServerSocket server= new ServerSocket(port);
			System.out.println("Skeleton in esecuzione ");
			
			while(true){
				
				Socket socket=server.accept();
				SkeletonThread thread=new SkeletonThread(socket,i++,this);
				thread.start();
				
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public boolean acquista(int qnt){
		return agenzia.acquista(qnt);
	}
	public boolean vendi(int qnt){
		return agenzia.vendi(qnt);
	}

}
