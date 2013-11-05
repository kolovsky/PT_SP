/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kodovani: Prilis zlutoucký kun upel dabelske ody. */




/*******************************************************************************
 * Instance tridy {@code Supply} predstavuji zasoby
 *
 * @author    jmeno autora
 * @version   0.00.000
 */
public class Supply
{
    //== KONSTANTNi ATRIBUTY TriDY =============================================
    //== PROMeNNe ATRIBUTY TriDY ===============================================
    //== STATICKÝ INICIALIZAcNi BLOK - STATICKÝ KONSTRUKTOR ====================
    //== KONSTANTNi ATRIBUTY INSTANCi ==========================================
    //== PROMeNNe ATRIBUTY INSTANCi ============================================
    int quant; //mnozstvi surovin
    int expire; //trvanlivost
    
    //== PriSTUPOVe METODY VLASTNOSTi TriDY ====================================
    //== OSTATNi NESOUKROMe METODY TriDY =======================================
    
    //##########################################################################
    //== KONSTRUKTORY A TOVaRNi METODY =========================================

    /***************************************************************************
     *
     */
    public Supply(int amount)
    {
        this.expire = 4320;
        this.quant = amount;
    }



    //== ABSTRAKTNi METODY =====================================================
    //== PriSTUPOVe METODY VLASTNOSTi INSTANCi =================================
    //== OSTATNi NESOUKROMe METODY INSTANCi ====================================
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
    //         Supply instance = new Supply();
    //     }
    //     /** @param args Parametry prikazoveho radku - nepouzivane. */
    //     public static void main(String[] args)  {  test();  }
}
