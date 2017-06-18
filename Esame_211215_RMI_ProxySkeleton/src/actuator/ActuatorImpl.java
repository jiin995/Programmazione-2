package actuator;

import java.io.*;
import java.util.concurrent.Semaphore;

import interfaces.IActuator;

public class ActuatorImpl implements IActuator{
	
	private PrintStream bufOut;
	private Semaphore request;

	public ActuatorImpl(int file){
		request=new Semaphore(3);
		try{
			bufOut=new PrintStream(new BufferedOutputStream(new FileOutputStream("./"+file+".txt")));
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public boolean executeCmd(String command){
		try{
			request.acquire();
				
				Thread.sleep(((int)(Math.random()*5000))+1000);
				bufOut.println(command);
				bufOut.flush();
				
			request.release();
			return true;
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return false;
	}
}
