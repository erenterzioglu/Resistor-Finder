package resistor_finder;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculations {
	static Integer r,g,b;
	static boolean fromtext;
	static boolean smdfromtext;
	static Integer toleranceindex;
	

static void setcolour(ItemEvent e,ArrayList<JComboBox<String>>lines,ArrayList<JPanel>linecolours,JTextField textField) {
	
	indexToColour(((JComboBox<String>) e.getSource()).getSelectedIndex());
	linecolours.get(lines.indexOf(e.getSource())).setBackground(new Color(r,g,b));
	
	
	if(!fromtext)	
		calculateohm(lines,textField);
}


static void indexToColour(int index) {
	
	if(index==0) 		{r=0; 	g=0;	b=0;		}
	else if(index==1) 	{r=100; g=51;	b=0; 		}
	else if(index==2) 	{r=255; g=0;	b=0;		}
	else if(index==3)	{r=255; g=165;	b=0;		}
	else if(index==4) 	{r=255; g=255;	b=0;		}
	else if(index==5) 	{r=0; 	g=255;	b=0;		}
	else if(index==6)	{r=0; 	g=0;	b=255;		}
	else if(index==7) 	{r=191; g=0;	b=255;		}
	else if(index==8) 	{r=127; g=127;	b=127;		}
	else if(index==9) 	{r=255; g=255;	b=255;		}
	

	//"BLACK","BROWN","RED","ORANGE","YELLOW","GREEN","BLUE","PURPLE","GREY","WHITE",
}
 
 
 
static void calculateohm(ArrayList<JComboBox<String>>lines,JTextField textField) {
	 
	 double value= ((lines.get(0).getSelectedIndex())*10)+lines.get(1).getSelectedIndex();
	 double multiplyer= (int)Math.pow(10, lines.get(lines.size()-2).getSelectedIndex());
	 Double	valuetowrite;
	 
	 if(lines.size()>=5)
		 value=value*10+lines.get(lines.size()-3).getSelectedIndex();
	 
	 valuetowrite=ohmWriteValue((value*multiplyer), lines.get(lines.size()-1));
	 
	 String  temp= valuetowrite.toString();

	 if(valuetowrite.intValue()==valuetowrite)
		 temp=String.valueOf(valuetowrite.intValue());
	
	textField.setText(temp);
	temp=null;
	
	 
 }
 
static void calculatefromwrite(ArrayList<JComboBox<String>>lines,JTextField textField) {
	
	 fromtext=true;
	 Double value;
	 int index=0;
	 value=Double.parseDouble(textField.getText())*Math.pow(10, lines.get(lines.size()-1).getSelectedIndex()*2);
	 index=lines.get(lines.size()-1).getSelectedIndex();
	
	if(lines.size()>=5)
		 while(value>=1000) { 
				index++;
				value/=10;
			}
	else {	
	 while(value>=100) { 
		index++;
		value/=10;
	}
	}
	Integer temp; 
	
	lines.get(lines.size()-2).setSelectedIndex(index);
	
	if(lines.size()>=5) {
		temp=value.intValue()/100;
		int temp2= value.intValue()%10;
		value=((value%100)-(value.intValue()%10))/10;
		lines.get(2).setSelectedIndex(temp2);
	}
	else{
		temp=value.intValue()/10;
		value=value%10;
	}
	lines.get(0).setSelectedIndex(temp.intValue());//
	lines.get(1).setSelectedIndex(value.intValue());//

	fromtext=false;
	
 }



static void setToleranceColour(ItemEvent e,JComboBox<String>fourthline,JComboBox<String>tolerance,JPanel tolerancecolour) {
	
	 
	  toleranceindex= ((JComboBox<String>) e.getSource()).getSelectedIndex();
	 
		if(toleranceindex==0) 		{r=215; g=175;	b=55;		}
		else if(toleranceindex==1) 	{r=192; g=192;	b=192; 		}
		else if(toleranceindex==2) 	{r=239; g=228;	b=176;		}
		
		if(e.getSource()==fourthline)
			tolerance.setSelectedIndex(fourthline.getSelectedIndex());
		else
			fourthline.setSelectedIndex(tolerance.getSelectedIndex());
		
		tolerancecolour.setBackground(new Color(r,g,b));
	 
}

static void smdTexttoNumber(SMDresistor resistor,String text) {
	String  temp=null;
	
	
	if((text.indexOf("R")!=-1)||(text.indexOf("r")!=-1)	) {
		
		if(text.contains("R")) text.replace('R', '.');// çalışmıyor
		else text.replace('r', '.');
		
		
	}
	else {
		Double value= Double.parseDouble(text);
		Double multiplyer= Math.pow(10, (value%10));
		value= (value/10)*multiplyer.intValue();
		
		value=ohmWriteValue(value, resistor.ohmwrite);
		
		  temp= value.toString();

		 if(value.intValue()==value)
			 temp=String.valueOf(value.intValue());
		
		//resistor.numbers.setText(temp);
		resistor.textonsmdpicture.setText(text);
		//temp=null;
		}
	
	resistor.numbers.setText(temp);
	temp=null;
	
	
	
}

static double ohmWriteValue(Double valuetowrite,JComboBox<String> ohmwrite) {
	if(valuetowrite>=	Math.pow(10, 6)) {
		 ohmwrite.setSelectedIndex(2);
		 valuetowrite/=Math.pow(10, 6);
		
	 }
	 
	 else if(valuetowrite<Math.pow(10, 3)) {
		 ohmwrite.setSelectedIndex(0);
		 
	 }
	 else {
		 valuetowrite/= 1000;
		 ohmwrite.setSelectedIndex(1);
		 
		 }
	
	 return valuetowrite;
}

static void valueToSmd(SMDresistor smd) {
	Double value=Double.parseDouble(smd.numbers.getText())*Math.pow(10, smd.ohmwrite.getSelectedIndex()*3);
	int index=0;
	smdfromtext=true;
	String temp;
	int num=100;
	
	if((value<100)&&(value.intValue()!=value)) {
		temp=value.toString();
		temp.replace('.', 'R');
	}
	else {
		if(value%10!=0) num=1000;
		
		 while(value>=num) { 
				index++;
				value/=10;
			}
		 temp=value.intValue()+String.valueOf(index);
		 //temp.replaceAll(".","");
	}
		
	smd.textonsmdpicture.setText(temp);
	smd.value_on_smd.setText(temp);
	
	
	smdfromtext=false;
}




}
