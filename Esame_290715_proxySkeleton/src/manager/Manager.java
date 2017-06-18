package manager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Manager {
	
	public static void main(String args[]){
		
		try{
			
			Socket s=new Socket("127.0.0.1",23232);
			
			DataOutputStream out=new DataOutputStream(s.getOutputStream());
			DataInputStream in=new DataInputStream(s.getInputStream());
			
			out.writeUTF("addPrinter");
			out.writeUTF(args[0]);
			out.writeInt(Integer.valueOf(args[1]));
			
			System.out.println("Esito registrazione di "+args[0]+" "+args[1]+" :"+in.readBoolean());
			
			out.close();
			in.close();
			s.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
