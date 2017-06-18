package sensor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SensorSkeleton implements ObserverSensor{
	
	private int port;
	
	public SensorSkeleton(int p){
		port=p;
	}
	
	public void runSkeleton(){
		try{
			System.out.println("Skeleton in esecuzione");
			@SuppressWarnings("resource")
			ServerSocket s=new ServerSocket(port);
			
			while(true){
				Socket socket=s.accept();
				
				DataInputStream in=new DataInputStream(socket.getInputStream());
				
				this.notifyReading(in.readInt());
				socket.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void notifyReading(int temp){
		System.out.println("Ho ricevuto una nuova Lettura "+temp);
	}


}
