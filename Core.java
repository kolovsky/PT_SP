import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Core{
	static Graph g = new Graph();
	static Calendar c = new Calendar();
	
	public static void generate(ActionEvent evt)
    {
        g.generate();
    }
    
    public static void start(ActionEvent evt)
    {
        c.start();
    }
    
    public static void stop(ActionEvent evt)
    {
        c.stop();
    }
    
	public static void main(String[] args) {
		g.generate();
		System.out.print(Arrays.toString(g.nodes));
	}
	
}