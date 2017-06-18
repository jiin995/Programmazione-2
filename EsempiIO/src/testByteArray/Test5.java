package testByteArray;

import java.io.*;
import testClass.*;


//Write Object to file 
public class Test5 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			
			TestCLass test=new TestCLass("ciao cazzone");
			
			FileOutputStream fo=new FileOutputStream("./testObject");
			BufferedOutputStream bfo=new BufferedOutputStream(fo);
			
			ObjectOutputStream oo=new ObjectOutputStream(bfo);
			
			oo.writeObject(test);
			
			bfo.flush();
			oo.close();
			
			
		}catch(IOException e){
			e.printStackTrace();
			
		}
	}

}
