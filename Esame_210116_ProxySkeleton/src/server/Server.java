package server;

import dispatcher.DispatcherImpl;
import dispatcher.IDispatcher;

public class Server {

	public static void main(String[] args) {
		IDispatcher dispatcher=new DispatcherImpl(5);
		DispatcherSkeletonD skelton=new DispatcherSkeletonD(dispatcher, 23233);
		skelton.runSkeleton();
		
	}

}
