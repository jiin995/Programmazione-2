package broker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import agenzia.IAgenzia;

public class AgenziaProxy implements IAgenzia {
	
	private int port;
	private String address;
	
	public AgenziaProxy(String add,int p){
		address=new String(add);
		port=p;
	}
	
	public boolean acquista(int qnt){
		
		boolean x=false;
		
		try{
			Socket socket=new Socket(address,port);
			
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			DataInputStream in=new DataInputStream(socket.getInputStream());
			
			System.out.println("[Broker] Inoltro richiesta di acquisto all'agenzia "+address+":"+port);
			
			out.writeUTF("acquista");
			out.writeInt(qnt);
			
			x=in.readBoolean();
			System.out.println("[Broker] Risposta all'acquisto da "+address+":"+port+" "+x);

			
			out.close();
			in.close();
			socket.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return x;
	}
	public boolean vendi(int qnt){
		
boolean x=false;
		
		try{
			Socket socket=new Socket(address,port);
			
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			DataInputStream in=new DataInputStream(socket.getInputStream());
			
			System.out.println("[Broker] Inoltro richiesta di vendita all'agenzia "+address+":"+port);
			
			out.writeUTF("vendi");
			out.writeInt(qnt);
			
			x=in.readBoolean();
			System.out.println("[Broker] Risposta alla vendita da "+address+":"+port+" "+x);

			
			out.close();
			in.close();
			socket.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return x;
	}

}
