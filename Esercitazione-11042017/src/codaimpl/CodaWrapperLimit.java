package codaimpl;

import coda.*;

import java.util.concurrent.locks.*;

public class CodaWrapperLimit extends CodaWrapper{
	
	private Lock lock;
	private Condition empty;
	private Condition full;
	private Lock contIns;
	private Lock contPre;

	private static volatile int maxThread=1;
	private static volatile int threadsIns=0;
	private static volatile int threadsPre=0;
	
	
	public CodaWrapperLimit(Coda c){
		super(c);
		
		lock=new ReentrantLock();
		empty=lock.newCondition();
		full=lock.newCondition();
		contIns=new ReentrantLock();
		contPre=new ReentrantLock();
		
	}
	
	public void inserisci(int i){
		
		
		try{
				Thread.sleep(1+(int)(Math.random()*10000));
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		if(contIns.tryLock()){
			lock.lock();
			try{
					while(coda.full()){
						try{
								empty.await();
						}catch(InterruptedException e){
							e.printStackTrace();
						}
				
					threadsIns++;
					
					if(threadsIns==maxThread)
						contIns.lock();
					
					coda.inserisci(i);
					full.signal();
					
					threadsIns--;
				}
			
			}finally{
				contIns.unlock();
				lock.unlock();
			}
		}
		else
			System.out.println("**Numero di Thread per l'inserimento superato "+Thread.currentThread().getName()+" Terminato ");
	}
	
	public int preleva(){
		int x=-1;
		
		
		try{
			Thread.sleep(1+(int)(Math.random()*10000));
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		lock.lock();
		
		if(contPre.tryLock()){
			try{
			
					while(coda.empty()){
						try{
							full.await();
						}catch(InterruptedException e){
							e.printStackTrace();
						}
					}	
				
						threadsPre++;
						
						if(threadsPre==maxThread)
							contPre.lock();
						
						x=coda.preleva();
						empty.signal();
						threadsPre--;			

					
			}finally{
				lock.unlock();
				contPre.unlock();
			}
		}else
			System.out.println("**Numero di Thread per il prelievo superato "+Thread.currentThread().getName()+" Terminato ");
		return x;
	}

}
