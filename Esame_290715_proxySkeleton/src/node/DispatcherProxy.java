package node;

import java.io.*;
import java.net.Socket;

import dispatcher.IDispatcher;

public class DispatcherProxy implements IDispatcher {
	
	int port;
	String address;
	
	public DispatcherProxy (String add,int p){
		port=p;
		address=add;
	}
	
	public boolean addPrinter(String add,int p){
		return false;
	}
	
	public boolean printRequest(String docName){
		
		boolean x=false;
		
		try{
			
			Socket socket=new Socket(address,port);
			
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			DataInputStream in=new DataInputStream(socket.getInputStream());
			
			out.writeUTF("printRequest");
			out.writeUTF(docName);
			x=in.readBoolean();
			
			socket.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return x;
	}
	

}
