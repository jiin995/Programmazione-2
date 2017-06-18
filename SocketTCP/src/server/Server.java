package server;

import java.io.IOException;
import java.net.*;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		try{		
			@SuppressWarnings("resource")
			ServerSocket server=new ServerSocket(32323);
			
			while(true){
				Socket s=server.accept();
				ServerThread t=new ServerThread(s);
				t.start();
			}			
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
