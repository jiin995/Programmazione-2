package gestore;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.locks.*;

@SuppressWarnings("serial")
public class GestoreImpl extends UnicastRemoteObject implements IGestore {
	
	private Vector <DepositoProxy> deposito;
	private Lock mutex;
	private int currentDeposito=0;
	
	public GestoreImpl() throws RemoteException {
		deposito=new Vector<DepositoProxy>();
		mutex=new ReentrantLock();
	}
	
	public boolean richiestaDeposito(int id_articolo) throws RemoteException{
		int x;
		boolean result=false;
		
		mutex.lock();
			x=currentDeposito;
			currentDeposito=(currentDeposito+1)%deposito.size();
		mutex.unlock();
		
		System.out.println("x"+x+" deposito"+deposito.size()+ " current "+currentDeposito);
		
		result=deposito.get(x).deposita(id_articolo);
		
		System.out.println(" Richiesta ricevuta e inoltrata al deposito "+x+"\n"+result);
		
		return result;
	}
	public boolean sottoscrivi(String add,int p) throws RemoteException{
		
		mutex.lock();
			deposito.addElement(new DepositoProxy(add, p));
			System.out.println("[Gestore] sottoscritto nuovo Deposito "+add+":"+p+" \nTotale sottoscritti "+deposito.size());
		mutex.unlock();
			
		return true;
	}

}
