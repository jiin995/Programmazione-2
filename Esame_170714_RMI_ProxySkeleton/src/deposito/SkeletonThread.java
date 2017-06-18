package deposito;

import java.net.*;
import java.io.*;

public class SkeletonThread extends Thread {
	
	private Socket socket;
	private IDeposito deposito;
	
	public SkeletonThread(Socket s,IDeposito dep,int i){
		super("[SkeletonThread "+i+"]");
		socket=s;
		deposito=dep;
	}
	
	public void run(){
		
		try{
			System.out.println(Thread.currentThread().getName()+" Avviato");
			
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			DataInputStream in=new DataInputStream(socket.getInputStream());
			
			String command=in.readUTF();
			int id_articolo=in.readInt();
			
			if(command.equals("deposita")){
				out.writeBoolean(deposito.deposita(id_articolo));
			}else{
				out.writeBoolean(false);
			}
			
			out.close();
			in.close();
			socket.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	


}
