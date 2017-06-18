package client;

import magazzino.IMagazzino;

public class ClientB {
	public static void main (String args[]){
		IMagazzino magazzino=new Proxy("127.0.0.1",21212);
		
		ThreadPreleva tPreleva[]=new ThreadPreleva[5];
		
		for(int i=0;i<5;i++){
			tPreleva[i]=new ThreadPreleva(magazzino);
				tPreleva[i].start();
		}
		
		for(int i=0;i<5;i++){
			try{
				tPreleva[i].join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		System.out.println("Tutti i thread sono termianti");
	}

}
