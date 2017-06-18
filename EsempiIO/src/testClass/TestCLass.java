package testClass;

import java.io.Serializable;

public class TestCLass implements Serializable{
	
	private String ss;
	
	public TestCLass(String s){
		ss=new String(s);
	}
	
	public String getSS(){
		return new String(ss);
	}

}
