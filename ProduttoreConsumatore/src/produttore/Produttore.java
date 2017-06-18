package produttore;

import buffer.Buffer;

public class Produttore extends Thread {
	
	private Buffer buffer;
	
	public Produttore(Buffer b){
		super(Object.class.getName());
		buffer=b;
	}
	
	public void run(){
		
		for(int i=0;i<10;i++){
			buffer.produci();
			try{
				Thread.sleep((int)(Math.random()*10000));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	

}
