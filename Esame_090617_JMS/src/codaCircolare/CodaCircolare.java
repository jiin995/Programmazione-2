package codaCircolare;

public class CodaCircolare {
	
	private int articolo[];
	private int tail;
	private int head;
	private int elem;
	private int dim;
	
	public CodaCircolare(int dimensione){
		dim=dimensione;
		articolo=new int[dim];
		tail=0;
		head=0;
		elem=0;
	}

	public synchronized int getElem(){
		int x=-1;
		if(!this.isEmpty()){
			elem--;
			x=articolo[(tail++)%dim];
		}
		return x;
	}
	
	public synchronized void addElem(int e){
		if(!this.isFull()){
			elem++;
			articolo[(head++)%dim]=e;
		}
	}
	
	public synchronized boolean isEmpty(){
		if(elem==0)
			return true;
		return false;
	}
	
	public synchronized boolean isFull(){
		if(elem==dim)
			return true;
		return false;
	}
}
