package Client;

import Dispatcher.IDispatcher;
import java.net.*;
import java.io.*;

public class DispatcherProxy implements IDispatcher{
	
	private Socket connection;
	private String ip="127.0.0.1";
	private int port=8000;
	private DataInputStream	datain;
	private DataOutputStream dataout;
	
	public DispatcherProxy(){
		
		try{
			connection=new Socket(ip,port);
			
			 dataout=new DataOutputStream(connection.getOutputStream());
			 datain=new DataInputStream(connection.getInputStream());
			
		}catch(IOException e){		
			e.printStackTrace();
		}
	}

	public DispatcherProxy(String s,int p){
		
		ip=s;
		port=p;
		
		try{
			connection=new Socket(ip,port);
			
			dataout=new DataOutputStream(connection.getOutputStream());
			datain=new DataInputStream(connection.getInputStream());
			
		}catch(IOException e){		
			e.printStackTrace();
		}
		
	}
	
	public void sendCmd(int cmd){
		
		try{
			dataout.writeUTF("sendCmd");
			dataout.writeInt(cmd);
			
			
			datain.readUTF();//E' solo un meccanismo per forzare il proxy ad attendere
			
		}catch(IOException e){
			e.printStackTrace();
		}

	}

	public int getCmd(){
		int x=-1;
		try{
			dataout.writeUTF("getCmd");
			x= datain.readInt();
			
		}catch(IOException e){
			e.printStackTrace();
		}
			return x;
			
	}
	
	public void close(){
		try{
			dataout.writeUTF("close");
			connection.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}


}
