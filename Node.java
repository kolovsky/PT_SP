/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Pøíliš luouèkı kùò úpìl ïábelské ódy. */

/*******************************************************************************
 * Instance tøídy {@code Node} pøedstavují vrcholy grafu.
 *
 * @author    jmacura
 * @version   2.01.000
 */
public class Node
{
    /**
     * Jméno vrcholu.
     */
    private String id;
    /**
     * Seznam hran.
     */
    public Edge edges;

    /**
     * Konstruktor objektù tøídy {@code Node}.
     * 
     * @param id Jméno nového vrcholu
     */
    public Node(String id)
    {
        setId(id);
        edges = null;
    }
    
    /**
     * Nastavuje parametr id.
     */
    public void setId(String newId)
    {
        this.id = newId;
    }
    
    /**
     * Vrací parametr id.
     * 
     * @return {@code String} ID vrcholu
     */
    public String getId()
    {
        return this.id;
    }

}
