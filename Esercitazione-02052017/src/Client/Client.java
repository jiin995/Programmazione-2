package Client;

import Dispatcher.*;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IDispatcher dispatcher=new DispatcherProxy();
		
		dispatcher.sendCmd(2);    //Non possiamo affermare nulla se sono invocazioni remote o locali
	try{
		Thread.sleep(1000);
			
	}catch(InterruptedException e){
		e.printStackTrace();
	}
		//System.out.println("ciao");
		System.out.println(dispatcher.getCmd());
		
		dispatcher.close();
	}

}
