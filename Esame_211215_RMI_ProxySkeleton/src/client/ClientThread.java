package client;

import java.rmi.RemoteException;

import interfaces.IDispatcher;

public class ClientThread extends Thread {
	
	private IDispatcher dispatcher;
	
	public ClientThread( IDispatcher d,int i){
		super("[Client Thread "+i+"]");
		dispatcher=d;
	}
	
	public void run(){
		
		try{
			
			for(int i=0;i<10;i++){
				switch((int)(Math.random()*4)){
			
					case 0:{
							dispatcher.sendCmd("write");
							break;
					}
					case 1:{
							dispatcher.sendCmd("read");
							break;
					}
					case 2:{
							dispatcher.sendCmd("stop");
							break;
					}
					case 3:{
							dispatcher.sendCmd("start");
							break;
					}
				}
			}
		}catch(RemoteException e){
			e.printStackTrace();
		}
	}

}
