import java.util.*;
import java.io.*;

class Core{
    static Graph g;
    static Calendar c;
    
    public static void generateNew()
    {
        g = new Graph();
        g.generate();
        System.out.println("1");
        g.generatePeople();
        System.out.println("2");
        g.createEdge();
        System.out.println("3");
    }
    
    public static void save() throws Exception
    {
        //g.save("graph.txt");
        System.out.println(Arrays.toString(g.dijkstra(g.firstNode,g.lastNode)));
    }
    
    public static void start()
    {
        c = new Calendar();
        c.start();

    }
    
    public static void stop()
    {
        c.stop();
    }
    
    public static void main(String[] args) throws Exception {
        
        //generateNew();
        
        //save();
        //System.out.print(Arrays.toString(g.nodes));
        
        g = new Graph();
        g.load("graph.txt");
        
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run() {
                new GUI_menu();
            }
        }
        );
        
    }
    
}