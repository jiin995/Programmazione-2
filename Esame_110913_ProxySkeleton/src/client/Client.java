package client;

import magazzino.IMagazzino;

public class Client {
	
	public static void main (String args[]){
		IMagazzino magazzino=new Proxy("127.0.0.1",21212);
		
		try{
			for(int i=0;i<6;i++){
				magazzino.deposita("laptop", i);
				Thread.sleep((int)(Math.random()*10000));
			}
			for(int i=0;i<6;i++){
				magazzino.preleva("laptop");
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
