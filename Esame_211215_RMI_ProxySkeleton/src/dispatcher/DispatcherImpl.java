package dispatcher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.IActuator;
import interfaces.IDispatcher;

@SuppressWarnings( "serial" )
public class DispatcherImpl extends UnicastRemoteObject implements IDispatcher {
	
	private String actuatorAddress[];
	private int actuatorPort[];
	private int numberOfMaxActuator;
	private int currentActuatorSubscribed;
	private static int currentActuator;
	private IActuator actuator[];
	
	public DispatcherImpl(int numberOfAct) throws RemoteException{
		numberOfMaxActuator=numberOfAct;
		currentActuatorSubscribed=0;
		currentActuator=0;
		actuatorAddress=new String [numberOfMaxActuator];
		actuatorPort=new int[numberOfMaxActuator];
		actuator=new IActuator[numberOfMaxActuator];
		
		
	}
	
	public void sottoscrivi(String address, int port) throws RemoteException{
		actuatorAddress[currentActuatorSubscribed]=new String(address);
		actuatorPort[currentActuatorSubscribed]=port;
		actuator[currentActuatorSubscribed]=new ActuatorProxy(address,port);
		
		System.out.println("[Dispatcher] :"+(currentActuatorSubscribed++)+" nuova sottoscrizione di un actuator \n	Indirizzo "+
							address+"	Port "+port);
	}

	public boolean sendCmd(String command) throws RemoteException{
		System.out.println("[Dispatcher] : servira' la richiesta l'attuatore con \n	Indirizzo"+
				actuatorAddress[(currentActuator)%currentActuatorSubscribed]+"	Port"+actuatorPort[(currentActuator)%currentActuatorSubscribed]);
		return actuator[(currentActuator++)%currentActuatorSubscribed].executeCmd(command);
	}


}
