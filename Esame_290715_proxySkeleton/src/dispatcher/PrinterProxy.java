package dispatcher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import printer.IPrinter;

public class PrinterProxy implements IPrinter {
	
	private String address;
	private int port;
	
	public PrinterProxy(String add, int p){
		address=new String(add);
		port=p;
	}
	
	public boolean print(String docName){
		boolean x=false;
		
		try{
			
			Socket s=new Socket(address,port);
			
			DataOutputStream out=new DataOutputStream(s.getOutputStream());
			DataInputStream in=new DataInputStream(s.getInputStream());
			
			out.writeUTF("print");
			out.writeUTF(docName);
			x=in.readBoolean();
			
			s.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}	
		return x;
	}
	
	public String getAddress(){
		return new String(address);
	}
	
	public int getPort(){
		return port;
	}
	

}
