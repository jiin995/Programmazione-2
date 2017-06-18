package Server;

public class Server {

	public static void main(String[] args){
		
		DispatcherImpl dispatcher=new DispatcherImpl();
		//DispatcherEr skeleton = new DispatcherEr(Integer.valueOf(args[0]));
		DispatcherSkeletonD skeleton=new DispatcherSkeletonD(dispatcher, Integer.valueOf(args[0]));
		skeleton.runSkeleton();
		
	}
	
}
