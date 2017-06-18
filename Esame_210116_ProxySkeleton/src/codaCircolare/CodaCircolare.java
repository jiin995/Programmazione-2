package codaCircolare;

public class CodaCircolare {
	
	private int size;
	private int elem;
	private String buffer[];
	private int tail;
	private int head;
	
	public CodaCircolare(int d){
		size=d;
		elem=0;
		tail=0;
		head=0;
		buffer=new String [size];
	}
	
	public boolean isFull(){
		if(elem==size)
			return true;
		return false;
	}
	
	public boolean isEmpty(){
		if(elem==0)
			return true;
		return false;
	}
	
	public synchronized void addElem(String  e){
		if(!this.isFull()){
			buffer[(tail++)%size]=new String(e);
			elem++;
		}
	}
	
	public synchronized String getElem(){
		String x=new String("vuota");
		if(!this.isEmpty()){
			x=new String(buffer[(head++)%size]);
			elem --;
		}
		
		return x;
		
	}
}
