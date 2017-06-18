package server;

import java.util.concurrent.Semaphore;

public class Compute {
	
	Semaphore availableRequest;
	
	public Compute(int maxReq){
		availableRequest=new Semaphore(maxReq);
	}
	
	public int getResult(String op,int op1, int op2){
		int x=-1;
		try{
			availableRequest.acquire();
			Thread.sleep((int)(Math.random()*6000));
			if(op.equals("SUM")){
				System.out.println(Thread.currentThread().getName()+"Effettuo somma su "+op1 +" e "+op2);
				x=op1+op2;
			}else if(op.equals("MUL")){
				System.out.println(Thread.currentThread().getName()+"Effettuo moltiplicazione su "+op1+ " e "+op2);
				x=op1*op2;
			}
			availableRequest.release();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return x;
	}

}
