/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kÛdov·nÌ: P¯Ìliö ûluùouËk˝ k˘Ú ˙pÏl Ô·belskÈ Ûdy. */

import java.awt.*;
import javax.swing.*;

/*******************************************************************************
 * Instance t¯Ìdy {@code LogWindow} p¯edstavujÌ ...
 *
 * @author    jmÈno autora
 * @version   0.00.000
 */
public class LogWindow extends JFrame
{
    //== KONSTANTNÕ ATRIBUTY TÿÕDY =============================================
    //== PROMÃNN… ATRIBUTY TÿÕDY ===============================================
    //== STATICK› INICIALIZA»NÕ BLOK - STATICK› KONSTRUKTOR ====================
    //== KONSTANTNÕ ATRIBUTY INSTANCÕ ==========================================
    //== PROMÃNN… ATRIBUTY INSTANCÕ ============================================
    JPanel content;
    JEditorPane editable;
    int cl = 1;
    
    //== PÿÕSTUPOV… METODY VLASTNOSTÕ TÿÕDY ====================================
    //== OSTATNÕ NESOUKROM… METODY TÿÕDY =======================================
    
    //##########################################################################
    //== KONSTRUKTORY A TOV¡RNÕ METODY =========================================

    /***************************************************************************
     *
     */
    public LogWindow()
    {
        this.setTitle("PT_SP: Event logging");
        
        content = new JPanel(null);
        content.setPreferredSize(new Dimension(500,300));
        content.setBackground(new Color(250,250,250));
        
        editable = new JEditorPane();
		editable.setBounds(1,1,499,50);
		editable.setBackground(new Color(214,217,223));
		editable.setForeground(new Color(0,0,0));
		editable.setEnabled(true);
		editable.setFont(new Font("couriernew",0,10));
		editable.setText("");
		editable.setBorder(BorderFactory.createBevelBorder(1));
		editable.setVisible(true);
		
		content.add(editable);
		
        this.add(content);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }


    //== ABSTRAKTNÕ METODY =====================================================
    //== PÿÕSTUPOV… METODY VLASTNOSTÕ INSTANCÕ =================================
    //== OSTATNÕ NESOUKROM… METODY INSTANCÕ ====================================
    
    public void log(String s)
    {
        /*JLabel l = new JLabel();
        l.setBounds(1,cl,100,10);
        l.setBackground(new Color(250,250,250));
        l.setForeground(new Color(0,0,0));
        l.setEnabled(true);
        l.setFont(new Font("CourierNew",0,10));
        l.setText(s);
        l.setVisible(true);
        content.add(l);*/
        editable.setText(editable.getText()+"\n"+s);
        editable.setBounds(1,1,499,50+cl);
        cl+=14;
    }
    
    //== SOUKROM… A POMOCN… METODY TÿÕDY =======================================
    //== SOUKROM… A POMOCN… METODY INSTANCÕ ====================================
    //== INTERNÕ DATOV… TYPY ===================================================
    //== TESTOVACÕ METODY A TÿÕDY ==============================================
    //
    //     /********************************************************************
    //      * TestovacÌ metoda.
    //      */
    //     public static void test()
    //     {
    //         LogWindow instance = new LogWindow();
    //     }
    //     /** @param args Parametry p¯ÌkazovÈho ¯·dku - nepouûÌvanÈ. */
    //     public static void main(String[] args)  {  test();  }
}
