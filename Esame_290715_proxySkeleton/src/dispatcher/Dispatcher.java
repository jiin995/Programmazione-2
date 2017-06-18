package dispatcher;

import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Dispatcher implements IDispatcher {
	
	private Vector<PrinterProxy> printer;
	//private PrinterProxy printer[];
	private int currentPrinter;
	private int numberOfPrinter;
	private Semaphore maxReq;
	
	public Dispatcher(){
		maxReq=new Semaphore(0);
		currentPrinter=0;
		numberOfPrinter=0;
		printer=new Vector<PrinterProxy>();
		//printer =new PrinterProxy[10];
	}
	
	public boolean addPrinter(String add,int p){
		printer.add(new PrinterProxy(add, p));
		numberOfPrinter++;
		maxReq.release();
		System.out.println("Aggiunta nuova stampante con address : "+add+" porta :"+p+"\n Numero di stampanti registrate "+numberOfPrinter+"\n permessi avviabili "
				+ maxReq.availablePermits());
		return true;
	}
	
	public boolean printRequest(String docName){
		
		boolean x=false;
		
		try{
			maxReq.acquire();
			int d=(currentPrinter++)%numberOfPrinter;

			System.out.println("[Dispatcher ] Servira' la richiesta la stampante numero "+d+"\n Registrata con address : "+
					/*printer[d].getAddress()+" porta : "+printer[d].getPort());*/
						printer.get(d).getAddress()+" porta : "+printer.get(d).getPort());
			//x=printer.get(d).print(docName);
			x=printer.get(d).print(docName);
			maxReq.release();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		return x;
	}

}
