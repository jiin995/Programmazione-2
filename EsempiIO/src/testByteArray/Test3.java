package testByteArray;

import java.io.*;

public class Test3 {
	
	//test reader e writer
	
	public static void main(String[] args) {
		
		try{
			FileWriter fw=new FileWriter("./testWriter");
			BufferedWriter bw=new BufferedWriter(fw);
		
			
			bw.write("ciao a tutti");
			Thread.sleep(10000);
			bw.flush();
			
			bw.close();
			fw.close();
			
			FileReader fr=new FileReader("./testWriter");
			BufferedReader br=new BufferedReader(fr);
			
			String s=br.readLine();
			
			System.out.println("Letto "+s);
			
			br.close();
			fr.close();
			
		}catch(FileNotFoundException f){
			f.printStackTrace();	
		}catch(IOException e){
			e.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
