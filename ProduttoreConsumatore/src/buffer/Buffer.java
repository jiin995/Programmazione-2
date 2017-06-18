package buffer;

public class Buffer {

	private boolean contentDisp;
	/*
	 * false --> contenuto non disponibile non posso prelevare
	 * true ---> contnuto disponibile non posso produrre
	 * 
	 */
	
	private long content;
	
	public Buffer (){
		content=0;
		contentDisp=false;
		
	}
	
	public synchronized long consuma(){
		
		System.out.println("["+Thread.currentThread().getName()+"]--> invocazione del metodo Consuma");
		
		while(contentDisp==false){
				System.out.println("["+Thread.currentThread().getName()+"]--> contenuto non disponibile");
				
				try{
					wait();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
		}
		
		contentDisp=false;
		long x=content;
			notify();
		System.out.println("["+Thread.currentThread().getName()+"]--> consumato :"+x);

		return x;	
	}
	
	
	public synchronized void produci (){
		
		System.out.println("["+Thread.currentThread().getName()+"]--> invocazione del metodo Produci");

		while(contentDisp==true){
			System.out.println("["+Thread.currentThread().getName()+"]--> spazio non disponibile");
			
			try{
				wait();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		content=System.currentTimeMillis();
		contentDisp=true;
		notify();
		System.out.println("["+Thread.currentThread().getName()+"]--> prodotto :"+content);

		
	}
	
	
}
