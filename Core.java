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
    static List<Exception> exceptions;
    
    /**
     * Staticky inicializacni blok.
     */
    static
    {
        exceptions = new Vector<Exception>(2, 1);
    }
    
    public static void abort()
    {
        stop();
        c.interrupt();
        g = null;
        //c = null;
        c = null;
        log("SIMULACE UKONCENA NASILIM. Vsechna data zahozena.");
    }
    
    public static synchronized void addSettle(int x, int y, int pop)
    {
        g.addSettle(x, y, pop);
        log("Pridano sidlo s " + pop + " obyvateli na pozici [" + x +", " + y + "].");
    }
    
    /**
     * @param type Typ procesu.
     * @param id ID procesu.
     */
    public static synchronized void check(String type, int id)
    {
        Process objekt;
        switch(type)
        {
            case "helicop": objekt = c.allHelicop.get(id); break;
            case "car": objekt = c.allCar.get(id); break;
            case "settle": objekt = g.get(id).proces; break;
            default: Core.log("Nebyl vybran objekt!"); return;
        }
        Core.log(objekt.toString(true));
    }
    
    public static void generateNew()
    {
        //g = new Graph();
        try
        {
            g.generate();
            //System.out.println("1");
            g.generatePeople();
            //System.out.println("2");
            g.createEdge();
            //System.out.println("3");
            g.createSupplied();
            //System.out.print(g.statistic());
            log(g.statistic());
        }
        catch(Exception e)
        {
            exceptions.add(e);
            log("Nelze vygenerovat nova data");
        }
    }
    
    public static synchronized int getProcessNumber(String process)
    {
        int num = 0;
        switch(process)
        {
            case "settle": num = Settle.lastID + 1; break;
            case "helicop": num = Helicop.lastID; break;
            case "car": num = Car.lastID; break;
            default: break;
        }
        return num;
    }
    
    public static synchronized void load()
    {
        try
        {
            g.load("graph.txt");
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }
        log(g.statistic());
    }
    
    public static synchronized void log(String s)
    {
        if (lw == null)
        {
            Thread t = new Thread(lw = new LogWindow(), "LogWindow");
            t.start();
        }
        else
        {
            lw.log(s);
        }
        //System.out.println(s);
    }
    
    public static void save()
    {
        //g.save("graph.txt");
        //System.out.println(Arrays.toString(g.dijkstra(g.firstNode,g.lastNode)));
        try
        {
            g.save("graph.txt");
            //c.createStatistics();
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
        /*try
        {
            c.createStatistics();
            //c.createStatistics();
        }
        catch (IOException e)
        {
            exceptions.add(e);
        }*/
        

    }
    
    public static void summary()
    {
        Exception[] allExs = new Exception[0];
        allExs = exceptions.toArray(allExs);
        int exNum = (int) allExs.length;
        if (exNum > 0)
        {
            log("Doslo k " + exNum + " chybam:");
            for(int i = 0; i < exNum; i++)
            {
                //Exception e = (Exception) exceptions.get(i);
                log(allExs[i].getMessage());
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
        
        //SwingUtilities.invokeLater(lw = new LogWindow());
        Thread t = new Thread(lw = new LogWindow(), "LogWindow");
        t.start();
        
    }
    
}