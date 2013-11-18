import java.util.*;
import java.io.*;
import javax.swing.*;

/**
 * Jadro - hlavni a spousteci trida aplikace.
 * @author  kolovsky
 * @author  jmacura
 * @version 1.00.000
 */
class Core
{
    /**
     * Graf reprezentujici mapu uzemi.
     */
    static Graph g;
    
    /**
     * Kalendar diskretni simulace.
     */
    static Calendar c;
    
    /**
     * Okno pro zapis prubehu simulace.
     */
    static LogWindow lw;
    
    /**
     * Graficke menu pro snadne ovladani aplikace.
     */
    static GUI_menu menu;
    
    /**
     * Chyby zaznamenane pri behu programu.
     */
    static Vector exceptions;
    
    /**
     * Staticky inicializacni blok.
     */
    static
    {
        exceptions = new Vector<Exception>(2, 1);
    }
    
    public static void generateNew()
    {
        //g = new Graph();
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
    
    public static void load()
    {
        try
        {
            g.load("graph.txt");
        }
        catch (IOException e)
        {
            exceptions.add(e);
        }
        lw.log(g.statistic());
    }
    
    public static synchronized void log(String s)
    {
        lw.log(s);
        //System.out.println(s);
    }
    
    public static void save()
    {
        //g.save("graph.txt");
        //System.out.println(Arrays.toString(g.dijkstra(g.firstNode,g.lastNode)));
        try
        {
            g.save("graph.txt");
        }
        catch (IOException e)
        {
            exceptions.add(e);
        }
    }
    
    public static synchronized void start()
    {
        c = new Calendar(g);

        //c.start();
        /*try
        {
            c.createStatistics();
        }
        catch (Exception e)
        {
        }*/
    }
    
    public static void stop()
    {
        c.pauseNplay();
    }
    
    public static void summary()
    {
        Object[] allExs = exceptions.toArray();
        int exNum = (int) allExs.length;
        if (exNum > 0)
        {
            log("Doslo k " + exNum + " chybam:");
            for(int i = 0; i < exNum; i++)
            {
                Exception e = (Exception) exceptions.get(i);
                log(e.getMessage());
            }
        }
        else
        {
            log("Vse OK");
        }
    }

    public static void main(String[] args) {
        
        //System.out.print(Arrays.toString(g.nodes));
        
        g = new Graph();
        
        //System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception useDefault)
        {
            exceptions.add(useDefault);
        }
        
        SwingUtilities.invokeLater(menu = new GUI_menu());
        
        Thread t = new Thread(lw = new LogWindow(), "LogWindow");
        t.start();
        
    }
    
}