
public class Edge
{
    public int cost;
    public Node node;
    public boolean isRoad = true;

    public Edge(Node node,int cost)
    {
        this.cost = cost;
        this.node = node;
    }
}