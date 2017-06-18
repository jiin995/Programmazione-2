package server;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.net.*;

import pizza.Pizza;

public class ServerThread extends Thread {
	
	private Socket s;
	
	public ServerThread (Socket ss){
		s=ss;
	}
	
	public void run(){
		
		try{
			
			System.out.println("Thread avviato lavora sula socket "+s.getPort());
			
			ObjectInputStream in=new ObjectInputStream(s.getInputStream());
			DataOutputStream out =new DataOutputStream(s.getOutputStream());
			
			Pizza piz=(Pizza)in.readObject();
			piz.getPizza();
			
			Thread.sleep((int)(Math.random()*50000));
			
			//wr.write("Ordine Effettuato");
			out.writeUTF("Ordine Confermato");
			
			s.close();

		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException c){
			c.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
