package dispatcher;

public interface IDispatcher {
	
	public boolean addPrinter(String add,int p);
	
	public boolean printRequest(String docName);

}
