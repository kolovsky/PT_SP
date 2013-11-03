import java.util.*;
import java.io.*;

class Core{
    static Graph g;
    static Calendar c;
    static LogWindow lw;
    
    public static void generateNew()
    {
        g = new Graph();
        g.generate();
        //System.out.println("1");
        g.generatePeople();
        //System.out.println("2");
        g.createEdge();
        //System.out.println("3");
        g.createSupplied();
        //System.out.print(g.statistic());
        lw.log(g.statistic());
    }
    
    public static void save() throws Exception
    {
        //g.save("graph.txt");
        System.out.println(Arrays.toString(g.dijkstra(g.firstNode,g.lastNode)));
    }
    
    public static void start() throws Exception
    {
        //lw = new LogWindow();
        c = new Calendar(g);
        c.start();
        c.createStatistics();

    }
    
    public static void stop()
    {
        c.end();
    }
    
    public static void log(String s)
    {
        lw.log(s);
        System.out.println(s);
    }
    
    public static void main(String[] args) throws Exception {
        
        //generateNew();
        
        //save();
        //System.out.print(Arrays.toString(g.nodes));
        
        g = new Graph();
        g.load("graph.txt");
        
        //System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run() {
                new GUI_menu();
            }
        }
        );
        
        Thread t2 = new Thread(new Runnable()
        {
            public void run() {
                lw = new LogWindow();
            }
        }, "GUI");
        t2.start();
        t2.setPriority(Thread.NORM_PRIORITY);
        
    }
    
}