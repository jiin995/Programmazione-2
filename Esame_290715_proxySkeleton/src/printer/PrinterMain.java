package printer;

public class PrinterMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrinterSkeleton skeleton=new PrinterSkeleton(Integer.valueOf(args[0]));
		skeleton.runSkeleton();
	}

}
