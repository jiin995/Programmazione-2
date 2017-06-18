package client;

import dispatcher.IDispatcher;

public class ClientThread extends Thread  {
	
	private IDispatcher dispatcher;
	
	public ClientThread (IDispatcher d,int id){
		super("[Client Thread "+id+" ]");
		dispatcher=d;
		
	}
	
	public void run(){
		
		try{
			for(int i=0;i<3;i++){
				Thread.sleep(((int)((Math.random()*2000))+2000));
				int s=(int)(Math.random()*10)%3;
				switch(s){
					case 0:{
						System.out.println(Thread.currentThread().getName()+" "+i+"  --> iviato scrivi"+" Esito "+dispatcher.invia("scrivi"));
						break;
					}
				
					case 1:{
						System.out.println(Thread.currentThread().getName()+" "+i+" --> iviato configura"+" Esito "+dispatcher.invia("configura"));
						break;
					}
					case 2:{
						System.out.println(Thread.currentThread().getName()+" "+i+" --> iviato leggi"+" Esito "+dispatcher.invia("leggi"));
						break;
					}
				}
			}
			
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}

}
