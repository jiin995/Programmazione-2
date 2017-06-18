package codaimpl;

import coda.*;
import java.util.concurrent.locks.*;

public class CodaWrapperLock extends CodaWrapper {
	
	private Lock lock;
	private Condition empty;
	private Condition full;
	
 
	public CodaWrapperLock(Coda c){
		
		super(c);
		
		lock= new ReentrantLock();
		empty= lock.newCondition();
		full= lock.newCondition();
		
		System.out.println("CodaWrapperLock");
		
		
	}
	
	public void inserisci(int i){
		
		lock.lock();
		
		try{
			while(coda.full()){
				try{
					empty.await();
				}catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			
			coda.inserisci(i);
			
			full.signal();
			
		}finally{
			lock.unlock();
		}
		
	
	}
	
	public int preleva(){
		
		int x=-1;
		
		lock.lock();

		try{
			
			while(coda.empty()){
				try{
					full.await();
				}catch(InterruptedException e){
						e.printStackTrace();
				}
			}
			
			x=coda.preleva();
			
			empty.signal();
			
		}finally{
			lock.unlock();
		}
		
		return x;
	}

}
