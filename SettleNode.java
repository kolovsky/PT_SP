/**
 * @author  kolovsky
 * @author  jmacura
 * @version 1.00.000
 */
class SettleNode extends Node
{
	public int people;
	/**
	 * Vytvori nove mesto.
	 * @param id ID mesta
	 * @param x X-ova souradnice
	 * @param y Y-ova souradnice
	 */
    public SettleNode(int id, int x, int y)
    {
        super(id,x,y);
        //edges = new Edge[11];
        costToAirAll = new int[5];
    }
}