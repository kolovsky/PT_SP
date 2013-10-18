/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola k�dov�n�: P��li� �lu�ou�k� k�� �p�l ��belsk� �dy. */

import java.awt.*;
import javax.swing.*;

/*******************************************************************************
 * Instance t��dy {@code LogWindow} p�edstavuj� ...
 *
 * @author    jm�no autora
 * @version   0.00.000
 */
public class LogWindow extends JFrame
{
    //== KONSTANTN� ATRIBUTY T��DY =============================================
    //== PROM�NN� ATRIBUTY T��DY ===============================================
    //== STATICK� INICIALIZA�N� BLOK - STATICK� KONSTRUKTOR ====================
    //== KONSTANTN� ATRIBUTY INSTANC� ==========================================
    //== PROM�NN� ATRIBUTY INSTANC� ============================================
    JPanel content;
    JEditorPane editable;
    int cl = 1;
    
    //== P��STUPOV� METODY VLASTNOST� T��DY ====================================
    //== OSTATN� NESOUKROM� METODY T��DY =======================================
    
    //##########################################################################
    //== KONSTRUKTORY A TOV�RN� METODY =========================================

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


    //== ABSTRAKTN� METODY =====================================================
    //== P��STUPOV� METODY VLASTNOST� INSTANC� =================================
    //== OSTATN� NESOUKROM� METODY INSTANC� ====================================
    
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
    
    //== SOUKROM� A POMOCN� METODY T��DY =======================================
    //== SOUKROM� A POMOCN� METODY INSTANC� ====================================
    //== INTERN� DATOV� TYPY ===================================================
    //== TESTOVAC� METODY A T��DY ==============================================
    //
    //     /********************************************************************
    //      * Testovac� metoda.
    //      */
    //     public static void test()
    //     {
    //         LogWindow instance = new LogWindow();
    //     }
    //     /** @param args Parametry p��kazov�ho ��dku - nepou��van�. */
    //     public static void main(String[] args)  {  test();  }
}
