package resistor_finder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.DropMode;

public class SMDresistor extends JFrame {

	private JPanel contentPane;
	
	
	String[] resistortypes = { "4 şeritli", "5 şeritli", "SMD" };
	char ohm = 0x2126;
	String[] multiply = { Character.toString(ohm), "k" + Character.toString(ohm), "m" + Character.toString(ohm) };
	String[]smd_type_text= {"Digits","EIA-96"};
	
	JLabel smdpicture;
	Image resistor;
	JComboBox<String>resistortype;
	JTextField value_on_smd;
	JTextField numbers;
	JComboBox ohmwrite;
	JTextField txtDeneme;
	static boolean fromtext;
	JLabel textonsmdpicture;
	
	//silinecek
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		SMDresistor resistor= new SMDresistor();
		resistor.setVisible(true);

	}*/
	
	

	
	public SMDresistor(/*Resistorfinder fourband,Fivebandresistor fiveband*/) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		

		resistor= new ImageIcon("images/smd.png").getImage();
		
		
		
		textonsmdpicture = new JLabel("");
		textonsmdpicture.setFont(new Font("Tahoma", Font.PLAIN, 50));
		textonsmdpicture.setHorizontalAlignment(SwingConstants.CENTER);
		textonsmdpicture.setForeground(Color.WHITE);
		textonsmdpicture.setBounds(112, 61, 319, 172);
		textonsmdpicture.setBackground(Color.WHITE);
		contentPane.add(textonsmdpicture);
		
		
		
		smdpicture = new JLabel("");
		smdpicture.setBackground(Color.WHITE);
		smdpicture.setIcon(new ImageIcon(resistor));
		

		smdpicture.setBounds(20, 54, 527, 187);
		contentPane.add(smdpicture);
		
		resistortype = new JComboBox(resistortypes);
		resistortype.setBounds(20, 11, 99, 32);
		contentPane.add(resistortype);
		
		value_on_smd = new JTextField();
		value_on_smd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		value_on_smd.setHorizontalAlignment(SwingConstants.RIGHT);
		value_on_smd.setBounds(169, 244, 205, 54);
		contentPane.add(value_on_smd);
		value_on_smd.setColumns(10);
		
		numbers = new JTextField();
		numbers.setFont(new Font("Tahoma", Font.PLAIN, 15));
		numbers.setHorizontalAlignment(SwingConstants.RIGHT);
		numbers.setBounds(169, 319, 205, 54);
		contentPane.add(numbers);
		numbers.setColumns(10);
		
		ohmwrite = new JComboBox(multiply);
		ohmwrite.setBounds(384, 325, 58, 43);
		contentPane.add(ohmwrite);
		
		SMDresistor obj=this;
		resistortype.setSelectedIndex(2);
		value_on_smd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				Calculations.smdTexttoNumber(obj,value_on_smd.getText());
				fromtext=false;
			}
		});
		

		numbers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Calculations.valueToSmd(obj);
				fromtext = true;
			}
		});

		ohmwrite.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (numbers.getText() != null)
					if (fromtext)
						Calculations.valueToSmd(obj);
				fromtext = false;
			}
		});
		
		SMDresistor smd=this;
		resistortype.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainClass.selectFrameOnSmd(smd, smd.resistortype.getSelectedIndex());
				
			}
		});
		
		
		
	}
}
