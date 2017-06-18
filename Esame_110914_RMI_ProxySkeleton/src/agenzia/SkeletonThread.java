package agenzia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SkeletonThread extends Thread{
	
	private Socket socket;
	private IAgenzia agenzia;
	
	public SkeletonThread (Socket s,int i,IAgenzia agen){
		super("[SkeletonThread "+i+"]");
		socket=s;
		agenzia=agen;
	}
	
	public void run(){
		
		try{
			
			DataInputStream in=new DataInputStream(socket.getInputStream());
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());

			boolean result=false;
			String request=in.readUTF();
			int qnt=in.readInt();
			
			
			if(request.equals("acquista")){
				result=agenzia.acquista(qnt);
				System.out.println(Thread.currentThread().getName()+" Ricevuta richiesta di acquisto per "+qnt+" biglietti \n"
						+ "Risultato :"+result);
			}else if (request.equals("vendi")){
				result=agenzia.vendi(qnt);
				System.out.println(Thread.currentThread().getName()+" Ricevuta richiesta di vendita per "+qnt+" biglietti \n"
						+ "Risultato :"+result);
			}
			out.writeBoolean(result);
			
			in.close();
			out.close();
			socket.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
