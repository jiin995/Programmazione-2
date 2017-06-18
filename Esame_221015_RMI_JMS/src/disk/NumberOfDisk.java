package disk;

public class NumberOfDisk {
	
	private static int nDisk;
	private  static NumberOfDisk istance;
	
	protected NumberOfDisk(){
		nDisk=0;
	}

	public static NumberOfDisk getIstance(){
		if(istance==null)
			istance=new NumberOfDisk();
		return istance;
	}

	public int getnDisk() {
		return nDisk;
	}
	
	public int newDisk(){
		 return nDisk;
	}
	
	
}
