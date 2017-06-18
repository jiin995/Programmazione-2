package client;

import java.net.*;
import java.io.*;
import pizza.Pizza;

public class Client  {
	
	public static void main(String[] args) {
		
		try{
			BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
			
			String piz=in.readLine();
			double costo=Double.valueOf(in.readLine());
			
			Pizza pizza=new Pizza(piz,costo);
			
			Socket s=new Socket("127.0.0.1", 32323);
			
			ObjectOutputStream outSocket=new ObjectOutputStream(s.getOutputStream());		
				outSocket.writeObject(pizza);
		
		//	InputStreamReader inSocket=new InputStreamReader(s.getInputStream());
			DataInputStream inSocket=new DataInputStream(s.getInputStream());
				
			String msg=new String(inSocket.readUTF());
			
			System.out.println("Aspetto conferma ordine");
			System.out.println(msg);
			
			s.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
