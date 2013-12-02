/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kodovani: Prilis zlutoucky kun upel dabelske ody. */

import java.awt.*;
import javax.swing.*;

/*******************************************************************************
 * Instance tridy {@code LogWindow} predstavuji ...
 *
 * @author    jmeno autora
 * @version   0.00.000
 */
public class LogWindow extends JFrame implements Runnable
{
    //== KONSTANTNi ATRIBUTY TriDY =============================================
    //== PROMeNNe ATRIBUTY TriDY ===============================================
    //== STATICKy INICIALIZAcNi BLOK - STATICKy KONSTRUKTOR ====================
    //== KONSTANTNi ATRIBUTY INSTANCi ==========================================
    //== PROMeNNe ATRIBUTY INSTANCi ============================================
    //JPanel content;
    JScrollPane jsp;
    JTextArea ta;
    
    //== PriSTUPOVe METODY VLASTNOSTi TriDY ====================================
    //== OSTATNi NESOUKROMe METODY TriDY =======================================
    
    //##########################################################################
    //== KONSTRUKTORY A TOVaRNi METODY =========================================

    /***************************************************************************
     *
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
        
        jsp = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //jsp.setPreferredSize(new Dimension(500,300));
        
        this.add(jsp);
        //this.add(content);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //this.setLocation(new Point(200,-200));
        //this.setLocationRelativeTo(Core.menu);
        //this.setPreferredSize(new Dimension(500,400));
    }

    //== PriSTUPOVe METODY VLASTNOSTi INSTANCi =================================
    //== OSTATNi NESOUKROMe METODY INSTANCi ====================================
    
    public synchronized void log(String s)
    {
        ta.append("\n" + s);
    }
    
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
    
    //== SOUKROMe A POMOCNe METODY TriDY =======================================
    //== SOUKROMe A POMOCNe METODY INSTANCi ====================================
    //== INTERNi DATOVe TYPY ===================================================
    //== TESTOVACi METODY A TriDY ==============================================
    //
    //     /********************************************************************
    //      * Testovaci metoda.
    //      */
    //     public static void test()
    //     {
    //         LogWindow instance = new LogWindow();
    //     }
    //     /** @param args Parametry prikazoveho radku - nepouzivane. */
    //     public static void main(String[] args)  {  test();  }
}
