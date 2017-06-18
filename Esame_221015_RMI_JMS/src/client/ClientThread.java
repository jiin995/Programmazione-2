package client;

import java.rmi.RemoteException;
import java.util.UUID;

import costumerData.CostumerData;
import frontend.IFrontend;

public class ClientThread extends Thread {
	
	IFrontend frontend;
	
	public ClientThread(IFrontend front,int i){
		super("[Client Thread "+i+"]");
		frontend=front;
		
	}
	
	public void run(){
		
		try{
			
			Thread.sleep(((int)Math.random()*5000)+1000);
			
			CostumerData data=new CostumerData(UUID.randomUUID().toString(), UUID.randomUUID().toString(), (int)(Math.random()*100));
			
			System.out.println("Nome : "+data.getName() +"\nCognome : "+data.getSurname()+"\nAnni : "+data.getAge()+"\nResult : "+frontend.saveData(data));
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(RemoteException e){
			e.printStackTrace();
		}
		
	}

}
