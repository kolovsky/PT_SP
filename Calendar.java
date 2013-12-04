import java.util.*;
import java.io.*;
/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kodovani: Prilis zlutoucky kun upel dabelske ody. */

/*******************************************************************************
 * Instance tridy {@code Calendar} predstavuji prioritni frontu objektu simulace.
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
    public int time;
    private final PriorityQueue<Process> q;
    private final Graph g;
    public ArrayList<Car> allCar;
    public ArrayList<Helicop> allHelicop;

    private volatile boolean isRun = true;
    
    /***************************************************************************
     *
     */
    public Calendar(Graph g)
    {
        super("Calendar");
        time = 0;
        allCar = new ArrayList<Car>();
        q = new PriorityQueue<Process>(10000, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.time - o2.time;
            }});
        this.g = g;
        allHelicop = new ArrayList<Helicop>();
        start();
    }
    
    public Graph getGraph()
    {
        return this.g;
    }
    
    public PriorityQueue<Process> getQueue()
    {
        return this.q;
    }
    
    @Override
    public synchronized void run()
    {
        //System.out.println("START!");
        Core.log("START!");
        if(g != null)
        {
            addAllNodeToQ();
        }
        else
        {
            Core.log("NENI GRAF!");
        }
        /*try{
            addAllNodeToQ();
        }
        catch(NullPointerException e){
            Core.log("NENI GRAF!");
            Core.exceptions.add(e);
            //return;
        }*/
        simulate();
        try
        {
            createStatistics();
        }
        catch (IOException e)

        {
            System.out.println("chyba");
            Core.exceptions.add(e);
            System.out.println(e.toString());
        }
        Core.summary();
    }
    
    public void pauseNplay()
    {
        if (isRun) {
            Core.log("POZASTAVENO!");
            isRun = false;
        }
        else {
            Core.log("SPUSTENO");
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
               //System.out.println(((Settle)proc).id);
               proc.goOn();
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
               //Core.log(Thread.currentThread().getName());
           }
           if (time == 3000) {
               break; 
           }
           while (!isRun) {
                try{
                    sleep(1);
                }
                catch(Exception e){
                    Core.exceptions.add(e);
                    //q.clear();
                    //System.out.println("SHUTDOWN");
                }
            }
           //if(isKillingTime)
           //{
           //    interrupt();
           //}
        }
    }
         
    public void addAllNodeToQ(){
        //Random r = new Random();
        Node node = g.firstNode;
        //int pp = 0;
        for (int i = 0;i<g.arrayAirport.length ;i++ ) {
            //g.arrayAirport[i].proces = new Airport(60);
            //g.arrayAirport[i].proces.node = g.arrayAirport[i];
            new Airport(60,g.arrayAirport[i]);
            q.add(g.arrayAirport[i].proces);
        }
        while (node != null) {
            if (node instanceof SettleNode) {
                //node.proces = new Settle(0);
                //node.proces.node = node;
                new Settle(0,node);
                q.add(node.proces);

                if (((Settle)node.proces).id == 3000) {
                    ((Settle)node.proces).lastID += 5;
                }
                //pp++;
            }
            //if (node instanceof AirportNode) {
                //node.proces = new Airport(r.nextInt(1000));
            //}
            node = node.next;
            /*if (pp == 3001) {
                break;
            }*/
        }
    }
    
    public void createStatistics() throws IOException{
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
        //System.out.println(0);
        
        FileWriter out3 = new FileWriter("movingObject.txt");

        out3.write("Celkovi pocet nakladnich aut je "+allCar.size()+"\n");
        out3.write("Celkovi pocet vrtulniku je "+allHelicop.size()+"\n");

        out3.write("=====CAR=====\n");
        out3.write("Start End Quant Settle\n");
        //System.out.println(1);
        for (int i = 0;i<allCar.size() ;i++ ) {
            out3.write(allCar.get(i).toString(false));
            
        }
        //System.out.println(2);
        out3.write("=====HELICOP=====\n");
        for (int i = 0;i<allHelicop.size() ;i++ ) {
            out3.write(allHelicop.get(i).toString(false));

        }
        out3.close();
        Core.log("Vytvorena statistika");
        
    }
}


