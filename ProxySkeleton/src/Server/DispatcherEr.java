package Server;

public class DispatcherEr extends DispatcherSkeletonE {
	
	private int command;
	
	public DispatcherEr(int p){
		super(p);
	}
	
	public int  getCmd(){
		return command;
	}

	public void sendCmd(int cmd){
		command=cmd;
	}
} 