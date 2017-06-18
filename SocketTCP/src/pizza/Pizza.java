package pizza;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Pizza implements Serializable {
	
	private String tipo;
	private double costo;
	
	public Pizza(String s, double c){
		tipo=new String(s);
		costo=c;
	}
	
	public void getPizza(){
		System.out.println("La pizza scelta è '"+tipo+"' e costa '"+costo+"'");
	}


}
