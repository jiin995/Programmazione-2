package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URL page=null;
			try{
				page=new URL("http://www.grid.unina.it/index.php");

			}catch(MalformedURLException e){
				e.printStackTrace();
			}
			
			try{
				URLConnection conn=page.openConnection();
				conn.connect();
				BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;
				StringBuffer buf=new StringBuffer();
				while((line=in.readLine())!=null){
					buf.append(line+"\n");
				}
				System.out.println(buf.toString());
				
			}catch(IOException e){
				e.printStackTrace();
			}
	}

}
