package agenzia;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.*;
import java.rmi.registry.*;
import broker.IBroker;

public class Agenzia {
	
	
	public static void main(String args[]){
		
		try{
			Registry registry=LocateRegistry.getRegistry();
			IBroker broker=(IBroker)registry.lookup("Broker");
			
			broker.sottoscrivi(InetAddress.getLocalHost().getHostAddress(),Integer.valueOf(args[0]));
			
			IAgenzia agenzia=new AgenziaImpl();
			AgenziaSkeletonD skeleton=new AgenziaSkeletonD(agenzia, Integer.valueOf(args[0]));
			skeleton.runSkeleton();
			
		}catch(NotBoundException n){
			n.printStackTrace();
		}catch(RemoteException e){
			e.printStackTrace();
		}catch(UnknownHostException e){
			e.printStackTrace();
		}
		
	}

}
