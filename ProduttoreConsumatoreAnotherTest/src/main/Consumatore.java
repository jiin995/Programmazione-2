package main;

import buffer.*;

public class Consumatore extends Thread{
	
	private Buffer b;
	
	public Consumatore(Buffer in,int i){
		super("THREAD--"+i);
		b=in;
		
	}
	
	public void run(){
		
		for(int i=0;i<10;i++){
			try{
				
				System.out.println("["+Thread.currentThread().getName()+"] Consumato <--"+b.Consuma());
				Thread.sleep((int)(Math.random()*1000));;
				
			}catch(InterruptedException e){
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	

}
