package consumatore;

import buffer.Buffer;

public class Consumatore extends Thread {
	
	private Buffer buffer;
	
	public Consumatore(Buffer b){
		super(Object.class.getName());
		buffer=b;
	}
	
	public void run(){
	
		for (int i=0;i<10;i++){
			buffer.consuma();
			try{
				Thread.sleep((int)(Math.random()*10000));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

}
