package Main;

import produttore.Produttore;
import consumatore.Consumatore;
import buffer.Buffer;
import java.io.*;

public class Tester {
	
	public static void main(String[] args){
		
		Buffer b=new Buffer();
		
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		int choice=0;
		
		while(true){
		
			try{
				choice =Integer.parseInt(stdin.readLine());
			}catch(IOException e){e.printStackTrace();}
			
			if(choice ==0){
				Consumatore c=new Consumatore(b);
				c.start();
			}
			else{
				Produttore p=new Produttore(b);
				p.start();
			}		
		}
		
		
		
	}
	
}
