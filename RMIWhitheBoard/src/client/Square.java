package client;

import whiteboard.*;

public class Square implements IShape {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2754831803557808547L;

	public boolean Draw(){
		System.out.println ("+--+");
		System.out.println ("|  |");
		System.out.println ("+--+");
		return true;
	}
	
}
