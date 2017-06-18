package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

import magazzino.IMagazzino;

public class Proxy implements IMagazzino {

	private int port;
	private String add;
	
	public Proxy(String s,int p){
		port=p;
		add=new String(s);
	}
	
	public void deposita(String articolo, int id){
		try{
			
			Socket socket=new Socket(add, port);
			
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			DataInputStream in=new DataInputStream(socket.getInputStream());
			
			out.writeUTF("deposita");
			
			out.writeUTF(articolo);
			out.writeInt(id);
			
			String s=in.readUTF();
			System.out.println(s);
				
			socket.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public int preleva(String articolo){
		int id=-1;
		try{
			
			Socket socket=new Socket(add, port);
		
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			DataInputStream in=new DataInputStream(socket.getInputStream());
		
			out.writeUTF("preleva");
			out.writeUTF(articolo);
		
			id=in.readInt();

			System.out.println("Prelevato '"+articolo+"' con id "+id);
			socket.close();
		
		}catch(IOException e){
			e.printStackTrace();
		}
		return id;
	}

}
