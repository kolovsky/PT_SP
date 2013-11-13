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
    JPanel content;
    //JEditorPane editable;
    JScrollPane jsp;
    JTextArea ta;
    //int cl = 1;
    
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
        
        content = new JPanel(null);
        content.setSize(new Dimension(500,300));
        content.setBackground(new Color(250,250,250));
        
        /*editable = new JEditorPane();
		editable.setBounds(1,1,499,299);
		editable.setBackground(new Color(214,217,223));
		editable.setForeground(new Color(0,0,0));
		editable.setEnabled(true);
		editable.setFont(new Font("couriernew",0,10));
		editable.setText("EVENTS:");
		//editable.setBorder(BorderFactory.createBevelBorder(1));
		editable.setVisible(true);
		
		JScrollPane jsp = new JScrollPane(editable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.createVerticalScrollBar();
		
		content.add(editable);
		content.add(jsp);*/
		
		ta = new JTextArea("EVENTS:");
		ta.setBackground(new Color(250,250,250));
		ta.setForeground(new Color(0,0,0));
		ta.setFont(new Font("couriernew",0,10));
		ta.setLineWrap(true);
		ta.setAutoscrolls(true);
		ta.setEnabled(true);
		ta.setVisible(true);
		
		//content.add(ta);
		
		jsp = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setPreferredSize(new Dimension(500,300));
		//jsp.setBackground(new Color(250,250,250));
		//jsp.setForeground(new Color(0,0,0));
		//jsp.setFont(new Font("couriernew",0,10));
		//jsp.setAutoscrolls(true);
		//jsp.setEnabled(true);
		//jsp.setVisible(true);
		//content.add(ta);
		//content.add(jsp);
		
        //this.add(ta);
        this.add(jsp);
        //this.add(content);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.pack();
        //this.setVisible(true);
    }

    public synchronized void run()
    {
        this.pack();
        this.setVisible(true);
    }

    //== ABSTRAKTNi METODY =====================================================
    //== PriSTUPOVe METODY VLASTNOSTi INSTANCi =================================
    //== OSTATNi NESOUKROMe METODY INSTANCi ====================================
    
    public synchronized void log(String s)
    {
        ta.append("\n" + s);
        //ta.updateUI();
        //update(ta);
        //editable.setText(editable.getText()+"\n"+s);
        //editable.setBounds(1,1,499,50+cl);
        //cl+=14;
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
