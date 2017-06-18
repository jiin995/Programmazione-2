package codaCircolare;

public class CodaCircolare {
	
	private int size;
	int elem;
	int head,tail;
	private int buffer[];
	
	public CodaCircolare(int s){
		size=s;
		elem=0;
		head=0;
		tail=0;
		buffer=new int[size];
	}
	
	public Boolean isFull(){
		if(elem==size)
			return true;
		else
			return false;
	}

	public Boolean isEmpty(){
		if(elem==0)
			return true;
		else
			return false;
	}
	
	public synchronized int getElem(){
		if(!this.isEmpty()){
			elem--;
			return buffer[(tail++)%size];
		}
		else
			return -1;	
	}
	
	public synchronized void addElem(int el){
		if(!this.isFull()){
			elem++;
			buffer[(head++)%size]=el;
		}
	}
}
