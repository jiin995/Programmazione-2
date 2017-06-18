package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import whiteboard.IWhiteBoard;

public class Client {

	public static void main(String[] args) {
		Registry remote;
		try {
			remote = LocateRegistry.getRegistry(2000);
			IWhiteBoard lavagnaremote=(IWhiteBoard) remote.lookup("lavagna");
			lavagnaremote.addForme(new Square());
			lavagnaremote.addForme(new Triangle());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
