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
	public Process(int time){
		this.time = time;
	}
	
	//== ABSTRAKTNi METODY =====================================================
	public abstract void goOn();
	
	public abstract String toString(boolean legend);
}