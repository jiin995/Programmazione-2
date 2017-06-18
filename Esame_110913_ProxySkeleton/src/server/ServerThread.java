package server;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
	
	private Skeleton skeleton;
	private Socket socket;
	
	public ServerThread(Socket ss,Skeleton skel,int i){
		super("[Skeleton Worker Thread -> "+i+"]");
		skeleton=skel;
		socket=ss;
	}
	
	public void run(){
		try{
			
			System.out.println(Thread.currentThread().getName()+" avviato");
			DataInputStream in=new DataInputStream(socket.getInputStream());
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			
			String command= new String(in.readUTF());
			
			if(command.equals("deposita")){
				String art=new String(in.readUTF());
				int id=in.readInt();
				skeleton.deposita(art, id);
				out.writeUTF("Registrato '"+art+"' con id "+id);
				
			}else if(command.equals("preleva")){
				String art=new String(in.readUTF());
				out.writeInt(skeleton.preleva(art));
			}
			socket.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
