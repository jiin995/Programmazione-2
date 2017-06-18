package testByteArray;

import java.io.*;
import testClass.TestCLass;

//Read Object to file
public class Test6 {
	
	public static void main(String []args){
		try{
			
			BufferedInputStream bif=new BufferedInputStream(new FileInputStream("./testObject"));
			ObjectInputStream oi=new ObjectInputStream(bif);
			
			TestCLass test=(TestCLass) oi.readObject();
			
			System.out.println("Letto dal file -->" +test.getSS());
			
			oi.close();
			bif.close();
			
			
			
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException c){
			c.printStackTrace();
		}
	}
}
