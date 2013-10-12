
public class Node
{
    /**
     * Jmeo vrcholu.
     */
    //vlastnosti
    public int id;
    public int x;
    public int y;
    public int people;
    public boolean isSimple;
    public boolean isHeliport = false;

    //vnitrni propojeni a promene pro algoritmy
    public Node next = null;
    public boolean isCloud = false;
    public int cost; //[minutes:time]
    public Node prev;

    /**
     * Seznam hran.
     */
    public Edge edges[];

    /**
     * Konstruktor objekt? t??y {@code Node}.
     * 
     * @param id Jm?o nov?o vrcholu
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
     * Vrac?parametr id.
     * 
     * @return {@code String} ID vrcholu
     */
    public int getId()
    {
        return this.id;
    }

}
