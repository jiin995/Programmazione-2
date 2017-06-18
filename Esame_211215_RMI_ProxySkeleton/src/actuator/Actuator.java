package actuator;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IActuator;
import interfaces.IDispatcher;

public class Actuator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Registry registry=LocateRegistry.getRegistry();
		
			IDispatcher dispatcher=(IDispatcher)registry.lookup("Dispatcher");
			
			IActuator actuator=new ActuatorImpl(Integer.valueOf(args[0]));
			ActuatorSkeletonD actuatorSkeleton=new ActuatorSkeletonD(Integer.valueOf(args[0]), actuator);
			
			dispatcher.sottoscrivi(InetAddress.getLocalHost().getHostAddress(), Integer.valueOf(args[0]).intValue());
			
			actuatorSkeleton.runSKeleton();
			
			
		}catch(RemoteException e){
			e.printStackTrace();
		}catch(NotBoundException e){
			e.printStackTrace();
		}catch(UnknownHostException e){
			e.printStackTrace();
		}
		
		
	}

}
