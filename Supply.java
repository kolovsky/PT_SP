/**
 * Instance tridy {@code Supply} predstavuji zasoby.
 *
 * @author    kolovsky
 * @author    jmacura
 * @version   1.00.000
 */
public class Supply
{
    
    int quant; //mnozstvi surovin
    int expire; //trvanlivost do [min]
    
    
    /**
     * konstruktor
     * @param mnozstvi jidla
     * @param spotrebovat do [min]
     */
    public Supply(int amount,int expiteTo)
    {
        this.expire = expiteTo;
        this.quant = amount;
    }

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
