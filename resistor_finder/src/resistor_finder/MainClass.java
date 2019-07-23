package resistor_finder;

public class MainClass {
	
	static Fivebandresistor fiveband;
	static SMDresistor smd;
	static Resistorfinder resistor;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		resistor= new Resistorfinder();
		resistor.setVisible(true);

	}
	
	
	static void selectFrameOnFiveband(Fivebandresistor obj, int index) {
		if(index!=1) {
		obj.resistortype.setSelectedIndex(1);
		obj.dispose();
		openFrame(index);}

	}
	
	static void selectFrameOnSmd(SMDresistor obj, int index) {
		if(index!=2) {
		obj.resistortype.setSelectedIndex(2);
		obj.dispose();
		
		openFrame(index);}
		
	}
	static void selectFrameOnFourband(Resistorfinder obj, int index) {
		if(index!=0) {
		obj.resistortype.setSelectedIndex(0);
		obj.dispose();
		openFrame(index);}
	}
	
	static void openFrame(int index) {
		if(index==0)
			resistor.show();
		else if(index==1) {
			if(fiveband==null) 
				fiveband=new Fivebandresistor();
			fiveband.show();}
			
			else {
				if(smd==null) 
					smd=new SMDresistor();
					smd.show();}
				
	}
	

}
