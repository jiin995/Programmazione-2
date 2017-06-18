package main;

import buffer.*;

public class Produttore extends Thread{
	
	Buffer b;
	
	public Produttore(Buffer in,int i){
		super("THREAD"+i);
		b=in;
	}
	
	public void run() {
		
		for(int i=0;i<10;i++){
			try{
				
				int x=(int)(Math.random()*10);
				b.Produci(x);
				System.out.println("["+Thread.currentThread().getName()+"] Prodotto-->"+x);
				Thread.sleep((int)(Math.random()*1000));
				
			}catch(InterruptedException e){
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
	}

}
