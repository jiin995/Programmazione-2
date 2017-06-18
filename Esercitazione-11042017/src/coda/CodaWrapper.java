package coda;

/*
 * nella versione base del Wrapper non faccio altro che convertire chiamate sul Wrapper in chiamate sull'oggetto
 */

public abstract class CodaWrapper implements Coda {
	
	protected Coda coda;
	
	public CodaWrapper(Coda c){
		coda=c;
	}


	
/*
	 public void inserisci(int i){
		coda.inserisci(i);
	}
	
	public int preleva(){
		return coda.preleva();
	}
*/
	
	public boolean empty(){
		return coda.empty();
	}
	
	public boolean full(){
		return coda.full();
	}
	
	public int getSize(){
		return coda.getSize();
	}
	
	
}
