/*
 * creata giusto per far vedere il polimorfismo
 */

package codaimpl;

import coda.*;

public class MyCoda implements Coda{
	
	public void inserisci(int i){
		System.out.println("*inserito*");
	}

	public int preleva(){
		System.out.println("*prelevato*");
		return 12;
	}
	
	public boolean empty(){return false;}
	public boolean full(){return false;}
	
	public int getSize(){return 1;}
	
	
	
}
