
public class Edge
{
    /**
     * Poradi vrcholu, na nijž hrana ukazuje, v poli vrcholu
     * {@code nodes[]} tridy {@code Graph}.
     */
    public int node;
    /**
     * Dalsí hrana v seznamu.
     */
    public Edge nxt;
    
    /**
     * Konstruktor objektù třídy Edge
     * 
     * @param v Index vrcholu, na který hrana vede, v poli
     * vrcholu trřdy {@code Graph}
     */
    public Edge(int v)
    {
        node = v;
        nxt = null;
    }
}