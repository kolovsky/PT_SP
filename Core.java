import java.util.*;
class Core{
	static Graph g = new Graph();
	public static void main(String[] args) throws Exception {
		g.generate();
		g.createEdge();

		g.save("graph.txt");
		//System.out.print(Arrays.toString(g.nodes));
	}
	
}