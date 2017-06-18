package Server;


public class DispatcherServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DispatcherImpl dispatcher=new DispatcherImpl();
		
		DispatcherSkeletonThread skel=new DispatcherSkeletonThread(dispatcher);
		
		skel.runSkelenton();
	}

}