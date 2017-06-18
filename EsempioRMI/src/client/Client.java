package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import counter.*;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			Registry registry=LocateRegistry.getRegistry();
		
			ICounter c=(ICounter)registry.lookup("counter");
			
			c.set();
			System.out.println("Settato "+c.get());
			c.set();
			System.out.println("Settato "+c.get());
			Thread.sleep(((int)(Math.random()*10000))+1000);
			c.set();
			System.out.println("Settato "+c.get());

		}catch(RemoteException e){
			e.printStackTrace();
		}catch(NotBoundException e){
			e.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
