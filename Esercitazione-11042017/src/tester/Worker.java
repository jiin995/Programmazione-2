package tester;

import coda.*;

public class Worker extends Thread{
	
	private boolean isProd=true; //di default il thread e' un produttore inserisce nelal coda
	
	private Coda coda;
	
	public Worker (Coda c, boolean p){
		
		coda=c;
		isProd=p;
		
	}
	
	// non fare il coglione invoca start e non il metodo run
	public void run(){
		
		if(isProd)
				coda.inserisci(1+(int)(Math.random()*100));
	
		else
				coda.preleva();
	}
	

}
