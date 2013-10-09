
class SettleNode extends Node
{
	public int people;
    public SettleNode(int id, int x, int y)
    {
        super(id,x,y);
        edges = new Edge[10];
    }
}