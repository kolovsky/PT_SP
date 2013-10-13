/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kodovani: Prilis zlutoucky kun upel dabelske ody. */

/*******************************************************************************
 * Instance tridy {@code Calendar} predstavuji prioritni frontu (?)
 *
 * @author    jmeno autora
 * @version   0.00.000
 */
public class Calendar
{
    //== KONSTANTNi ATRIBUTY TriDY =============================================
    //== PROMeNNe ATRIBUTY TriDY ===============================================    
    //== STATICKy INICIALIZAcNi BLOK - STATICKy KONSTRUKTOR ====================
    //== KONSTANTNi ATRIBUTY INSTANCi ==========================================
    //== PROMeNNe ATRIBUTY INSTANCi ============================================
    public long time; //cas simulace v minutach;
    
    //== PriSTUPOVe METODY VLASTNOSTi TriDY ====================================
    //== OSTATNi NESOUKROMe METODY TriDY =======================================
    
    //##########################################################################
    //== KONSTRUKTORY A TOVaRNi METODY =========================================

    /***************************************************************************
     *
     */
    public Calendar()
    {
        time = 0;
    }



    //== ABSTRAKTNi METODY =====================================================
    //== PriSTUPOVe METODY VLASTNOSTi INSTANCi =================================
    //== OSTATNi NESOUKROMe METODY INSTANCi ====================================
    public void start()
    {
        System.out.println("START!");
        test(); //pro testovani
        
        //TODO
    }
    
    public void stop()
    {
        //TODO
    }
    //== SOUKROMe A POMOCNe METODY TriDY =======================================
    //== SOUKROMe A POMOCNe METODY INSTANCi ====================================
    //== INTERNi DATOVe TYPY ===================================================
    //== TESTOVACi METODY A TriDY ==============================================
    //
         /********************************************************************
          * Testovaci metoda.
          */
         public static void test()
         {
         }
}
