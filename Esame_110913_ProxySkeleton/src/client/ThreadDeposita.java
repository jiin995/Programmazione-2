package client;

import magazzino.*;

public class ThreadDeposita extends Thread {
	
	private IMagazzino magazzino;
	
	public ThreadDeposita(IMagazzino m){
		magazzino=m;
	}
	
	public void run(){
		
		try{
			for(int i=0;i<3;i++){
				Thread.sleep(((int)(Math.random()*2000)+2000));
				if((i%2)==0)
					magazzino.deposita("laptop", ((int)(Math.random()*100)+1));
				else
					magazzino.deposita("smartphone", ((int)(Math.random()*100)+1));
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
