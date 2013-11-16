import java.util.*;

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
    public Process proces;

    //vnitrni propojeni a promene pro algoritmy
    public Node next = null;
    // for dijkstra
    public boolean isCloud = false;
    public int cost = Integer.MAX_VALUE; //[minutes:time]
    public Node prev = null;
    //ceny do jednotlivich letist
    public int [] costToAirAll;

    // nalezi ke grafu
    public AirportNode suppliedFrom = null; //prislusnost k letisti
    public int costToAir; //[min] jak dlouho trva cesta z letiste
    //zaznamy a statistiky 
    public Statistic log = new Statistic();

    /**
     * Seznam hran.
     */
    //public Edge edges[];
    public Edge firstEdge = null;
    public Edge lastEdge = null;

    public void addEdge(Edge newEdge){
        if (firstEdge == null) {
            firstEdge = newEdge;
            lastEdge = newEdge;    
        }
        else {
            lastEdge.next = newEdge;
            lastEdge = newEdge;
        }
    }

    /**
     * Konstruktor objekt? t??y {@code Node}.
     * 
     * @param id Jm?o nov?o vrcholu
     */
    public Node(int id, int x, int y)
    {
        setId(id);
        //edges = null;
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
