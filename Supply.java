/**
 * Instance tridy {@code Supply} predstavuji zasoby.
 *
 * @author    kolovsky
 * @author    jmacura
 * @version   1.00.000
 */
public class Supply
{
    //== PROMeNNe ATRIBUTY INSTANCi ============================================
    /**
     * Mnozstvi surovin.
     */
    int quant;
    
    /**
     * Trvanlivost do.. [min]
     */
    int expire;
    
    //== KONSTRUKTORY A TOVaRNi METODY =========================================
    /**
     * Vytvori dany pocet zasob.
     * @param amount Pocet potravin.
     * @param expireTo Doba trvanlivosti.
     */
    public Supply(int amount,int expiteTo)
    {
        this.expire = expiteTo;
        this.quant = amount;
    }
}
