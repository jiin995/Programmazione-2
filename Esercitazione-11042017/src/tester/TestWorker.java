/*
 * massimo un certo numero di lock dche si possono acquisire
 */

package tester;

import coda.*;
import codaimpl.*;

public class TestWorker {

	public static void main(String [] args){
		
		Coda codacirc=new CodaCircolare(5);
		Coda coda=new CodaWrapperLimit(codacirc);
		
		//Coda coda=new MyCoda();
		
		Worker[] worker =new Worker[100];
		
		for(int i=0;i<100;i++){
			
			if(i%2==0)
					worker[i]=new Worker(coda,true); //Produttore
			else
					worker[i]=new Worker(coda,false); //Consumatore
			worker[i].start();
		}
		
		for(int i=0;i<100;i++){
			
			try{
				worker[i].join();
			}catch(InterruptedException e){
				
				e.printStackTrace();
				
			}
		}
		
		System.out.println("Tutti i thread hanno terminato la loro esecuzione");
	}
}//end class
