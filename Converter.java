import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A class to allow simple binary conversions to various representations such as hexadecimal, octal and IEEE 754 standard.
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
    private JTextField binaryEntry;
    private JButton convertBtn;
    
    private static final Font binaryFont = new Font("Courier New", Font.BOLD, 30);
    
    // constants
    private static final int SCREEN_WIDTH = 400;
    private static final int SCREEN_HEIGHT = 500;

    /**
     * Simple constructor to set initial values.
     */
    public Converter()
    {
        // create windows and panels
        window = new JFrame();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        topPanel = new JPanel();
        centerPanel = new JPanel(new GridLayout(3,5));
        wrapperPanel = new JPanel(new FlowLayout(10,0,FlowLayout.LEADING));
        actionPanel = new JPanel();
        
        // add components to panels
        mainPanel.add("North", topPanel);
        mainPanel.add("Center", centerPanel);
        mainPanel.add("South", actionPanel);
        
        // create components to display
        titleLbl = new JLabel("Binary Converter");
        convertBtn = new JButton("Convert");
        convertBtn.addActionListener(this);
        enterBinaryLbl = new JLabel("Enter Binary:");

        binaryEntry = new JTextField();
        binaryEntry.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                if (binaryEntry.getText().length() >= 8) // limit textfield to 3 characters
                    e.consume();
            }  
        });
        
        binaryEntry.setPreferredSize(new Dimension(170,50));
        binaryEntry.setFont(binaryFont);
        
        topPanel.add(titleLbl);
        actionPanel.add(convertBtn);
        
        wrapperPanel.add(enterBinaryLbl);
        wrapperPanel.add(binaryEntry);
        centerPanel.add(wrapperPanel);
        
        // further window details
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
     * @param e the action event instance.
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == convertBtn)
        {
            System.out.println(binaryEntry.getText());
        }
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
