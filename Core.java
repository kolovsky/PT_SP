import java.io.IOException;
import java.util.List;
import java.util.Vector;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

/**
 * Trida {@code Core} je hlavni a spousteci tridou projektu. Nevytvari instance.
 * Projekt resi problem diskretni simulace potravinove pomoci na uzemi postizenem prirodni katastrofou.
 * 
 * @author  kolovsky
 * @author  jmacura
 * @version 1.00.000
 */
class Core
{
    /**
     * Graf reprezentujici mapu uzemi.
     */
    public static Graph g;
    
    /**
     * Kalendar diskretni simulace.
     */
    public static Calendar c;
    
    /**
     * Okno pro zapis prubehu simulace.
     */
    public static LogWindow lw;
    
    /**
     * Graficke menu pro snadne ovladani aplikace.
     */
    public static GUI_menu menu;
    
    /**
     * Chyby zaznamenane pri behu programu.
     */
    public static List<Exception> exceptions;
    
    //== STATICKY INICIALIZACNI BLOK - STATICKY KONSTRUKTOR ====================
    static
    {
        exceptions = new Vector<Exception>(2, 1);
    }
    
    //== OSTATNi NESOUKROMe METODY TriDY =======================================
    /*public static void abort()
    {
        //System.out.println(Thread.activeCount() + Thread.currentThread().getName());
        //c.setDaemon(true);
        //c.interrupt();
        //System.out.println(Thread.activeCount() + Thread.currentThread().getName());
        //g = null;
        //c = null;
        //log("SIMULACE UKONCENA NASILIM. Vsechna data zahozena.");
    }*/
    
    /**
     * Zavola odpovidajici metodu tridy {@code Graph}, ktera vytvori nove sidlo na danem uzemi.
     * 
     * @param x X-ova pozice sidla.
     * @param y Y-ova pozice sidla.
     * @param pop Populace sidla.
     */
    public static synchronized void addSettle(int x, int y, int pop)
    {
        g.addSettle(x, y, pop);
        log("Pridano sidlo s " + pop + " obyvateli na pozici [" + x +", " + y + "].");
    }
    
    /**
     * Vypise do zaznamoveho okna stav vybraneho objektu.
     * 
     * @param type Typ procesu. Jedna z moznosti {@code car}, {@code helicop} nebo {@code settle}.
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
    
    /**
     * Vygeneruje novou mapu uzemi. Postupne vola odpovidajici metody tridy {@code Graph} pro generovani
     * a) novych sidel a letist;
     * b) obyvatel do danych sidel;
     * c) silnic mezi sidly;
     * d) prirazeni sidel k letistim.
     */
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
    
    /**
     * Zjisti nejvyssi ID daneho typu procesu a vrati jej.
     * 
     * @param process Typ procesu. Jedna z hodnot {@code car}, {@code helicop} nebo {@code settle}.
     * @erturn Nejvyssi ID daneho typu procesu.
     */
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
    
    /**
     * Nacte mapu uzemi ze souboru.
     * Nejprve zavola odpovidajici metodu ve tride {@code Graph}
     * a pote vypise statistiku nacteneho uzemi do zaznamoveho okna.
     */
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
    
    /**
     * Vypise zaznam o udalosti na obrazovku.
     * Zavola odpovidajici metodu ve tride {@code LogWindow}, ktera vypise zadany retezec do okna.
     * 
     * @param s Retezec, ktery ma byt zapsan do zaznamoveho okna.
     */
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
    
    /**
     * Ulozi aktualne vygenerovana data uzemi do souboru. Vola odpovidajici metodu tridy {@code Graph}. Soubor bude mit nazev "graph.txt".
     */
    public static void save()
    {
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
    
    /**
     * Spusti simulaci potravinove pomoci. Vytvori novy kalendar, ktery diskretni simulaci realizuje.
     */
    public static synchronized void start()
    {
        c = new Calendar(g);
    }
    
    /**
     * Pozastavi, respektive znovu spusti simulaci. Vola odpovidajici metodu tridy {@code Calendar}.
     */
    public static void stop()
    {
        c.pauseNplay();
    }
    
    /**
     * Na konci simulace vypise do zaznamoveho okna informace o prubehu programu.
     * Pokud behem behu programu doslo k nezavaznym chybam, pak je vypise.
     * V opacnem pripade vypise "Vse OK".
     */
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

    //== TESTOVACi METODY A TriDY ==============================================
    /***************************************************************************
     * Metoda, prostrednictvim niz se spousti cela aplikace.
     *
     * @param args Parametry prikazoveho radku. Nevyuzite.
     */
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