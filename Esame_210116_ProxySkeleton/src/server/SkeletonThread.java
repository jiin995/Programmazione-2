package server;

import dispatcher.IDispatcher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class SkeletonThread extends Thread {
	
	private IDispatcher dispatcher;
	private Socket socket;
	
	public SkeletonThread(IDispatcher d,int i,Socket s){
		super("[Thread dispatcher ->]"+i+" in esecuzione ");
		dispatcher =d;
		socket=s;
	}
	
	public void run(){
		
		try{
			System.out.println(Thread.currentThread().getName()+" -->Avviato");
			
			DataInputStream in=new DataInputStream(socket.getInputStream());
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			
			String command=new String(in.readUTF());
				if(command.equals("invia")){
					command=new String(in.readUTF());
					out.writeBoolean(dispatcher.invia(command));					
				}else if(command.equals("esegui")){
					//out.writeUTF(Thread.currentThread().getName()+" eseguito command "+dispatcher.esegui());
					out.writeUTF(dispatcher.esegui());
				}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
	}

}
