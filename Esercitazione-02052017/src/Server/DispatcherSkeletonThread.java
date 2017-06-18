package Server;


	import Dispatcher.*;
	import java.net.*;
	import java.io.*;

public class DispatcherSkeletonThread implements IDispatcher{
			
		private int port=8000;
		private ServerSocket server;
		private IDispatcher delegato;
		
		public void close(){};

		public DispatcherSkeletonThread(IDispatcher in){
			
			delegato=in;
			
			try{
				server=new ServerSocket(port);
			
			}catch (IOException e){
				e.printStackTrace();
			}
			
		}
		
		public void runSkelenton(){
			
			while(true){
				
				try{
					Socket s= server.accept();
					
					ServerThread th=new ServerThread(s,delegato);
					th.start();
					
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		
		
		public void sendCmd(int cmd){
			
			delegato.sendCmd(cmd);
		}
		
		public int getCmd(){
			
			return delegato.getCmd();
		}



}
