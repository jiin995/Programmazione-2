package readingsGenerator;

import java.rmi.RemoteException;

import controller.IController;

public class ReadingThread extends Thread{
	
	private IController controller;
	
	public ReadingThread(IController c,int i){
		super("[Reading Thread "+i+"]");
		controller=c;
	}
	
	public void run(){
		try{
			while(true){
				Thread.sleep((int)(1+Math.random()*5));
				int sensor=(int)(1+Math.random()*5);
				int temp=(int)(16+Math.random()*15);
						
				System.out.println(Thread.currentThread().getName()+" Invio nuova temperatura al senosre "+sensor+" Temp= "+temp);
				controller.sendReading(sensor, temp);
			}
		}catch(RemoteException e){
			e.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
