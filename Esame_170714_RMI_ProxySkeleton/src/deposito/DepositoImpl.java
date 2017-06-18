package deposito;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.concurrent.locks.*;;

public class DepositoImpl implements IDeposito{
	
	private Lock mutex;
	private PrintStream file;
	private int id_richiesta;
	
	public DepositoImpl(String nomeFile){
		id_richiesta=0;
		mutex=new ReentrantLock();
		try{
			file=new PrintStream(new BufferedOutputStream(new FileOutputStream("./"+nomeFile)));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public boolean deposita(int id_articolo){
		
		try{
			
			mutex.lock();
				Thread.sleep((int)(5000+Math.random()*6000));
				
				System.out.println(Thread.currentThread().getName()+"\n"+id_richiesta+"	Ricevuta richiesta di deposito : "+id_articolo+" sul file ");
				file.println((id_richiesta++)+"]		"+id_articolo);
				file.flush();
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			mutex.unlock();
		}
		
		return true;
	}

}
