package dispatcher;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.concurrent.Semaphore;

import codaCircolare.CodaCircolare;

public class DispatcherImpl implements IDispatcher {
	
	CodaCircolare commandQueue;
	Semaphore spaceAvailable;
	Semaphore elemAvailable;
	Semaphore maxRequest;
	Semaphore suspendQueue;
	int maxReq=3;
	int size;
	PrintStream fout;
	
	public DispatcherImpl(int dimCommandQueue){
		size=dimCommandQueue;
		commandQueue=new CodaCircolare(size);
		spaceAvailable=new Semaphore(size);
		elemAvailable=new Semaphore(0);
		maxRequest =new Semaphore(maxReq);
		suspendQueue=new Semaphore(0);
		try{
			fout=new PrintStream(new BufferedOutputStream(new FileOutputStream("./cmdlog.txt")));
		}catch(FileNotFoundException n){n.printStackTrace();}
	}
	
	public boolean invia(String command){
		
		try{
			if(!spaceAvailable.tryAcquire()) { //se non c'e' spazio vedo se posso accodare
				if(!maxRequest.tryAcquire()){
					System.out.println(Thread.currentThread().getName()+" Numero di richieste massime raggiunte");
					return false;
				}else{
					System.out.println(Thread.currentThread().getName()+" Spazio nella coda esaurito, inizio ad accodare Richeste disponibili "+maxRequest.availablePermits());
					suspendQueue.acquire(); //accodo la richiesta sospendendo il thread
					spaceAvailable.acquire();
				}
			}
				commandQueue.addElem(command);
				if(elemAvailable.availablePermits()<size){
					elemAvailable.release();
				}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return true;
		
	}
	public String esegui(){
		
		String command=new String("");
		try{
			elemAvailable.acquire();
			command=new String(commandQueue.getElem());
			fout.println(command);
			fout.flush();
			if(spaceAvailable.availablePermits()<size){
				spaceAvailable.release();
				//System.out.println(spaceAvailable.availablePermits());
			}	
			if(maxRequest.availablePermits()<maxReq){
				maxRequest.release();
				suspendQueue.release();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return command;
	}

}
