package printer;

import java.net.*;
import java.io.*;

public class PrinterSkeleton implements IPrinter {
	
	private int port;
	
	public PrinterSkeleton(int p){
		port=p;
	}
	
	public void runSkeleton(){
		
		try{
			
			@SuppressWarnings("resource")
			ServerSocket server=new ServerSocket(port);
			
			while(true){
				Socket s=server.accept();
				
				DataOutputStream out=new DataOutputStream(s.getOutputStream());
				DataInputStream in =new DataInputStream(s.getInputStream());
				
				if(in.readUTF().compareTo("print")==0){
					String docName=in.readUTF();
					out.writeBoolean(this.print(docName));
				}else{
					out.close();
					in.close();
					s.close();
				}
				
				out.close();
				in.close();
				s.close();
				
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public boolean print(String docName){
		try{
			
			//Thread.sleep((int)(10000+Math.random()*6000));
			Thread.sleep(1);
			System.out.println("[Printer ]--> Stampo"+docName);
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return true;
	}
	
	

}
