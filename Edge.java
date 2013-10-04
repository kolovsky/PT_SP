/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Pøíliš luouèkı kùò úpìl ïábelské ódy. */

/*******************************************************************************
 * Instance tøídy {@code Edge} pøedstavují hrany grafu.
 *
 * @author    jmacura
 * @version   1.01.000
 */
public class Edge
{
    /**
     * Poøadí vrcholu, na nìj hrana ukazuje, v poli vrcholù
     * {@code nodes[]} tøídy {@code Graph}.
     */
    public int node;
    /**
     * Další hrana v seznamu.
     */
    public Edge nxt;
    
    /**
     * Konstruktor objektù tøídy Edge
     * 
     * @param v Index vrcholu, na kterı hrana vede, v poli
     * vrcholù tøídy {@code Graph}
     */
    public Edge(int v)
    {
        node = v;
        nxt = null;
    }
}