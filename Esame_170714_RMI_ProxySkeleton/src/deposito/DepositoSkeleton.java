package deposito;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DepositoSkeleton implements IDeposito{
	
	private int port;
	private IDeposito deposito;
	private int nThread;
	
	public DepositoSkeleton(IDeposito dep,int p){
		deposito=dep;
		port=p;
		nThread=0;
	}
	
	public void runSkeleton(){
		
		try{
			
			@SuppressWarnings("resource")
			ServerSocket server=new ServerSocket(port);
			
			System.out.println("[SkeletonDeposito "+port+" ] Avviato");
			
			while(true){
				Socket socket=server.accept();
				SkeletonThread thread=new SkeletonThread(socket,this,nThread++);
				thread.start();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public boolean deposita(int id_articolo){
		return deposito.deposita(id_articolo);
	}

}
