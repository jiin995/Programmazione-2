package client;

import magazzino.IMagazzino;

public class ClientA {

	public static void main (String args[]){
		IMagazzino magazzino=new Proxy("127.0.0.1",21212);
		
		ThreadDeposita tDeposita[]=new ThreadDeposita[5];
		
		for(int i=0;i<5;i++){
			tDeposita[i]=new ThreadDeposita(magazzino);
				tDeposita[i].start();
		}
		
		for(int i=0;i<5;i++){
			try{
				tDeposita[i].join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		System.out.println("Tutti i thread sono termianti");
	}
}
