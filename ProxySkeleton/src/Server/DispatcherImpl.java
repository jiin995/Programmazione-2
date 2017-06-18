package Server;

import Dispatcher.IDispatcher;

public class DispatcherImpl implements IDispatcher {
	
	private int command;
	
	public void sendCmd(int cmd){
		System.out.println("    +[DispImp] sendCmd"+cmd);
		command=cmd;
	}
	
	public int getCmd(){
		System.out.println("    +[DispImp] getCmd"+command);
		return command;
	}
	
	

}
