import java.util.*;
import java.io.*;
/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kodovani: Prilis zlutoucky kun upel dabelske ody. */

/*******************************************************************************
 * Instance tridy {@code Calendar} predstavuji prioritni frontu.
 *
 * @author    kolovsky
 * @author    jmacura
 * @version   1.00.000
 */
public class Calendar extends Thread
{
    /**
     * Cas simulace v minutach.
     */
    public static int time;
    public static PriorityQueue<Process> q = new PriorityQueue<Process>(10000, new ProcCompare());
    public static Graph g;

    private volatile boolean isRun = true;
    
    
    /***************************************************************************
     *
     */
    public Calendar(Graph g)
    {
        time = 0;
        this.g = g;
        start();
    }

    
    @Override
    public synchronized void run()
    {
        //System.out.println("START!");
        Core.log("START!");
        try{
            addAllNodeToQ();
        }
        catch(NullPointerException e){
            Core.log("NENI GRAF!");
            Core.exceptions.add(e);
        }
        simulate();
        try
        {
            createStatistics();
        }
        catch (Exception e)
        {
            Core.exceptions.add(e);
        }
        Core.summary();
    }
    
    public void pauseNplay()
    {
        if (isRun) {
            Core.log("PAUSE!");
            isRun = false;
        }
        else {
            Core.log("PLAY");
            isRun = true;
        }
    }
    
    /**
    * 
    */
    public void simulate()
    {  
        Process proc;
        while (q.size() != 0) {
           if (q.peek().time == time) {
               proc = q.poll();
               proc.go();
               //System.out.println("peak time "+q.peek().time);
               //System.out.println(proc.time);
               //System.out.println(((Airport)proc.node.suppliedFrom.proces).actualFood);
           }
           else {
               if (q.peek().time < time) {
                   Process pe = q.peek();
                   //Core.log("peak time "+pe.time + " "+pe.people);
                   if (pe instanceof Settle) {
                       Core.log("mesto"+pe.node.people);
                   }
                   else if (pe instanceof Car) {
                       Car pee = (Car) pe;
                       Core.log("car"+pee.nextWork+" "+pee.path[pee.path.length-1].id+" kolik: "+pee.kolik +" "+ pee.cast);
                   }
                   else {
                       Core.log("ost"+pe.nextWork);
                   }
               }
               time++;
               Core.log("======"+time+"======");
           }
           if (time == 200) {
               break; 
           }
           while (!isRun) {
                try{
                    sleep(1);
                }
                catch(Exception e){
                    Core.exceptions.add(e);
                }
            }
        }
    }
         
    public void addAllNodeToQ(){
        //Random r = new Random();
        Node node = g.firstNode;
        int pp = 0;
        for (int i = 0;i<g.arrayAirport.length ;i++ ) {
            g.arrayAirport[i].proces = new Airport(60);
            g.arrayAirport[i].proces.node = g.arrayAirport[i];
            q.add(g.arrayAirport[i].proces);
        }
        while (node != null) {
            if (node instanceof SettleNode) {
                node.proces = new Settle(0);
                node.proces.node = node;
                q.add(node.proces);
                pp++;
            }
            if (node instanceof AirportNode) {
                //node.proces = new Airport(r.nextInt(1000));
            }
            node = node.next;
            if (pp == 3000) {
                break;
            }
        }
    }
    
    public void createStatistics() throws Exception{
        FileWriter out1 = new FileWriter("zasobovano_z.txt");
        FileWriter out2 = new FileWriter("zasobovano_kdy_kolik.txt");
        out1.write("ID zasobovano z letiste\n");
        Node node = g.firstNode;
        while (node != null) {
            if (node instanceof SettleNode) {
                out1.write(node.id+"");
                out1.write(" "+node.suppliedFrom.id+"\n");

                out2.write("==== ID "+node.id+" ====\n");
                out2.write(node.log.toString()+"\n"); 
            }
            node = node.next;
        }
        out2.close();
        out1.close();
        Core.log("Vytvorena statistika");
        //throw new RuntimeException("mama mrda maso");
    }
}


