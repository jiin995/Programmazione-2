package Client;

import Dispatcher.IDispatcher;

public class Client {

	public static void main(String[] args){
		
		IDispatcher dispatcher=new DispatcherProxy(args[0],Integer.valueOf(args[1]));
		
		int x=(int) (Math.random()*4);
		
		System.out.println("[Client] --> Invio il comando ~> "+ x);
		
		dispatcher.sendCmd(x);
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		x=dispatcher.getCmd();
		System.out.println("[Client] --> Ricevo il comando  ~> "+ x);
		
	}
	
}
