package server;

import magazzino.Magazzino;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Magazzino m=new Magazzino(5, 5);
		Skeleton dispatcher=new Skeleton(21212, m);
		dispatcher.runSkeleton();

	}

}
