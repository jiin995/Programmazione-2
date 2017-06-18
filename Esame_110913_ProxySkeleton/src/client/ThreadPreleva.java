package client;

import magazzino.IMagazzino;

public class ThreadPreleva extends Thread{
	
	private IMagazzino magazzino;
	
	public ThreadPreleva(IMagazzino m){
		magazzino=m;
	}
	
	public void run(){
		
		try{
			for(int i=0;i<3;i++){
				Thread.sleep(((int)(Math.random()*2000)+2000));
				if((i%2)==0)
					magazzino.preleva("laptop");
				else
					magazzino.preleva("smartphone");
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
