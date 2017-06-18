package testByteArray;

import java.io.*;

public class test1 {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		ByteArrayOutputStream output=new ByteArrayOutputStream();
		String s=new String("Ciao Mondo");
		try{
			output.write(s.getBytes());
			InputStream input=new ByteArrayInputStream(output.toByteArray());
			
			int inBytes=input.available();
			byte inBuf[]=new byte[inBytes];
			int bytesRead=input.read(inBuf,0,inBytes);
		
			System.out.println("Read :"+bytesRead+" bytes");
			System.out.println("Read String:"+new String(inBuf));
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
