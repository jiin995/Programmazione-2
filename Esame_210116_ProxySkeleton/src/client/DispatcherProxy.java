package client;

import dispatcher.IDispatcher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class DispatcherProxy implements IDispatcher {
	
	private int port;
	private String address;
	
	public DispatcherProxy(String add,int porta) {
		// TODO Auto-generated constructor stub
		port=porta;
		address=new String(add);
	}
	
	public boolean invia(String command){
		boolean response=false;
		
		try{
			
			Socket socket=new Socket(address,port);
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			DataInputStream in=new DataInputStream(socket.getInputStream());
			
			out.writeUTF("invia");
			out.writeUTF(command);
			
			response=in.readBoolean();
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return response;
	}
	
	public String esegui(){
		
		String cmd=new String("nono");
		
		try{
			
			Socket socket=new Socket(address, port);
			
			DataInputStream in =new DataInputStream(socket.getInputStream());
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			
			out.writeUTF("esegui");
			cmd=new String(in.readUTF());
			
			socket.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return cmd;
		
		
	}

}
