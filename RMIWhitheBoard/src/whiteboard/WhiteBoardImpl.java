package whiteboard;

import java.rmi.RemoteException;
import java.util.Vector;

import observer.IObserver;

public class WhiteBoardImpl implements IWhiteBoard {
	private Vector<IObserver> osservatori;
	private Vector<IShape> forme;
	@Override
	public boolean Subscribe(IObserver i) throws RemoteException {
		osservatori.addElement(i);
		return true;
	}

	public Vector<IShape> getForme() throws RemoteException{
		return forme;
	}

	public void addForme(IShape i) throws RemoteException {
		System.out.println("Lavagna Luminosa::Stampo!");
		forme.addElement(i);
		for(int j=0;j<osservatori.size();j++)
				osservatori.get(j).CallBack();
	}

	public WhiteBoardImpl() {
		super();
		this.osservatori = new Vector<IObserver>();
		this.forme = new Vector<IShape>();
	}

}
