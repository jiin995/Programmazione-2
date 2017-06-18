package server;

import magazzino.*;

import java.io.IOException;
import java.net.*;

public class Skeleton implements IMagazzino {
	
	private Magazzino magazzino;
	private int port;
	
	public Skeleton(int ss,Magazzino m){
		port=ss;
		magazzino=m;
	}
	
	public void runSkeleton(){
		try{
				@SuppressWarnings("resource")
				ServerSocket server=new ServerSocket(port);
				int i=0;
				System.out.println("[Server]--> Skeleton avviato e posto in ascolto sulla porta "+port);
				while(true){
					Socket s=server.accept();
					ServerThread t=new ServerThread(s, this,i++);
					t.start();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		
	}
	
	public void deposita(String articolo,int id){
		magazzino.deposita(articolo, id);	
	}
	
	public int preleva(String articolo){
		return magazzino.preleva(articolo);
	}
	

}
