package computeData;

import java.io.Serializable;


@SuppressWarnings("serial")
public class ComputeData implements Serializable{
	
	private int operando1;
	private int operando2;
	private String operazione;
	
	public ComputeData(int op1,int op2,int op) throws Exception{
		operando1=op1;
		operando2=op2;	
		if(op==0){
			operazione=new String("SUM");
		}else if(op==1){
			operazione=new String("MUL");
		}else throw new Exception("operazione non valida");
	}

	public int getOperando1() {
		return operando1;
	}

	public void setOperando1(int operando1) {
		this.operando1 = operando1;
	}

	public int getOperando2() {
		return operando2;
	}

	public void setOperando2(int operando2) {
		this.operando2 = operando2;
	}

	public String getOperazione() {
		return operazione;
	}

	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}
	
	

}
