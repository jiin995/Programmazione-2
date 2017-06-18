package client;

import whiteboard.*;

public class Triangle implements IShape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1826772663087127316L;

	public boolean Draw (){
		System.out.println (" /\\ ");
		System.out.println ("/__\\");
		return true;
	}
	
}
