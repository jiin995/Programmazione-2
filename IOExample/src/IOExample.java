import  java.io.*;

public class IOExample {

	public static void main(String[] args) {
			
		ByteArrayOutputStream out= new ByteArrayOutputStream();
		
		String s="This is String";
		
		try{
			out.write(s.getBytes());
		}
		catch (IOException e){
			e.printStackTrace();
		}
			System.out.println("Ecco il contenuto di out "+out);
			System.out.println("Ecco le sue dimensioni "+out.size());
		
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		
		int dimSt=in.available();
		
		byte inBuf[]=new byte[dimSt];
		
		int BytesRead=in.read(inBuf,0,dimSt);
		
		System.out.println("Ecco il contenuto di in "+ new String(inBuf));
		System.out.println("Ecco le sue dimensioni "+ BytesRead);
		
		File dir=new File ("./src");
		File file[];
		if(dir.isDirectory()){
			file=dir.listFiles();
			for(int i=0;i<file.length;i++){
				System.out.println("File :"+file[i].getName());
			}
		}
		
	}

}
