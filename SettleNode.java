/**
 * @author  kolovsky
 * @author  jmacura
 * @version 1.00.000
 */
class SettleNode extends Node
{
	public int people;
	/**
     * konstruktor
     * @param ID
     * @param souradnice x
     * @param souradnice y
     */
    public SettleNode(int id, int x, int y)
    {
        super(id,x,y);
        //edges = new Edge[11];
        costToAirAll = new int[5];
    }
}