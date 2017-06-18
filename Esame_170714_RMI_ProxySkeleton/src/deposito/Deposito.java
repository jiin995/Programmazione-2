package deposito;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;

import gestore.IGestore;

public class Deposito {
	
	public static void main(String args[]){
		
		try{
			
			Registry registry=LocateRegistry.getRegistry();
			IGestore gestore=(IGestore)registry.lookup("Gestore");
			
			gestore.sottoscrivi(InetAddress.getLocalHost().getHostAddress(), Integer.valueOf(args[0]));
			
			IDeposito deposito=new DepositoImpl(args[0]);
			
			DepositoSkeleton skeleton=new DepositoSkeleton(deposito, Integer.valueOf(args[0]));
			skeleton.runSkeleton();
			
		}catch(RemoteException e){
			e.printStackTrace();
		}catch(NotBoundException e){
			e.printStackTrace();
		}catch(UnknownHostException e){
			e.printStackTrace();
		}
		
	}

}
