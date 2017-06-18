package gestore;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import deposito.IDeposito;

public class DepositoProxy implements IDeposito {

	private String address;
	private int port;
	
	public DepositoProxy(String add,int p){
		address=new String(add);
		port=p;
	}
	
	public boolean deposita(int id_articolo){
		boolean result=false;
		
		try{
			
			Socket socket=new Socket(address,port);
			
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			DataInputStream in=new DataInputStream(socket.getInputStream());
			
			out.writeUTF("deposita");
			out.writeInt(id_articolo);
			
			result=in.readBoolean();
			
			out.close();
			in.close();
			socket.close();
			
		}catch(IOException e){
			e.printStackTrace();
			
			return false;
		}
		
		return result;
	}
}
