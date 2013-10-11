import java.util.*;
class Core{
	static Graph g = new Graph();
	public static void main(String[] args) {
		g.generate();
		System.out.print(Arrays.toString(g.nodes));
	}
	
}