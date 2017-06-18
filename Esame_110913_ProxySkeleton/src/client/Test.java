package client;

import magazzino.IMagazzino;

public class Test {

	public static void main (String args[]){
		IMagazzino magazzino=new Proxy("127.0.0.1",21212);
		
		try{
			magazzino.preleva("laptop");
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
}
