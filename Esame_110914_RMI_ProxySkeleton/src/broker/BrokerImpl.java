package broker;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.concurrent.locks.*;

@SuppressWarnings("serial")

public class BrokerImpl extends UnicastRemoteObject implements IBroker  {
	
	private Vector<AgenziaProxy> agenzie;
	private int currentAgent=0;
	private Lock mutex;
	
	public BrokerImpl() throws RemoteException{
		agenzie=new Vector<AgenziaProxy>();
		mutex=new ReentrantLock();
	}
	
	public boolean sottoscrivi(String address,int port) throws RemoteException{
		
		mutex.lock();
			System.out.println("[Broker] Richiesta di sottoscrizione da "+address+":"+port);
			agenzie.add(new AgenziaProxy(address, port));
		mutex.unlock();
			
		return true;
		
	}
	public boolean sottoponi(int tipoOperazione,int quantita) throws RemoteException{
		
		int x;
		mutex.lock();
			x=currentAgent;
			currentAgent=(currentAgent+1)%agenzie.size();
		mutex.unlock();
		
		if(tipoOperazione==IBroker.ACQUISTA){
			
			System.out.println("[Broker] L'agenzia "+x+" eseguira' la richiesta di acquisto");
			agenzie.get(x).acquista(quantita);
			
		}else if(tipoOperazione==IBroker.VENDI){
			
			System.out.println("[Broker] L'agenzia "+x+" eseguira' la richiesta di vendita");
			agenzie.get(x).vendi(quantita);
			
		}else
			return false;
				
		return true;
	}


}
