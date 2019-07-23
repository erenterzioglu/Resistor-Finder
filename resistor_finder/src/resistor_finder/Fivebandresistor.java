package resistor_finder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Fivebandresistor extends JFrame {
	
	static Integer r,g,b;
	int toleranceindex;
	static boolean fromtext;
	
	static JPanel firstlinecolour ,secondlinecolour,thirdlinecolour,fourthlinecolour,fifthlinecolour,tolerancecolour;
	JLabel lblNewLabel;
	static JComboBox <String> ohmwrite;
	private static JComboBox <String> firstline;
	static JComboBox <String> secondline;
	static JComboBox <String> thirdline;
	static JComboBox <String> fifthline;
	static JComboBox <String> fourthline;
	static JComboBox <String> tolerance;
	
	static JComboBox resistortype;
	
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
	String[] resistortypes = { "4 şeritli", "5 şeritli", "SMD" };

	 
	 static int i=0;

	ArrayList<JComboBox<String>> lines= new ArrayList<JComboBox<String>>();
	ArrayList<JPanel> linecolours= new ArrayList<JPanel>();

	

	private JPanel contentPane;
	private JTextField textField;


	/**
	 * Create the frame.
	 */
	public Fivebandresistor() {
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		

		resistor= new ImageIcon("images/resistor2.png").getImage();
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
		fourthlinecolour.setBackground(Color.BLACK);
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
		firstline.setBounds(10, 333, 99, 39);
		contentPane.add(firstline);
		
		secondline = new JComboBox(colours);
		secondline.setBounds(119, 333, 99, 39);
		contentPane.add(secondline);
		
		thirdline = new JComboBox(colours);
		thirdline.setBounds(228, 333, 100, 39);
		contentPane.add(thirdline);
		
		fifthline = new JComboBox(tolerancecolours);
		fifthline.setBounds(447, 333, 100, 39);
		contentPane.add(fifthline);
		
		fourthline = new JComboBox(colours);
		fourthline.setBounds(338, 333, 100, 39);
		contentPane.add(fourthline);
		
		
		
		tolerance = new JComboBox(this.tolerance_val);
		tolerance.setBounds(459, 234, 60, 32);
		contentPane.add(tolerance);

		
		textField = new JTextField();
		
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBounds(167, 234, 176, 32);
		contentPane.add(textField);
		textField.setColumns(10);
				
		

		lines.add(firstline);
		lines.add(secondline);
		lines.add(thirdline);
		lines.add(fourthline);
		lines.add(ohmwrite);
		
		linecolours.add(firstlinecolour);
		linecolours.add(secondlinecolour);
		linecolours.add(thirdlinecolour);
		linecolours.add(fourthlinecolour);


		
		resistortype = new JComboBox<String>(resistortypes);
		resistortype.setBounds(20, 11, 99, 32);
		contentPane.add(resistortype);

		resistortype.setSelectedIndex(1);
		Fivebandresistor fiveband=this;
		

		resistortype.addItemListener(new ItemListener() {

		

			@Override
			public void itemStateChanged(ItemEvent e) {
				MainClass.selectFrameOnFiveband(fiveband, resistortype.getSelectedIndex());	
				
			}
		});
			
		
		itemListener= new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource() instanceof JComboBox) {
					Calculations.setcolour(e,lines,linecolours,textField);
					fromtext=false;
				}
			}
		};
		
		
		toleranceListener=new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent event) {
				
				if(event.getSource() instanceof JComboBox) {
					if(tolerance.getSelectedIndex()!=fifthline.getSelectedIndex())
						Calculations.setToleranceColour(event,fifthline,tolerance,tolerancecolour);
				}

				
			}
		};
	
		
		
		firstline.addItemListener(itemListener);
		secondline.addItemListener(itemListener);
		thirdline.addItemListener(itemListener);
		fourthline.addItemListener(itemListener);


		fifthline.addItemListener(toleranceListener);
		tolerance.addItemListener(toleranceListener);
		
		
		
		
		textField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					Calculations.calculatefromwrite(lines,textField);
					fromtext=true;
			}
		});
		
		
		
		/*ohmwrite.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(textField.getText()!=null)
					if(fromtext)
						Calculations.calculatefromwrite(lines,textField);
				fromtext=false;
			}
		});*/
		
		ohmwrite.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER)
					if (textField.getText() != null) 
						Calculations.calculatefromwrite(lines, textField); 

			}
		});

		
	}
}
