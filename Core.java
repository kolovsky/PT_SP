import java.util.*;
class Core{
	static Graph g = new Graph();
	public static void main(String[] args) throws Exception {
		g.generate();
		System.out.println("1");
		g.generatePeople();
		System.out.println("2");
		g.createEdge();
		System.out.println("3");

		
		
		g.save("graph.txt");
		//System.out.print(Arrays.toString(g.nodes));
	}
	
}