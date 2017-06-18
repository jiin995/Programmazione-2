package Server;

import Dispatcher.*;
import java.net.*;
import java.io.*;

public class DispatcherSkeletonD implements IDispatcher{
		
	private int port=8000;
	private ServerSocket server;
	private IDispatcher delegato;
	
	public void close(){};
	
	public DispatcherSkeletonD(IDispatcher in){
		
		delegato=in;
		
		try{
			server=new ServerSocket(port);
		
		}catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void runSkelenton(){
		
		boolean connesso=false;
		Socket s=null;
		DataInputStream datain=null;
		DataOutputStream dataout=null;
		
		while(true){

			try{
				
				if(!connesso){
					System.out.println("ASPETTO");
					s=server.accept();
					datain = new DataInputStream(s.getInputStream());
					dataout =new DataOutputStream(s.getOutputStream());
					connesso=true;
				}else{
				
					String cmd=datain.readUTF();
					int x=0;
				
					System.out.println(cmd);

					if(cmd.compareTo("sendCmd")==0){
					
						x=datain.readInt();
						System.out.println(" +method='"+cmd+"',"+x);
					
						//invocazione oggetto reale
					
						dataout.writeUTF("ciao");
					}
					else if(cmd.compareTo("getCmd")==0) {
						System.out.println(" +method='"+cmd+"',"+x);
					
						dataout.writeInt(x);
					}
					else if(cmd.compareTo("close")==0){
						s.close();
						connesso=false;
					}
					else{
						System.out.println("Comando Sconosciuto");
					}			

				}
			}catch(IOException e){
				e.printStackTrace();
				return ;
			}
	}

}
	public void sendCmd(int cmd){
		
		delegato.sendCmd(cmd);
	}
	
	public int getCmd(){
		
		return delegato.getCmd();
	}


}
