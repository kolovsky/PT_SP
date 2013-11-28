/**
*Text genereted by Simple GUI Extension for BlueJ
*/
import java.awt.*;
import java.awt.event.*;
//import javax.swing.border.Border;
//import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.*;
import java.util.*;

public class GUI_menu extends JFrame implements Runnable {

    private final JButton button1;
    private final JButton button2;
    private final JButton button3;
    private final JButton button4;
    private final JButton button5;
    private final JButton button6;
    private final JButton button7;
    private final JButton button8;

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
        button1.setText("Nacist data");
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
        button2.setText("Generovat nova data");
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
        button3.setText("Ulozit nova data");
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
        button4.setText("Pridat sidlo");
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
        button5.setText("Spustit simulaci");
        button5.setVisible(true);
        //Set action for button click
        //Call defined method
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                kickThisPig();
            }
        });

        button6 = new JButton();
        button6.setBounds(50,335,190,40);
        button6.setBackground(new Color(200,200,200));
        button6.setForeground(new Color(0,0,0));
        button6.setEnabled(true);
        button6.setFont(new Font("sansserif",0,12));
        button6.setText("Pozastavit simulaci");
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
        button7.setText("Pokracovat v simulaci");
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
        button8.setText("Zjistit stav objektu");
        button8.setVisible(true);
        //Set action for button click
        //Call defined method
        button8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                check();
            }
        });

        JLabel label1 = new JLabel();
        label1.setBounds(115,45,200,50);
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(0,0,0));
        label1.setEnabled(true);
        label1.setFont(new Font("SansSerif",1,20));
        label1.setText("MENU");
        label1.setVisible(true);

        JLabel label2 = new JLabel();
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
    private void kickThisPig() {
        Core.start();
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
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
        int people = 1; //pracovne
        int x = 1; //pracovne
        int y = 1; //pracovne
        //JOptionPane msg = new JOptionPane();
        //msg.setMessageType(JOptionPane.QUESTION_MESSAGE);
        //msg.setMessage("hello");
        //msg.setName("Adding new settlement");
        try
        {
            String pop = JOptionPane.showInputDialog(this, "Zadejte pocet obyvatel:");
            //System.out.println(pop);
            people = Integer.parseInt(pop);
            String xx = JOptionPane.showInputDialog(this, "Zadejte souradnici x:");
            x = Integer.parseInt(xx);
            String yy = JOptionPane.showInputDialog(this, "Zadejte souradnici y:");
            y = Integer.parseInt(yy);
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            Core.exceptions.add(e);
            return;
        }
        Core.addSettle(x, y, people);
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
        new CheckDialog(this, "Vyberte objekt pro sledovani");
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
     * Vnitrni trida.
     */
    class CheckDialog extends JDialog
    {
        private final JPanel jdCont;
        private final ButtonGroup bg;
        private final JRadioButton opt1;
        private final JRadioButton opt2;
        private final JRadioButton opt3;
        private final JLabel info;
        private final JTextField line;
        private final JButton sender;
        
        //private int bounds;
        
        /**
         * @param owner Nadrazene okno {@code java.awt.Frame}.
         * @param title Jmeno dialogoveho okna.
         */
        CheckDialog(Frame owner, String title)
        {
            //super(owner, title);
            this.setTitle(title);
            this.setPreferredSize(new Dimension(200,200));
            
            jdCont = new JPanel();
            jdCont.setPreferredSize(new Dimension(200,200));
            
            bg = new ButtonGroup();
            
            ActionListener change = new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent evt)
                {
                    chngInfo(evt);
                }
            };
            
            opt1 = new JRadioButton("Sidlo");
            opt1.setBounds(10,5,10,10);
            opt1.setSize(10, 10);
            opt1.setLocation(10, 5);
            opt1.setSelected(true);
            opt1.setOpaque(true);
            opt1.setActionCommand("settle");
            opt1.addActionListener(change);
            
            opt2 = new JRadioButton("Vrtulnik");
            opt2.setBounds(30,5,10,10);
            opt2.setOpaque(true);
            opt2.setActionCommand("helicop");
            opt2.addActionListener(change);
            
            opt3 = new JRadioButton("Auto");
            opt3.setBounds(50,5,10,10);
            opt3.setOpaque(true);
            opt3.setActionCommand("car");
            opt3.addActionListener(change);
            
            bg.add(opt1);
            bg.add(opt2);
            bg.add(opt3);
            
            info = new JLabel("ID musi byt v danem rozmezi!", JLabel.CENTER);
            
            line = new JTextField(10);
            //line.setBounds(10,20,100,20);
            //line.setSize(100, 20);
            //line.setAlignmentX(100);
            line.setEnabled(true);
            line.setOpaque(true);
            
            sender = new JButton();
            sender.setBounds(50,50,100,20);
            sender.setBackground(new Color(200,200,200));
            sender.setEnabled(true);
            sender.setFont(new Font("sansserif",0,12));
            sender.setText("Zjistit!");
            sender.setVisible(true);
            sender.setOpaque(true);
            sender.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    send();
                }
            });
            
            jdCont.add(opt1);
            jdCont.add(opt2);
            jdCont.add(opt3);
            jdCont.add(info);
            jdCont.add(line);
            jdCont.add(sender);
            
            this.setContentPane(jdCont);
            //this.setPreferredSize(new Dimension(200,200));
            this.setLocationRelativeTo(owner);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.pack();
            this.setVisible(true);
        }
        
        private void chngInfo(ActionEvent evt)
        {
            String action = evt.getActionCommand();
            int bounds = 0;
            bounds = Core.getProcessNumber(action) - 1;
            info.setText("ID musi byt v rozmezi 0 az " + bounds);
        }
        
        public void send()
        {
            Enumeration<AbstractButton> eab = bg.getElements();
            String action = "", idS;
            int id = 0;
            while(eab.hasMoreElements())
            {
                AbstractButton x = eab.nextElement();
                if (x.isSelected())
                {
                    action = x.getActionCommand();
                    break;
                }
            }
            try
            {
                idS = line.getText();
                id = Integer.parseInt(idS);
            }
            catch(Exception e)
            {
                Core.log("Chybny format ID");
                Core.exceptions.add(e);
            }
            
            Core.check(action, id);
        }
    }
}
