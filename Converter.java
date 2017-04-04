import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A class to allow simple binary conversions to various representations such as denary, octal, hexadecimal and IEEE 754 standard.
 * To use in command line: > java Converter
 *
 * @author Yash Sandu & Harry Baines
 */
public class Converter implements ActionListener
{
    // window, panels + components
    private JFrame window;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel wrapperPanel;
    private JPanel actionPanel;
    
    private JLabel titleLbl;
    private JLabel enterBinaryLbl;
    private JLabel resultBinaryLbl;
    private JLabel decimalResultLbl;
    private JTextField binaryEntry;
    private JButton convertBtn;
    
    private static final Font binaryFont = new Font("Courier New", Font.BOLD, 22);
    
    // variables
    private int powerValue = 0;
    private int runningTotal = 0;
    private String resultStr = "";
    
    // constants
    private static final int SCREEN_WIDTH = 400;
    private static final int SCREEN_HEIGHT = 300;

    /**
     * Simple constructor to set initial values.
     */
    public Converter()
    {
        // create window, panels and layouts
        window = new JFrame();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        topPanel = new JPanel();
        centerPanel = new JPanel(new BorderLayout());
        
        wrapperPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        actionPanel = new JPanel();
        
        // add sub-panels to main panel
        mainPanel.add("North", topPanel);
        mainPanel.add("Center", centerPanel);
        mainPanel.add("South", actionPanel);
        
        // create components to display
        titleLbl = new JLabel("Binary Converter");
        convertBtn = new JButton("Convert");
        convertBtn.addActionListener(this);
        enterBinaryLbl = new JLabel("Enter Binary:");
        resultBinaryLbl = new JLabel("In Decimal:");
        decimalResultLbl = new JLabel("");

        // entry field
        binaryEntry = new JTextField();
        binaryEntry.setPreferredSize(new Dimension(120,30));
        binaryEntry.setFont(binaryFont);
        binaryEntry.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                if (binaryEntry.getText().length() >= 8) // limit textfield to 8 characters
                    e.consume();
            }  
        });
        
        // add components to panels
        topPanel.add(titleLbl);
        actionPanel.add(convertBtn);
        
        centerPanel.add(wrapperPanel, BorderLayout.WEST);
        
        c.gridx = 0;
        c.gridy = 0;
        wrapperPanel.add(enterBinaryLbl, c);
        
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 20;
        wrapperPanel.add(binaryEntry, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(20,0,0,0);
        wrapperPanel.add(resultBinaryLbl, c);
        
        c.gridx = 1;
        c.gridy = 1;
        wrapperPanel.add(decimalResultLbl, c);

        window.setContentPane(mainPanel);
        window.setTitle("Binary Converter");
        window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(200,200);
        window.setResizable(false);
        window.setVisible(true);
    }
    
    /**
     * Detects if a button on the UI has been pressed.
     *
     * @param e The action event instance.
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == convertBtn)
        {
            if (toBinary(binaryEntry.getText()))
            {
                decimalResultLbl.setText(Integer.toString(runningTotal));
                System.out.println("Decimal version of " + binaryEntry.getText() + " is: " + runningTotal);
            }
            else
                System.out.println("Error - invalid format for conversion.");
        }
    }
    
    /**
     * A method to calculate the binary equivalent of the passed string parameter.
     *
     * @param s The string to convert to binary.
     * @return true if binary conversion was successful.
     */
    private boolean toBinary(String s)
    {
        runningTotal = 0;
        
        if (s.equals(""))
            return false;
        
        for (int i = 0; i < s.length(); i++)
        {
            // for each character, do a validation check
            if (s.charAt(i) != '0' && s.charAt(i) != '1')
                return false;
            else
            {
                // retrieve power of 2 (working from left)
                powerValue = (s.length() - i) - 1;
                if (s.charAt(i) != '0')
                {
                    // you have 1 at this point, so add to total
                    runningTotal += (int) (Math.pow(2, powerValue));
                }
            }
        }
        return true;
    }
    
    /**
     * Main method used to create a new instance of this Converter class.
     * 
     * @param args unused.
     */
    public static void main(String[] args)
    {
        Converter c = new Converter();
    }
}
