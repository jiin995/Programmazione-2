package testByteArray;

import java.io.*;

public class Test2{

// test io file

	//test io file 
	public static void main(String[] args) {
		
		try{
			FileOutputStream fos= new FileOutputStream("./test.txt");
			BufferedOutputStream bos=new BufferedOutputStream(fos);
			DataOutputStream out= new DataOutputStream(bos);
			PrintStream pout=new PrintStream(out);
			
			
			pout.println("ciao mondo ");
			pout.println(5);
			
			Thread.sleep(10000);
			
			pout.flush();

			pout.close();
			out.close();
			fos.close();
			
			FileInputStream fis=new FileInputStream("./test.txt");
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader br=new BufferedReader(isr);

			StreamTokenizer st=new StreamTokenizer(isr);

			st.ordinaryChar(' ');
			
			while (st.nextToken() != StreamTokenizer.TT_EOF) {
				String s=null;
				switch(st.ttype) {
				case StreamTokenizer.TT_NUMBER:
				System.out.print ( "Number: !");
				s = Double.toString(st.nval);
				break;
				case StreamTokenizer.TT_WORD:
				System.out.print ( "Word: !");
				s = st.sval; 
				break;
				default: 
				System.out.print ( "Other: !");
				s = String.valueOf((char)st.ttype); 
				}
				System.out.println( s );
			}
			
			
			//String s=br.readLine();
			//int i= Integer.valueOf(br.readLine());
			
			//System.out.println("Letta la stringa dal file:"+s+"  "+i);

			br.close();
			isr.close();
			fis.close();
			
		}catch(FileNotFoundException f){
			f.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(InterruptedException i){
			i.printStackTrace();
		}
	
		
		
	}

}
