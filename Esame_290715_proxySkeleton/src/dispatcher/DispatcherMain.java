package dispatcher;

public class DispatcherMain {

	public static void main(String[] args) {

		Dispatcher dispatcher=new Dispatcher();
		DispatcherSkeletonD skeleton=new DispatcherSkeletonD(dispatcher, 23232);
		skeleton.runSkeleton();
		
	}

}
