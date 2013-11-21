/**
*Text genereted by Simple GUI Extension for BlueJ
*/
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.border.Border;
import javax.swing.*;


public class GUI_menu extends JFrame implements Runnable {

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JLabel label1;
    private JLabel label2;

    //Constructor 
    public GUI_menu() {

        this.setTitle("PT_SP");

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        //contentPane.setPreferredSize(new Dimension(300,400));
        contentPane.setBackground(new Color(200,200,200));

        button1 = new JButton();
        button1.setBounds(50,100,190,40);
        button1.setBackground(new Color(200,200,200));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("Load data");
        button1.setVisible(true);
        //Set action for button click
        //Call defined method
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                load();
            }
        });

        button2 = new JButton();
        button2.setBounds(50,145,190,40);
        button2.setBackground(new Color(200,200,200));
        button2.setEnabled(true);
        button2.setFont(new Font("sansserif",0,12));
        button2.setText("Generate new data");
        button2.setVisible(true);
        //Set action for button click
        //Call defined method
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                generate();
            }
        });

        button3 = new JButton();
        button3.setBounds(50,190,190,40);
        button3.setBackground(new Color(200,200,200));
        button3.setEnabled(false);
        button3.setFont(new Font("sansserif",0,12));
        button3.setText("Save new data");
        button3.setVisible(true);
        //Set action for button click
        //Call defined method
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                save();
            }
        });

        button4 = new JButton();
        button4.setBounds(50,235,190,40);
        button4.setBackground(new Color(200,200,200));
        button4.setEnabled(true);
        button4.setFont(new Font("sansserif",0,12));
        button4.setText("Add new settlement");
        button4.setVisible(true);
        //Set action for button click
        //Call defined method
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addNode();
            }
        });
        
        button5 = new JButton();
        button5.setBounds(50,290,190,40);
        button5.setBackground(new Color(200,200,200));
        button5.setForeground(new Color(0,0,0));
        button5.setEnabled(true);
        button5.setFont(new Font("SansSerif",1,12));
        button5.setText("Start simulation");
        button5.setVisible(true);
        //Set action for button click
        //Call defined method
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                go();
            }
        });

        button6 = new JButton();
        button6.setBounds(50,335,190,40);
        button6.setBackground(new Color(200,200,200));
        button6.setForeground(new Color(0,0,0));
        button6.setEnabled(true);
        button6.setFont(new Font("sansserif",0,12));
        button6.setText("Pause simulation");
        button6.setVisible(true);
        //Set action for button click
        //Call defined method
        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pause();
            }
        });
        
        button7 = new JButton();
        button7.setBounds(50,335,190,40);
        button7.setBackground(new Color(200,200,200));
        button7.setForeground(new Color(0,0,0));
        button7.setEnabled(false);
        button7.setFont(new Font("sansserif",0,12));
        button7.setText("Continue simulation");
        button7.setVisible(false);
        //Set action for button click
        //Call defined method
        button7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cont();
            }
        });
        
        button8 = new JButton();
        button8.setBounds(50,380,190,40);
        button8.setBackground(new Color(200,200,200));
        button8.setForeground(new Color(0,0,0));
        button8.setEnabled(false);
        button8.setFont(new Font("sansserif",0,12));
        button8.setText("Check status of ...");
        button8.setVisible(true);
        //Set action for button click
        //Call defined method
        button8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                check();
            }
        });

        label1 = new JLabel();
        label1.setBounds(115,45,200,50);
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(0,0,0));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif",1,20));
        label1.setText("MENU");
        label1.setVisible(true);

        label2 = new JLabel();
        label2.setBounds(125,10,50,50);
        label2.setBackground(new Color(214,217,223));
        label2.setForeground(new Color(0,0,0));
        label2.setEnabled(true);
        label2.setFont(new Font("SansSerif",1,14));
        label2.setText("PT_SP");
        label2.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(button1);
        contentPane.add(button2);
        contentPane.add(button3);
        contentPane.add(button4);
        contentPane.add(button5);
        contentPane.add(button6);
        contentPane.add(button7);
        contentPane.add(button8);
        contentPane.add(label1);
        contentPane.add(label2);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(300,500));
        //this.setLocationRelativeTo(null);
    }

    //Method actionPerformed for button2
    private void generate() {
        Core.generateNew();
        button3.setEnabled(true);
    }

    //Method actionPerformed for button5
    private void go() {
        Core.start();
        button5.setEnabled(false);
    }

    //Method actionPerformed for button7
    private void pause() {
        button6.setEnabled(false);
        button6.setVisible(false);
        button7.setVisible(true);
        button7.setEnabled(true);
        button8.setEnabled(true);
        Core.stop();
        //notifyAll();
    }

    //Method actionPerformed for button3
    private void save() {
        Core.save();
    }
    
    //Method actionPerformed for button4
    private void addNode() {
        int id = 1; //pracovne
        int x = 1; //pracovne
        int y = 1; //pracovne
        SettleNode n = new SettleNode(id, x, y);
        //JOptionPane msg = new JOptionPane();
        //msg.setMessageType(JOptionPane.QUESTION_MESSAGE);
        //msg.setMessage("hello");
        //msg.setName("Adding new settlement");
        String pop = JOptionPane.showInputDialog(this, new JOptionPane(), "Adding new settlement", JOptionPane.QUESTION_MESSAGE);
        System.out.println(pop);
        try
        {
            n.people = Integer.parseInt(pop);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Core.exceptions.add(e);
        }
        Core.g.addNode(n);
    }

    //Method actionPerformed for button1
    private void load(){
        Core.load();
    }
    
    //Method actionPerformed for button6
    private void cont(){
        Core.stop();
        button7.setEnabled(false);
        button7.setVisible(false);
        button6.setVisible(true);
        button6.setEnabled(true);
        button8.setEnabled(false);
    }
    
    //Method actionPerformed for button8
    private void check(){
        int[] answers = new int[2];
        
        MyDialog jxd = new MyDialog(this, "Pick a jew");
        
        Core.check(answers[0], answers[1]);
    }
    
    @Override
    public void run() {
        this.pack();
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Point newLoc = new Point(screenSize.width / 2 - (this.getWidth() / 2), screenSize.height / 2 - (this.getWidth() / 2));
        //Point newLocation = new Point(middle.x - , middle.y));
        //this.setLocation(newLoc);
        //this.setLocationByPlatform(true);
        this.setVisible(true);
    }
    
    /**
     * Nested class
     */
    class MyDialog extends JDialog
    {
        MyDialog(Frame owner, String title)
        {
            super(owner, title);
            JPanel jdCont = new JPanel();
            jdCont.add(new JRadioButton());
            jdCont.add(new JRadioButton());
            jdCont.add(new JRadioButton());
            
            this.setPreferredSize(new Dimension(200,150));
            this.setContentPane(jdCont);
            this.setLocationRelativeTo(Core.menu);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.pack();
            this.setVisible(true);
        }
    }
}