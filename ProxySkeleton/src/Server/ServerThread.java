package Server;

import java.io.*;
import java.net.*;
import Dispatcher.IDispatcher;

public class ServerThread extends Thread {
	
	private Socket s;
	private IDispatcher dispatcher;
	
	public ServerThread(Socket sk,IDispatcher d){
		s=sk;
		dispatcher=d;
	}
	
	public void run(){
		
		System.out.println("  [ServeThread] run Thread !");
		
		try{
			
			DataOutputStream dataOut= new DataOutputStream(s.getOutputStream());
			DataInputStream dataIn = new DataInputStream(s.getInputStream());
			
			String operations=dataIn.readUTF();
			int x;
			
			if(operations.compareTo("getCmd")==0){
					x= dispatcher.getCmd();
						System.out.println ("	[SrvThread] method: " + operations + ", " + x);
					dataOut.writeInt(x);
				
			}else if (operations.compareTo("sendCmd")==0){	
					x=dataIn.readInt();
					dispatcher.sendCmd(x);	
						System.out.println ("	[SrvThread] method: " + operations + ", " + x);
					dataOut.writeUTF("ACK");
			}else 
					System.out.println("ERROR");
			
			s.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}//end run method
	

}
