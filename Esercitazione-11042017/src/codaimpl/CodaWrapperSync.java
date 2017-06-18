package codaimpl;

import coda.*;

public class CodaWrapperSync extends CodaWrapper {

		public CodaWrapperSync(Coda c){
			super(c);
			
		}
		
		public void inserisci(int i){
			
			synchronized(coda){
				
				while(coda.full()){
					try{
						coda.wait();
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			
				coda.inserisci(i);
				coda.notify();
			}
		} //end inserisci
		
		public int preleva(){
			
			int x=-1;
			
			synchronized(coda){
				
				while(coda.empty()){
					try{
						coda.wait();
					}catch(InterruptedException e){
							e.printStackTrace();
					}
				}
				
				x=coda.preleva();
				coda.notify();
			}
			
			return x;
		} //end preleva
	
}