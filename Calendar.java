import java.util.*;
/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kodovani: Prilis zlutoucky kun upel dabelske ody. */

/*******************************************************************************
 * Instance tridy {@code Calendar} predstavuji prioritni frontu (?)
 *
 * @author    jmeno autora
 * @version   0.00.000
 */
public class Calendar
{
    
    public static int time; //cas simulace v minutach;
    public static PriorityQueue<Process> q = new PriorityQueue<Process>(3000, new MyComparator());
    public static Graph g;
    
    

    /***************************************************************************
     *
     */
    public Calendar(Graph g)
    {
        time = 0;
        this.g = g;
    }



    
    public void start()
    {
        //System.out.println("START!");
        Core.log("START!");
        addAllNodeToQ();
        test(); //pro testovani
        
        //TODO
    }
    
    public void stop()
    {
        Core.log("STOP!");
        
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
                    //System.out.println(q.size());
                }
                else {
                    time++;
                    System.out.println(time);
                }
                if (time == 1000) {
                   break; 
                }
                //System.out.println(q.peek().time);
            }
         }
    public void addAllNodeToQ(){
        Random r = new Random();
        Node node = g.firstNode;
        int pp = 0;
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
            if (pp == 100) {
                break;
            }
            
        }
    }
}
