package readingsGenerator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import controller.IController;

public class ReadingsGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try{
			Registry registry=LocateRegistry.getRegistry();
			
			IController controller=(IController)registry.lookup("Controller");
			
			for(int i=0;i<5;i++){
				ReadingThread thread=new ReadingThread(controller,i);
				thread.start();
			}
			
		}catch(RemoteException e){
			e.printStackTrace();
		}catch(NotBoundException e){
			e.printStackTrace();
		}
	}

}
