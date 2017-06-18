package agenzia;

import java.util.concurrent.locks.*;

public class AgenziaImpl implements IAgenzia {
	
	private int biglietti=10;
	private Lock mutex;
	private Condition fineBiglietti;
	
	public AgenziaImpl(){
		mutex=new ReentrantLock();
		fineBiglietti=mutex.newCondition();
	}
	
	public boolean acquista(int qnt){
		
		try{
			mutex.lock();
			Thread.sleep((int)(4000+Math.random()*5000));
			while(biglietti<qnt){
				fineBiglietti.await();
			}
			biglietti=biglietti-qnt;
		}catch(InterruptedException e){
			e.printStackTrace();
			return false;
		}finally{
			mutex.unlock();
		}
		return true;
		
	}
	public boolean vendi(int qnt){
		
		try{
			mutex.lock();
			Thread.sleep((int)(4000+Math.random()*5000));
				biglietti=biglietti+qnt;
				fineBiglietti.signal();
				
		}catch(InterruptedException e){
			e.printStackTrace();
			return false;
		}finally{
			mutex.unlock();
		}
		
		return true;
	}

}
