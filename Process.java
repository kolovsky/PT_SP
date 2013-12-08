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
	 * Vytvori novy proces.
	 * @param time Simulacni cas dalsi akce.
	 */
	public Process(int time){
		this.time = time;
	}
	
	//== ABSTRAKTNi METODY =====================================================
	/**
	 * Provede s procesem akci.
	 */
	public abstract void goOn();
	
	/**
	 * Vypise udaje o procesu.
	 * @param legend Chcete legendu informaci?
	 * @return Statisticka data.
	 */
	public abstract String toString(boolean legend);
}