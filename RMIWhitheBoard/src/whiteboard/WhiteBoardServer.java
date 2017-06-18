package whiteboard;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class WhiteBoardServer {

	public static void main(String[] args) {
		try {
			Registry remote=LocateRegistry.getRegistry(2000);
			IWhiteBoard lavagna=new WhiteBoardImpl();
			IWhiteBoard lavagnaremote=(IWhiteBoard) UnicastRemoteObject.exportObject(lavagna,0);
			remote.rebind("lavagna", lavagnaremote);
			System.out.println(lavagnaremote);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
