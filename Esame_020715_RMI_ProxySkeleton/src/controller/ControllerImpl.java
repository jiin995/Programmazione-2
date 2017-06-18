package controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("serial")

public class ControllerImpl extends UnicastRemoteObject implements IController {

	private Vector<SensorProxy> sensors;
	private Lock lock[];
	
	public ControllerImpl() throws RemoteException{
		sensors=new Vector<SensorProxy>();
		lock=new ReentrantLock[5];
		for(int i=0;i<5;i++)
			lock[i]=new ReentrantLock();
	}
	
	public boolean sendReading(int sensorID,int temp) throws RemoteException{
		lock[sensorID-1].lock();

		try{
			Thread.sleep((int)(1000+Math.random()*5000));
			System.out.println("[Controller] nuovo stato per il sensore "+sensorID);
			for(int i=0;i<sensors.size();i++){
				if(sensors.get(i).getSensorId()==sensorID){
					sensors.get(i).notifyReading(temp);
					System.out.println("[Controller] Notifico "+sensors.get(i).getPort());
					
				}
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			lock[sensorID-1].unlock();
		}
		return true;
	}
	
	public boolean subscribe(int sensorID,int p) throws RemoteException{
		sensors.add(new SensorProxy(sensorID, p));
		System.out.println("Sottoscritto -->SensorID : "+sensorID+" porta : "+p+"\nNumero Sottoscritti "+sensors.size());
		return true;
	}

}
