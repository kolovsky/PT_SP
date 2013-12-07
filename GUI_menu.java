import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
//import javax.swing.border.Border;
//import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Instance tridy {@code GUI_menu} predstavuji jednoduche uzivatelske rozhrani pro ovladani programu.
 * 
 * @author  kolovsky
 * @author  jmacura
 * @version 1.00.000
 */
public class GUI_menu extends JFrame implements Runnable
{
    //== PROMeNNe ATRIBUTY INSTANCi ============================================
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    //private JButton button9;

    //== KONSTRUKTORY A TOVaRNi METODY =========================================
    /**
     * Vytvori nove graficke menu s pevne danym layoutem.
     */
    public GUI_menu() {

        this.setTitle("PT_SP");

        JPanel contentPane = initComponents();

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLocationRelativeTo(null);
    }

    private JPanel initComponents()
    {
        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setBackground(new Color(200,200,200));
        
        initButton1();
        initButton2();
        initButton3();
        initButton4();
        initButton5();
        initButton6();
        initButton7();
        initButton8();
        //initButton9();

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
        //contentPane.add(button9);
        contentPane.add(label1);
        contentPane.add(label2);
        
        return contentPane;
    }
    
    //== OSTATNi NESOUKROMe METODY INSTANCi ====================================
    /**
     * Nastavi velikost okna a vykresli menu na obrazovku.
     */
    @Override
    public void run() {
        this.setPreferredSize(new Dimension(300,500));
        this.pack();
        //this.setLocationByPlatform(true);
        this.setVisible(true);
    }
    
    //== SOUKROMe A POMOCNe METODY INSTANCi ====================================
    private void initButton1()
    {
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
        //return button1;
    }
    
    private void initButton2()
    {
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
        //return button2;
    }
    
    private void initButton3()
    {
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
    }
    
    private void initButton4()
    {
        button4 = new JButton();
        button4.setBounds(50,235,190,40);
        button4.setBackground(new Color(200,200,200));
        button4.setEnabled(false);
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
    }
    
    private void initButton5()
    {
        button5 = new JButton();
        button5.setBounds(50,290,190,40);
        button5.setBackground(new Color(200,200,200));
        button5.setForeground(new Color(0,0,0));
        button5.setEnabled(false);
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
    }
    
    private void initButton6()
    {
        button6 = new JButton();
        button6.setBounds(50,335,190,40);
        button6.setBackground(new Color(200,200,200));
        button6.setForeground(new Color(0,0,0));
        button6.setEnabled(false);
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
    }
    
    private void initButton7()
    {
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
    }
    
    private void initButton8()
    {
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
    }
    
    /*private void initButton9()
    {
        button9 = new JButton();
        button9.setBounds(50,290,190,40);
        button9.setBackground(new Color(200,200,200));
        button9.setForeground(new Color(0,0,0));
        button9.setEnabled(false);
        button9.setFont(new Font("sansserif",1,12));
        button9.setText("Zrusit simulaci vcetne dat");
        button9.setVisible(false);
        //Set action for button click
        //Call defined method
        button9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                abort();
            }
        });
    }*/
    
    private void catchMeIfYouCan(Exception e, String s)
    {
        Core.exceptions.add(e);
        if(s.length() == 0)
        {
            Core.log("Zadejte souradnici");
        }
        else
        {
            Core.log("Souradnice musi byt prirozene cislo!");
        }
    }
    
    private int getPopulation()
    {
        int people = -1;
        String pop = JOptionPane.showInputDialog(this, "Zadejte pocet obyvatel (1 - " + Integer.MAX_VALUE + "):");
        try
        {
            people = Integer.parseInt(pop);
            if (people <= 0)
            {
                throw new RuntimeException("Zadan zaporny pocet obyvatel");
            }
        }
        catch(NumberFormatException naN) //Not a Number
        {
            Core.exceptions.add(naN);
            if(pop.length() == 0)
            {
                Core.log("Zadejte pocet obyvatel");
            }
            else
            {
                Core.log("Pocet obyvatel znamena prirozene cislo mensi nez " + Integer.MAX_VALUE + "!");
            }
            return -1;
        }
        catch(Exception e) //<0
        {
            Core.exceptions.add(e);
            Core.log("Zadejte pocet obyvatel vetsi nez 0");
            return -1;
        }
        return people;
    }
    
    private int getXCoord()
    {
        int x = -1;
        String xx = JOptionPane.showInputDialog(this, "Zadejte souradnici x (0 - 500 000):");
        try
        {
            x = Integer.parseInt(xx);
            if (x < 0 || x > 500000)
            {
                throw new RuntimeException("Souradnice x musi byt v rozmezi 0 a 500 000");
            }
        }
        catch(NumberFormatException naN) //Not a Number
        {
            catchMeIfYouCan(naN, xx);
            return -1;
        }
        catch(Exception e) //Out of bounds
        {
            Core.exceptions.add(e);
            Core.log("Zadejte souradnici v danem rozmezi");
            return -1;
        }
        return x;
    }
    
    private int getYCoord()
    {
        int y = -1;
        String yy = JOptionPane.showInputDialog(this, "Zadejte souradnici y (0 - 500 000):");
        try
        {
            y = Integer.parseInt(yy);
            if (y < 0 || y > 500000)
            {
                throw new RuntimeException("Souradnice y musi byt v rozmezi 0 a 500 000");
            }
        }
        catch(NumberFormatException naN) //Not a Number
        {
            catchMeIfYouCan(naN, yy);
            return -1;
        }
        catch(Exception e) //Out of bounds
        {
            Core.exceptions.add(e);
            Core.log("Zadejte souradnici v danem rozmezi");
            return -1;
        }
        return y;
    }
    
    //Method actionPerformed for button2
    private void generate() {
        Core.generateNew();
        button1.setEnabled(false);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
    }

    //Method actionPerformed for button5
    private void kickThisPig() {
        Core.start();
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        //button5.setVisible(false);
        button6.setEnabled(true);
        //button9.setVisible(true);
        //button9.setEnabled(true);
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
        int pop = getPopulation();
        if (pop < 0) {return;}
        int x = getXCoord();
        if (x < 0) {return;}
        int y = getYCoord();
        if (y < 0) {return;}
        Core.addSettle(x, y, pop);
    }

    //Method actionPerformed for button1
    private void load(){
        Core.load();
        button2.setEnabled(false);
        button4.setEnabled(true);
        button5.setEnabled(true);
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
    
    //Method actionPerformed for button9
    /*private void abort()
    {
        Core.abort();
        button1.setEnabled(true);
        button2.setEnabled(true);
        //button3.setEnabled(false);
        //button4.setEnabled(false);
        button5.setEnabled(true);
        button5.setVisible(true);
        button6.setEnabled(false);
        button7.setEnabled(false);
        button8.setEnabled(false);
        //button9.setEnabled(false);
        //button9.setVisible(false);
    }*/
    
    //== INTERNi DATOVe TYPY ===================================================
    /**
     * Instance tridy {@code CheckDialog} predstavuji dotazovaci dialog pro kontrolu objektu sumulace.
     * Vytvareni takovych dialogu je pouzivano jen instancemi tridy {@code GUI_menu},
     * proto se jedna o tridu vnitrni.
     * @author  kolovsky
     * @author  jmacura
     * @version 1.00.000
     */
    class CheckDialog extends JDialog
    {
        //== KONSTANTNi ATRIBUTY INSTANCi ==========================================
        private final JPanel jdCont;
        private final ButtonGroup bg;
        private final JRadioButton opt1;
        private final JRadioButton opt2;
        private final JRadioButton opt3;
        private final JLabel info;
        private final JTextField line;
        private final JButton sender;
        
        //== PROMeNNe ATRIBUTY INSTANCi ============================================
        private int bounds;
        
        //== KONSTRUKTORY A TOVaRNi METODY =========================================
        /**
         * Vytvori dotazovaci dialog s prednastavenym layoutem, nastavi jeho pozici a vykresli jej na obrazovku.
         * 
         * @param owner Nadrazene okno {@code java.awt.Frame}.
         * @param title Jmeno dialogoveho okna.
         */
        CheckDialog(Frame owner, String title)
        {
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
        
        //== SOUKROMe A POMOCNe METODY INSTANCi ====================================
        private void chngInfo(ActionEvent evt)
        {
            String action = evt.getActionCommand();
            bounds = Core.getProcessNumber(action) - 1;
            if (bounds <= 0)
            {
                info.setText("Tento objekt se v simulaci nevyskytuje");
            }
            else
            {
                info.setText("ID musi byt v rozmezi 1 az " + bounds);
            }
        }
        
        private void send()
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
                Core.log("ID objektu je prirozene cislo!");
                Core.exceptions.add(e);
                return;
            }
            if(verifyInput(id))
            {
                Core.check(action, id);
            }
            else
            {
                Core.log("ID musi byt v uvedenem rozsahu");
                return;
            }
        }
        
        private boolean verifyInput(int id)
        {
            if (id <= 0 || id > bounds)
            {
                return false;
            }
            return true;
        }
    }
}
