package Server;

import Dispatcher.*;

public class DispatcherImpl implements IDispatcher {
	
	int command;
	
	public void close(){};

	
	public void sendCmd(int cmd){
		
		command=cmd;
	}
	
	public int getCmd(){
		
		return command;
	}


}
