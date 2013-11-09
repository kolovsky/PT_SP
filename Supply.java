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
    //==  ====================
    //== KONSTANTNi ATRIBUTY INSTANCi ==========================================
    //== PROMeNNe ATRIBUTY INSTANCi ============================================
    int quant; //mnozstvi surovin
    int expire; //trvanlivost do [min]
    
    //== PriSTUPOVe METODY VLASTNOSTi TriDY ====================================
    //== OSTATNi NESOUKROMe METODY TriDY =======================================
    
    //##########################################################################
    //== KONSTRUKTORY A TOVaRNi METODY =========================================

    /***************************************************************************
     *
     */
    public Supply(int amount,int expiteTo)
    {
        this.expire = expiteTo;
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
