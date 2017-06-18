package server;

import java.rmi.*;
import java.util.concurrent.Semaphore;

import banca.*;

public class SportelloImpl implements ISportello{

	private Semaphore sem;
	private Semaphore sem2;
	
	public SportelloImpl()throws RemoteException{
		sem=new Semaphore(3);
		sem2=new Semaphore(5);
	}
	
	public boolean serviRichiesta(int idClient) throws RemoteException{
	
		if(sem2.tryAcquire())	{
			try{	
		
				sem.acquire();
		
				try{
					System.out.println("Inizio richiesta id "+idClient+ " , "+Thread.currentThread().getName());
					Thread.sleep(10000);
					System.out.println("Fine Richiesta id"+idClient);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					sem.release();
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}finally{
				sem2.release();
			}
			return true;
		}
		else
			return false;	
	}
	
}
