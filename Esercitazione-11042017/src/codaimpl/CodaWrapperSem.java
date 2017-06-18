package codaimpl;

import java.util.concurrent.*;
import coda.*;

public class CodaWrapperSem extends CodaWrapper {

	private Semaphore spazioDisp;
	private Semaphore elemDisp;


	
	public CodaWrapperSem(Coda c){
		super(c);		
		System.out.println("Costruita obj CodaWrapperSem");
		
		spazioDisp=new Semaphore(coda.getSize());
		elemDisp= new Semaphore(0);
	}
	
	public void inserisci(int i){
		
		try{
			spazioDisp.acquire();	//se non c'e' spazio questo metodo mette in sospensione il nostro thread
			try{
				
				synchronized (coda) {		//perche' non abbiamo in esecuzione un solo produttore o un solo consumatore invece di mettere altri due mutex usiamo il monitor built-in in java
					coda.inserisci(i);
				}				
			}finally{
				elemDisp.release(); //in caso di eccezzione devo essere sicuro che il permesso sia rilasciato sopra il sem
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int preleva(){
		int x=-1;
		
		try{
			elemDisp.acquire();
			
			try{
				
				synchronized(coda){
					x=coda.preleva();
				}
				
			}finally{
				spazioDisp.release();
			}
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		
		return x;
	}
	
	
}
