package testByteArray;

import java.io.*;


public class Test4 {

	//Random access file
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			
			File filein=new File("./test.txt");
			RandomAccessFile raf=new RandomAccessFile(filein, "rw");
			
			raf.seek(0);
			
			
			BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
			String s=raf.readLine();

			
			System.out.println("Letto --> "+s );
			
			s=stdin.readLine();
			
			raf.seek(raf.length());
			raf.writeBytes("\r\n"+s);

			raf.close();
	
		}catch(IOException e){
			e.printStackTrace();
		}finally{
		}
	}

}
