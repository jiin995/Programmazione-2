package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

import Dispatcher.IDispatcher;



public class ServerThread extends Thread {
	
	private Socket s;
	private IDispatcher dispatcher; 
	
	public ServerThread(Socket sk,IDispatcher disp){
		
		s=sk;
		dispatcher=disp;
		
	}
		
	
	
	public void run(){
		
		boolean connesso=true;

		System.out.println("Thread Di Servizio");
		
		while(connesso){
			try{
			
				DataInputStream datain = new DataInputStream(s.getInputStream());
				DataOutputStream dataout =new DataOutputStream(s.getOutputStream());
				
				String cmd=datain.readUTF();
				int x=0;
				
				if(cmd.compareTo("sendCmd")==0){
					
					x=datain.readInt();
					System.out.println(" +method='"+cmd+"',"+x);
					
					dispatcher.sendCmd(x);
					
					dataout.writeUTF("ciao");
				}
				else if(cmd.compareTo("getCmd")==0) {
					System.out.println(" +method='"+cmd+"',"+x);
					x=dispatcher.getCmd();
					dataout.writeInt(x);
				}
				else if(cmd.compareTo("close")==0){
					s.close();
					connesso=false;
				}
				else{
					System.out.println("Comando Sconosciuto");
				}
				
			}catch(IOException e){
				e.printStackTrace();
			}
			
		}
			
	}
	
}
	

