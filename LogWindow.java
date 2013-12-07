import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

/**
 * Instance tridy {@code LogWindow} predstavuji zaznamove okno udalosti.
 *
 * @author  kolovsky
 * @author  jmacura
 * @version 1.00.000
 */
public class LogWindow extends JFrame implements Runnable
{
    //== PROMeNNe ATRIBUTY INSTANCi ============================================
    private final JTextArea ta;
    
    //== KONSTRUKTORY A TOVaRNi METODY =========================================
    /**
     *  Vytvori nove okno s textovym polem pro zaznamy a s posuvnikem.
     */
    public LogWindow()
    {
        this.setTitle("PT_SP: Event logging");
        
        //content = new JPanel(null);
        //content.setSize(new Dimension(500,300));
        //content.setBackground(new Color(250,250,250));
        
        //JScrollPane jsp = new JScrollPane(editable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //jsp.createVerticalScrollBar();
        
        //content.add(editable);
        //content.add(jsp);*/
        
        ta = new JTextArea("EVENTS:");
        ta.setBackground(new Color(250,250,250));
        ta.setForeground(new Color(0,0,0));
        ta.setFont(new Font("couriernew",0,10));
        ta.setLineWrap(true);
        ta.setAutoscrolls(true);
        ta.setEnabled(true);
        ta.setVisible(true);
        
        //content.add(ta);
        
        JScrollPane jsp = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //jsp.setPreferredSize(new Dimension(500,300));
        
        this.add(jsp);
        //this.add(content);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //this.setLocation(new Point(200,-200));
        //this.setLocationRelativeTo(Core.menu);
        //this.setPreferredSize(new Dimension(500,400));
    }

    //== OSTATNi NESOUKROMe METODY INSTANCi ====================================
    /**
     * Pripoji zadany retezec na konec stavajiciho zapisu v okne.
     * @param s Retezec, ktery se ma vypsat do okna.
     */
    public synchronized void log(String s)
    {
        ta.append("\n" + s);
    }
    
    /**
     * Nastavi velikost okna, vypocte pozici pro zobrazeni a vykresli okno na obrazovce.
     */
    @Override
    public synchronized void run()
    {
        this.setPreferredSize(new Dimension(500,500));
        this.pack();
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Point newLoc = new Point((screenSize.width / 4) + 300, screenSize.height / 2);
        Point mLoc = Core.menu.getLocationOnScreen();
        Point newLoc = new Point (mLoc.x + 300, mLoc.y);
        this.setLocation(newLoc);
        this.setVisible(true);
    }
}
