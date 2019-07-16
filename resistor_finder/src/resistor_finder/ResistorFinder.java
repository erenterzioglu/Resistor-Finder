package resistor_finder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class ResistorFinder extends JFrame  {
    private static Integer r,g,b;
    private static boolean fromText;

    private static JPanel firstLineColour;
    private static JPanel secondLineColour;
    private static JPanel thirdLineColour;
    private static JPanel fourthLineColour;
    private static JPanel toleranceColour;

    private JLabel lblNewLabel;

    private static JComboBox <String> ohmWrite;
    private static JComboBox <String> firstLine;
    private static JComboBox <String> secondLine;
    private static JComboBox <String> thirdLine;
    private static JComboBox <String> fourthLine;
    private static JComboBox <String> tolerance;

    private JPanel contentPane;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ResistorFinder frame = new ResistorFinder();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public ResistorFinder() {
        final char ohm = 0x2126;

        final String[] multiply = {
                Character.toString(ohm),
                "k" + ohm,
                "m" + ohm
        };

        final String[] colours = {
                "Siyah",
                "Kahverengi",
                "Kırmızı",
                "Turuncu",
                "Sarı",
                "Yeşil",
                "Mavi",
                "Mor",
                "Gri",
                "Beyaz"
        };

        final String[] toleranceColours = { "Altın", "Gümüş", "Yok" };

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 573, 573);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Image resistor = new ImageIcon("images/resistor1.png").getImage();

        firstLineColour = new JPanel();
        firstLineColour.setForeground(new Color(0, 0, 0));
        firstLineColour.setBackground(new Color(0, 0, 0));
        firstLineColour.setBounds(139, 69, 10, 146);
        contentPane.add(firstLineColour);

        secondLineColour = new JPanel();
        secondLineColour.setBackground(new Color(0, 0, 0));
        secondLineColour.setBounds(201, 95, 10, 98);
        contentPane.add(secondLineColour);

        thirdLineColour = new JPanel();
        thirdLineColour.setBackground(new Color(0, 0, 0));
        thirdLineColour.setBounds(257, 95, 10, 98);
        contentPane.add(thirdLineColour);

        fourthLineColour = new JPanel();
        fourthLineColour.setBackground(new Color(239,228,176));
        fourthLineColour.setBounds(306, 95, 10, 98);
        contentPane.add(fourthLineColour);

        toleranceColour = new JPanel();
        toleranceColour.setForeground(Color.BLACK);
        toleranceColour.setBackground(new Color(212,175,55));
        toleranceColour.setBounds(424, 73, 10, 146);
        contentPane.add(toleranceColour);

        lblNewLabel = new JLabel("");
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setIcon(new ImageIcon(resistor));

        lblNewLabel.setBounds(43, 54, 476, 169);
        contentPane.add(lblNewLabel);

        ohmWrite = new JComboBox<>(multiply);
        ohmWrite.setBounds(384, 234, 60, 32);
        contentPane.add(ohmWrite);

        firstLine = new JComboBox<>(colours);
        firstLine.setBounds(30, 333, 99, 39);
        contentPane.add(firstLine);

        secondLine = new JComboBox<>(colours);
        secondLine.setBounds(153, 333, 99, 39);
        contentPane.add(secondLine);

        thirdLine = new JComboBox<>(colours);
        thirdLine.setBounds(293, 333, 100, 39);
        contentPane.add(thirdLine);

        fourthLine = new JComboBox<>(toleranceColours);
        fourthLine.setBounds(432, 333, 100, 39);
        contentPane.add(fourthLine);

        char tol = 177;
        String[] tolerance_val = {tol + "5", tol + "10", tol + "20"};
        tolerance = new JComboBox<>(tolerance_val);
        tolerance.setBounds(459, 234, 60, 32);
        contentPane.add(tolerance);

        textField = new JTextField();

        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setBounds(167, 234, 176, 32);
        contentPane.add(textField);
        textField.setColumns(10);

        ItemListener itemListener = e -> {
            if (e.getSource() instanceof JComboBox) {
                setColour(e);
            }
        };

        ItemListener toleranceListener = event -> {
            if (event.getSource() instanceof JComboBox) {
                if (tolerance.getSelectedIndex() != fourthLine.getSelectedIndex())
                    setToleranceColour(event);
            }
        };

        firstLine.addItemListener(itemListener);
        secondLine.addItemListener(itemListener);
        thirdLine.addItemListener(itemListener);
        fourthLine.addItemListener(toleranceListener);
        tolerance.addItemListener(toleranceListener);

        textField.addActionListener(e -> calculateFromWrite());

        ohmWrite.addItemListener(e -> {
            if(textField.getText()!=null)
                calculateFromWrite();
        });
    }

    private void setColour(ItemEvent e) {
        indexToColour(((JComboBox<String>) e.getSource()).getSelectedIndex());
        JPanel line;

        if (e.getSource() == firstLine) {
            line = firstLineColour;
        } else if (e.getSource() == secondLine) {
            line = secondLineColour;
        } else if (e.getSource() == thirdLine) {
            line = thirdLineColour;
        } else {
            throw new RuntimeException("Error on setColour");
        }

        if (line != null) {
            line.setBackground(new Color(r, g ,b));
        }

        if(!fromText)
            calculateOhm();
    }

    private void indexToColour(int index) {
        switch (index) {
            case 0: r = 0;      g = 0;      b = 0;      break;
            case 1: r = 100;    g = 51;     b = 0;      break;
            case 2: r = 255;    g = 0;      b = 0;      break;
            case 3: r = 255;    g = 165;    b = 0;      break;
            case 4: r = 255;    g = 255;    b = 0;      break;
            case 5: r = 0;      g = 255;    b = 0;      break;
            case 6: r = 0;      g = 0;      b = 255;    break;
            case 7: r = 191;    g = 0;      b = 255;    break;
            case 8: r = 127;    g = 127;    b = 127;    break;
            case 9: r = 255;    g = 255;    b = 255;    break;
            default:
                throw new RuntimeException("Invalid index");
        }
    }

    private void setToleranceColour(ItemEvent e) {
        int toleranceIndex = ((JComboBox<String>) e.getSource()).getSelectedIndex();

        switch (toleranceIndex) {
            case 0: r = 215; g = 175; b = 55;  break;
            case 1: r = 192; g = 192; b = 192; break;
            case 2: r = 239; g = 228; b = 176; break;
            default:
                throw new RuntimeException("Invalid Tolerance Index");
        }

        if(e.getSource() == fourthLine)
            tolerance.setSelectedIndex(fourthLine.getSelectedIndex());
        else
            fourthLine.setSelectedIndex(tolerance.getSelectedIndex());

        toleranceColour.setBackground(new Color(r,g,b));
    }

    private void calculateOhm() {
        double value= (firstLine.getSelectedIndex() * 10) + secondLine.getSelectedIndex();
        double multiplier = Math.pow(10, thirdLine.getSelectedIndex());
        double valueToWrite;

        valueToWrite = value * multiplier;

        if(valueToWrite >= Math.pow(10, 6)) {
            valueToWrite /= Math.pow(10, 6);
            ohmWrite.setSelectedIndex(2);
        } else if(valueToWrite < Math.pow(10, 3)) {
            ohmWrite.setSelectedIndex(0);
        } else {
            valueToWrite /= 1000;
            ohmWrite.setSelectedIndex(1);
        }

        String temp = (valueToWrite == (int)valueToWrite) ? String.valueOf((int) valueToWrite)
                : Double.toString(valueToWrite);

        textField.setText(temp);
    }

    private void calculateFromWrite() {
        fromText = true;
        double value = Double.parseDouble(textField.getText())
                * Math.pow(10, ohmWrite.getSelectedIndex() * 2);
        int index = ohmWrite.getSelectedIndex();

        while (value >= 100) {
            index++;
            value /= 10;
        }

        int temp = (int)value / 10;
        value %= 10;

        firstLine.setSelectedIndex(temp);
        secondLine.setSelectedIndex((int) value);
        thirdLine.setSelectedIndex(index);
        fromText = false;
    }
}