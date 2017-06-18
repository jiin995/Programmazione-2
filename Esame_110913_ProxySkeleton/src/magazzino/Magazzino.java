package magazzino;

import java.io.*;
import java.util.concurrent.Semaphore;

import codaCircolare.*;

public class Magazzino implements IMagazzino {
	
	int dimLaptop;
	int dimSmarphone;
	private CodaCircolare laptop;
	private CodaCircolare smartphone;
	private Semaphore spaceAvailableLaptop;
	private Semaphore spaceAvailableSmartphone;
	private Semaphore productAvailableLaptop;
	private Semaphore productAvailableSmartphone;
	private PrintStream outLaptop;
	private PrintStream outSmartphone;
	
	public Magazzino(int dL,int dS){
		dimLaptop=dL;
		dimSmarphone=dS;
		laptop=new CodaCircolare(dimLaptop);
		smartphone=new CodaCircolare(dimSmarphone);
		productAvailableLaptop=new Semaphore(0);
		productAvailableSmartphone=new Semaphore(0);
		spaceAvailableLaptop=new Semaphore(dimLaptop);
		spaceAvailableSmartphone=new Semaphore(dimSmarphone);
		try{
			outLaptop=new PrintStream(new BufferedOutputStream(new FileOutputStream("./laptop.txt")));
			outSmartphone=new PrintStream(new BufferedOutputStream(new FileOutputStream("./smartphone.txt")));
		}catch(FileNotFoundException f){
			f.printStackTrace();
		}
	}
	
	public void deposita(String articolo, int id){
		try{
			if(articolo.equals("laptop")){
				if(!spaceAvailableLaptop.tryAcquire()){
					System.out.println(Thread.currentThread().getName()+" coda laptop piena");
					spaceAvailableLaptop.acquire();
				}
					laptop.addElem(id);
				
				if(productAvailableLaptop.availablePermits()!=dimLaptop)
					productAvailableLaptop.release();
			}else if(articolo.equals("smartphone")){
				if(!spaceAvailableSmartphone.tryAcquire()){
					System.out.println(Thread.currentThread().getName()+" coda smartphone piena");
					spaceAvailableSmartphone.acquire();
				}
					smartphone.addElem(id);
			
				if(productAvailableSmartphone.availablePermits()!=dimSmarphone)
					productAvailableSmartphone.release();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
	
	public int preleva(String articolo){
		int x=-1;
		try{
			if(articolo.equals("laptop")){
				if(!productAvailableLaptop.tryAcquire()){
					System.out.println(Thread.currentThread().getName()+" sospeso non ci sono laptop");
					productAvailableLaptop.acquire();
				}
					x=laptop.getElem();
				spaceAvailableLaptop.release();
			
				outLaptop.println(x);
					outLaptop.flush();
					
			}else if(articolo.equals("smartphone")){
				if(!productAvailableSmartphone.tryAcquire()){
					System.out.println(Thread.currentThread().getName()+" sospeso non ci sono smartphone");
					productAvailableSmartphone.acquire();
				}
					x=smartphone.getElem();
				spaceAvailableSmartphone.release();
			
				outSmartphone.println(x);
					outSmartphone.flush();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return x;
	}

}
