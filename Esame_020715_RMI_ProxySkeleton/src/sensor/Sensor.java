package sensor;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import controller.IController;

public class Sensor  {

	public static void main(String args[]){
		try{
			Registry registry=LocateRegistry.getRegistry();
			
			IController controller=(IController)registry.lookup("Controller");
			controller.subscribe(Integer.valueOf(args[0]),Integer.valueOf( args[1]));
			
			System.out.println("Sottoscrizione effettuata porta"+args[1]+" presso il sensore "+args[0]);
		}catch(RemoteException e){
			e.printStackTrace();
		}catch(NotBoundException e){
			e.printStackTrace();
		}
		
		SensorSkeleton skeleton=new SensorSkeleton(Integer.valueOf(args[1]));
		skeleton.runSkeleton();
	}
}
