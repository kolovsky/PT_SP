/**
 * Abstraktni trida {@code Process} shrnuje obecne vlastnosti a metody procesu,
 * ktere probihaji v kalendari ({@code Calendar}).
 * 
 * @author  kolovsky
 * @author  jmacura
 * @version 1.00.000
 */
abstract class Process{
    //== PROMeNNe ATRIBUTY INSTANCi ============================================
	public int time;
	public Node node;
	public int nextWork;
	//int actualFood;
	
	//== KONSTRUKTORY A TOVaRNi METODY =========================================
	/**
     * konstruktor
     * @param na kdy ma byt proces nacasovan (jeho prvni akce)
     */
	public Process(int time){
		this.time = time;
	}
	
	//== ABSTRAKTNi METODY =====================================================
	/**
     * metoda volana po vyberu z fronty v kalendari
     */
	public abstract void goOn();
	/**
     * metody pro vypis statistiky pro dany objekt
     * @param vypsat legendu?
     */
	public abstract String toString(boolean legend);
}