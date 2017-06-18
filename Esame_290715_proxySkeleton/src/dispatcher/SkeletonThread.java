package dispatcher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class SkeletonThread extends Thread{
	
	private IDispatcher dispatcher;
	private Socket socket;
	
	public SkeletonThread(IDispatcher d, Socket s,int i){
		super("[ Skeleton Thread "+i+"]");
		dispatcher=d;
		socket=s;
	}
	
	public void run(){
		
		try{
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			DataInputStream in=new DataInputStream(socket.getInputStream());
			
			String command=in.readUTF();
			
			if(command.compareTo("printRequest")==0){
				String docName=in.readUTF();
				out.writeBoolean(dispatcher.printRequest(docName));;
			}else if(command.compareTo("addPrinter")==0){
				String address=in.readUTF();
				int port=in.readInt();
				out.writeBoolean(dispatcher.addPrinter(address, port));
			}
			
			out.close();
			in.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	

}
