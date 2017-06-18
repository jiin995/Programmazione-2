package actuator;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import interfaces.IActuator;

public class ActuatorSkeletonD implements IActuator {
	
	private int port;
	private ServerSocket server;
	private IActuator actuator;
	
	public ActuatorSkeletonD(int p,IActuator act){
		port=p;
		actuator=act;
	}
	
	public void runSKeleton(){
		try{
			
			server=new ServerSocket(port);
			System.out.print("[Actuator :"+InetAddress.getLocalHost().getHostName()+":"+port+"] SottoScritto e pronto \n");
			
			while(true){
				
				Socket s=server.accept();
				
				DataInputStream in=new DataInputStream(s.getInputStream());
				DataOutputStream out=new DataOutputStream(s.getOutputStream());
				
				String command=in.readUTF();
				boolean x=actuator.executeCmd(command);

				
				System.out.println("[Actuator :"+InetAddress.getLocalHost().getHostAddress()+":"+port+"] \n Ricevuto Comando :"+command+" Risulato :"+x);
				
				out.writeBoolean(x);
				
				s.close();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public boolean executeCmd(String command){
		return actuator.executeCmd(command);
	}

}
