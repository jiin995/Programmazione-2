/*
 * classe wrapper che aggiunge a coda la parte di concorrenza 
 */

package codaimpl;

import coda.*;


/*
 * implementazione della coda circolare --> lock free
 * deve necessariamente implementare i metodi che sono definiti in coda.Coda
 */

public class CodaCircolare implements Coda {
	
	
	private int data[];	//gli elementi sono memorizzati in un array gestito in maniera circolare
	
	private int size;
	private int elem;

	
	private int tail;
	private int head;
		
	

	public CodaCircolare( int s){
		size=s;
		elem=0;
		data = new int[size];
		tail=head=0;
	}
	
	// metodi di utilita'

	public boolean full(){
		if ( elem == size )
			return true;
		return false;
	}
	
	public boolean empty(){
		if( elem == 0)
			return true;
		return false;
	}
	
	public int getSize(){
		return size;
	}
	

	//inserisce un elemento 
	
	public void inserisci( int i ){
		
		try{
			Thread.sleep(500);	
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		data[ head%size ] =i;
		elem=elem+1;
		
		System.out.println ("inserito " + i + " , numero elementi: " + elem);
		
		head=head+1;
	}
	
	
	//estrae un elemento 
	public int preleva (){
		
		try{
			Thread.sleep(2000);	
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		
		int x = data[ tail%size ];
		elem=elem-1;
		
		System.out.println ("prelevato " + x + " , numero elementi: " + elem);
		
		tail=tail+1;
		return x;
	}
	
	

}
