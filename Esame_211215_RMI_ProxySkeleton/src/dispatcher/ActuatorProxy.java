package dispatcher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfaces.IActuator;

public class ActuatorProxy implements IActuator{
	
	
	private int port;
	private String address;
	
	public ActuatorProxy(String add,int p){
		address=new String (add);
		port=p;
	}
	
	public boolean executeCmd(String command){
		boolean x=false;
		
		try{
			Socket socket=new Socket(address,port);
			
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			DataInputStream in=new DataInputStream(socket.getInputStream());
			
			out.writeUTF(command);
			x=in.readBoolean();
			
			socket.close();
			
		}catch(IOException e){
			e.printStackTrace(); 
		}
		return x;
	}

}
