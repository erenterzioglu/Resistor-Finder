package resistor_finder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class resistor_finder extends JFrame  {
	static Integer r,g,b;
	int toleranceindex;
	static boolean fromtext;

	
	static JPanel firstlinecolour ,secondlinecolour,thirdlinecolour,fourthlinecolour,/*fifthlinecolour,*/tolerancecolour;
	JLabel lblNewLabel;
	static JComboBox <String> ohmwrite;
	private static JComboBox <String> firstline;
	static JComboBox <String> secondline;
	static JComboBox <String> thirdline;
	static JComboBox <String> fourthline;
	//static JComboBox <String> fifthline;
	static JComboBox <String> tolerance;
	
	ActionListener actionListener;
	ItemListener itemListener,toleranceListener;
	
		
	 Image resistor;
	 char tol=177;
	 char ohm=0x2126;
	 
	 String[] multiply= {Character.toString(ohm),"k"+Character.toString(ohm),"m"+Character.toString(ohm)};
	 String[] colours= {"Siyah","Kahverengi","Kırmızı","Turuncu","Sarı","Yeşil","Mavi","Mor","Gri","Beyaz"	};
	 String[] tolerance_val= {Character.toString(tol)+"5", Character.toString(tol)+"10",Character.toString(tol)+"20",};
	 String[] tolerancecolours= {"Altın","Gümüş","Yok"};
	// String[] colours_eng= {"BLACK","BROWN","RED","ORANGE","YELLOW","GREEN","BLUE","PURPLE","GREY","WHITE","GOLD","SİLVER","NULL"};

	 static int i=0;

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					resistor_finder frame = new resistor_finder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public resistor_finder() {
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		

		resistor= new ImageIcon("images/resistor1.png").getImage();
		//resistor= new ImageIcon("images/res-removebg-preview.png").getImage();
		
		firstlinecolour = new JPanel();
		firstlinecolour.setForeground(new Color(0, 0, 0));
		firstlinecolour.setBackground(new Color(0, 0, 0));
		firstlinecolour.setBounds(139, 69, 10, 146);
		contentPane.add(firstlinecolour);
		
		secondlinecolour = new JPanel();
		secondlinecolour.setBackground(new Color(0, 0, 0));
		secondlinecolour.setBounds(201, 95, 10, 98);
		contentPane.add(secondlinecolour);
		
		thirdlinecolour = new JPanel();
		thirdlinecolour.setBackground(new Color(0, 0, 0));
		thirdlinecolour.setBounds(257, 95, 10, 98);
		contentPane.add(thirdlinecolour);
		
		fourthlinecolour = new JPanel();
		fourthlinecolour.setBackground(new Color(239,228,176));
		fourthlinecolour.setBounds(306, 95, 10, 98);
		contentPane.add(fourthlinecolour);
		
		tolerancecolour = new JPanel();
		tolerancecolour.setForeground(Color.BLACK);
		tolerancecolour.setBackground(new Color(212,175,55));
		tolerancecolour.setBounds(424, 73, 10, 146);
		contentPane.add(tolerancecolour);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(resistor));
	

		lblNewLabel.setBounds(43, 54, 476, 169);
		contentPane.add(lblNewLabel);
		
		
		ohmwrite = new JComboBox(multiply);// 
		ohmwrite.setBounds(384, 234, 60, 32);
		contentPane.add(ohmwrite);
		
		firstline=new JComboBox(colours);
		firstline.setBounds(30, 333, 99, 39);
		contentPane.add(firstline);
		
		secondline = new JComboBox(colours);
		secondline.setBounds(153, 333, 99, 39);
		contentPane.add(secondline);
		
		thirdline = new JComboBox(colours);
		thirdline.setBounds(293, 333, 100, 39);
		contentPane.add(thirdline);
		
		fourthline = new JComboBox(tolerancecolours);
		fourthline.setBounds(432, 333, 100, 39);
		contentPane.add(fourthline);
		
		
		
		tolerance = new JComboBox(this.tolerance_val);
		tolerance.setBounds(459, 234, 60, 32);
		contentPane.add(tolerance);
		
		textField = new JTextField();
		
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBounds(167, 234, 176, 32);
		contentPane.add(textField);
		textField.setColumns(10);
				
		
		itemListener= new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource() instanceof JComboBox) {
					setcolour(e);
				}
			}
		};
		
		
		toleranceListener=new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent event) {
				
				if(event.getSource() instanceof JComboBox) {
					if(tolerance.getSelectedIndex()!=fourthline.getSelectedIndex())
						setToleranceColour(event);
				}

				
			}
		};
	
		
		
		firstline.addItemListener(itemListener);
		secondline.addItemListener(itemListener);
		thirdline.addItemListener(itemListener);

		fourthline.addItemListener(toleranceListener);
		tolerance.addItemListener(toleranceListener);
		
		
		
		
		textField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calculatefromwrite();
				
			}
		});
		
		/*textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				calculatefromwrite();

			}
		});*/
		
		ohmwrite.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
					//
					//
					//
				//thirdline.setSelectedIndex(ohmwrite.getSelectedIndex()*3);
				if(textField.getText()!=null)
					calculatefromwrite();
			}
		});
		
		
	}

void setcolour(ItemEvent e) {
	indexToColour(((JComboBox<String>) e.getSource()).getSelectedIndex());
	
	if(e.getSource()==firstline) {
		firstlinecolour.setBackground(new Color(r, g, b));
	}
	
	else if(e.getSource()==secondline) {
		secondlinecolour.setBackground(new Color(r, g, b));
	}
	else if(e.getSource()==thirdline) {
		thirdlinecolour.setBackground(new Color(r, g, b));
	}
	
	if(!fromtext)	
		calculateohm();
	
}

 void indexToColour(int index) {
	
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
 
 void setToleranceColour(ItemEvent e) {
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
 
 void calculateohm() {
	 double value= ((firstline.getSelectedIndex())*10)+secondline.getSelectedIndex();
	 double multiplyer= (int)Math.pow(10, thirdline.getSelectedIndex());
	 Double	valuetowrite;
	 
	 valuetowrite= (value*multiplyer);

	 
	 if(valuetowrite>=	Math.pow(10, 6)) {
		 ohmwrite.setSelectedIndex(2);
		 valuetowrite/=Math.pow(10, 6);
	 }
	 
	 else if(valuetowrite<Math.pow(10, 3)) {
		 ohmwrite.setSelectedIndex(0);
		 
	 }
	 else {
		 valuetowrite/= 1000;
		 ohmwrite.setSelectedIndex(1);}

	 
	 
	 
	 String  temp= valuetowrite.toString();

	 if(valuetowrite.intValue()==valuetowrite)
		 temp=String.valueOf(valuetowrite.intValue());
	
	 textField.setText(temp);
	 temp=null;
	 
	 
 }
 
 void calculatefromwrite() {
	// if(!fromtext) {
	 fromtext=true;
	 Double value;
	 int index=0;
		value=Double.parseDouble(textField.getText())*Math.pow(10, ohmwrite.getSelectedIndex()*2);
	 index=ohmwrite.getSelectedIndex();
	
	while(value>=100) { 
		index++;
		value/=10;
	}
	
	Integer temp= value.intValue()/10;
	value=value%10;
	
	thirdline.setSelectedIndex(index);
	firstline.setSelectedIndex(temp.intValue());//
	secondline.setSelectedIndex(value.intValue());//
	
	fromtext=false;
	
 }


}
