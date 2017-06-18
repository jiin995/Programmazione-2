package buffer;

//Uso i semafori e la coda circolare

import java.util.concurrent.Semaphore;

public class Buffer {
	
	private Semaphore space_available;
	private Semaphore means_available;
	private Object mutex_C;
	private Object mutex_P;
	
	CodaCircolare coda;
	
	public Buffer(int s){
		coda=new CodaCircolare(s);
		space_available=new Semaphore(s);
		means_available= new Semaphore(0);
		mutex_C=new Object();
		mutex_P=new Object();
	}
	
	public int Consuma()throws Exception{
		int x=0;
		
		try{
			synchronized(mutex_C){
				means_available.acquire();
				x=coda.getValue();
				space_available.release();
			}

		}catch (InterruptedException e){
			e.printStackTrace();
		}
		return x;
	}
	
	public synchronized void Produci(int in)throws Exception{	
		try{
			synchronized(mutex_P){
				space_available.acquire();
				coda.addValue(in);
				means_available.release();	
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
