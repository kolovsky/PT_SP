
public class Edge
{
    /**
     * Poradi vrcholu, na nejz hrana ukazuje, v poli vrcholu
     * {@code nodes[]} tridy {@code Graph}.
     */
    public int node;
    /**
     * Dalsi hrana v seznamu.
     */
    public Edge nxt;
    
    /**
     * Konstruktor objektu tridy Edge
     * 
     * @param v Index vrcholu, na ktery hrana vede, v poli
     * vrcholu tridy {@code Graph}
     */
    public Edge(int v)
    {
        node = v;
        nxt = null;
    }
}