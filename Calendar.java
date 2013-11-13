import java.util.*;
import java.io.*;
/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kodovani: Prilis zlutoucky kun upel dabelske ody. */

/*******************************************************************************
 * Instance tridy {@code Calendar} predstavuji prioritni frontu (?)
 *
 * @author    jmeno autora
 * @version   0.00.000
 */
public class Calendar extends Thread
{
    
    public static int time; //cas simulace v minutach;
    public static PriorityQueue<Process> q = new PriorityQueue<Process>(3000, new ProcCompare());
    public static Graph g;
    
    
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
        addAllNodeToQ();
        test(); //pro testovani
        try{
            createStatistics();
        }
        catch (Exception e){
        }
    }
    
    public void end()
    {
        Core.log("STOP!");
        super.interrupt();
        notifyAll();
        //TODO
    }
    
    /********************************************************************
    * Testovaci metoda.
    */
    public static void test()
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
                time++;
                Core.log("======"+time+"======");
            }
            if (time == 7200) {
                break; 
            }
            //System.out.println("peak time "+q.peek().time);
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
            if (pp == 30) {
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
                out2.write(node.log); 
            }

            node = node.next;
        }
        out2.close();
        out1.close();
        Core.log("Vytvorena statistika");
    }
}
