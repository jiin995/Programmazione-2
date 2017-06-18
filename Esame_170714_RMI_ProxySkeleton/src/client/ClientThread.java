package client;

import java.rmi.RemoteException;

import gestore.IGestore;

public class ClientThread  extends Thread{
	
	IGestore gestore;
	int nRichieste;
	
	public ClientThread(IGestore gest,int n,int i){
		super("[Client "+i+"]");
		gestore=gest;
		nRichieste=n;
	}
	
	public void run(){
		
		try{
			
			System.out.println(Thread.currentThread().getName()+" Avviato");
			Thread.sleep((int )(1000+Math.random()*3000));
			
			for(int i=0;i<nRichieste;i++){
				int id_articolo=1+(int)(Math.random()*100);
				
				System.out.println("[Client] invio richiesta di deposito "+id_articolo);
				
				gestore.richiestaDeposito(id_articolo);
			}
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(RemoteException e){
			e.printStackTrace();
		}
		
	}

}
