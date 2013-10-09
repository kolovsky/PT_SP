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


public class GUImenu extends JFrame {

    private JMenuBar menuBar;
    private JButton button1;
    private JButton button3;
    private JButton button4;

    //Constructor 
    public GUImenu(){

        this.setTitle("PT_SP");
        this.setSize(500,300);
        //menu generate method
        //generateMenu();
        //this.setJMenuBar(menuBar);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(495,426));
        contentPane.setBackground(new Color(204,204,255));

        /*
        MENU = new JLabel();
        MENU.setBounds(199,15,90,35);
        MENU.setBackground(new Color(214,217,223));
        MENU.setForeground(new Color(0,0,0));
        MENU.setEnabled(true);
        MENU.setFont(new Font("SansSerif",1,20));
        MENU.setText("MENU");
        MENU.setVisible(true);
        */

        button1 = new JButton();
        button1.setBounds(144,68,171,30);
        button1.setBackground(new Color(204,204,204));
        button1.setForeground(new Color(0,0,0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("Generate new data");
        button1.setVisible(true);
        //Set action for button click
        //Call defined method
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Core.generate(evt);
            }
        });

        button3 = new JButton();
        button3.setBounds(144,108,173,33);
        button3.setBackground(new Color(204,204,204));
        button3.setForeground(new Color(0,0,0));
        button3.setEnabled(true);
        button3.setFont(new Font("SansSerif",1,12));
        button3.setText("start simulation");
        button3.setVisible(true);
        //Set action for button click
        //Call defined method
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Core.start(evt);
            }
        });


        button4 = new JButton();
        button4.setBounds(145,150,176,36);
        button4.setBackground(new Color(204,204,204));
        button4.setForeground(new Color(0,0,0));
        button4.setEnabled(true);
        button4.setFont(new Font("sansserif",0,12));
        button4.setText("stop simulation");
        button4.setVisible(true);
        //Set action for button click
        //Call defined method
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Core.stop(evt);
            }
        });

        //adding components to contentPane panel
        //contentPane.add(MENU);
        contentPane.add(button1);
        contentPane.add(button3);
        contentPane.add(button4);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    /*
    //method for generate menu
    public void generateMenu(){
        menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu tools = new JMenu("Tools");
        JMenu help = new JMenu("Help");

        JMenuItem open = new JMenuItem("Open   ");
        JMenuItem save = new JMenuItem("Save   ");
        JMenuItem exit = new JMenuItem("Exit   ");
        JMenuItem preferences = new JMenuItem("Preferences   ");
        JMenuItem about = new JMenuItem("About   ");


        file.add(open);
        file.add(save);
        file.addSeparator();
        file.add(exit);
        tools.add(preferences);
        help.add(about);

        menuBar.add(file);
        menuBar.add(tools);
        menuBar.add(help);
    }*/



     public static void main(String[] args){
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUImenu();
            }
        });
    }

}