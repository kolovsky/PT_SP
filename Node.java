
public class Node
{
    /**
     * Jmeno vrcholu.
     */
    private int id;
    public int x;
    public int y;
    /**
     * Seznam hran.
     */
    public Edge edges;

    /**
     * Konstruktor objektu tridy {@code Node}.
     * 
     * @param id Jmeno noveho vrcholu
     */
    public Node(int id, int x, int y)
    {
        setId(id);
        edges = null;
        this.x = x;
        this.y = y;
    }
    
    /**
     * Nastavuje parametr id.
     */
    public void setId(int newId)
    {
        this.id = newId;
    }
    
    /**
     * Vraci parametr id.
     * 
     * @return {@code int} ID vrcholu
     */
    public int getId()
    {
        return this.id;
    }

}
