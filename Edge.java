public class Edge
{
    public int cost;
    public Node node;
    public boolean isRoad = true;
    public Edge next = null;
    /**
     * konstuktor
     * @param kam hrana vede
     * @param jak je dlouha
     */
    public Edge(Node node,int cost)
    {
        this.cost = cost;
        this.node = node;
    }
}