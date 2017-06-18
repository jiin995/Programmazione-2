package buffer;

public class CodaCircolare {

	private int space;
	private int elem;
	
	private int head,tail;
	private int buffer[];
	
	public CodaCircolare(int s){
		space=s;
		elem=0;
		head=0;
		tail=0;
		buffer=new int[space];
	}
	
	public int getValue() throws Exception{
		if(!this.isEmpty()){
			int l= buffer[(tail++)%space];
			elem--;
			return l;
		}
		else
			throw new Exception("Vuota");
	}

	public void addValue(int in)throws Exception{
		if(!this.isFull()){
			buffer[(head++)%space]=in;
			elem++;
		}
		else
			throw new Exception("Piena");
	}
	
	public boolean isFull(){
		if(elem==space)
			return true;
		return false;
	}
	
	public boolean isEmpty(){
		if(elem==0)
			return true;
		return false;
	}
	
	public int getSize(){
		return space;
	}
}
