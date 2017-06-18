package main;


import buffer.*;

public class Tester {

	public static void main(String[] args) {
		Thread t;
		
		Buffer b=new Buffer(3);

		for(int i=0;i<10;i++){
			if(i%2==0)
				t=new Produttore(b,i);
			else
				t=new Consumatore(b,i);
			t.start();
		}
		
	}

}
