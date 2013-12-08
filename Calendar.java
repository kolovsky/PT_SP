import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Instance tridy {@code Calendar} predstavuji prioritni frontu objektu simulace.
 *
 * @author    kolovsky
 * @author    jmacura
 * @version   1.00.000
 */
public class Calendar extends Thread
{
    //== KONSTANTNi ATRIBUTY INSTANCi ==========================================
    private final PriorityQueue<Process> q;
    private final Graph g;
    
    //== PROMeNNe ATRIBUTY INSTANCi ============================================
    /**
     * Cas simulace v minutach.
     */
    public int time;
    
    /**
     * Seznam vsech nakladnich aut.
     */
    public ArrayList<Car> allCar = new ArrayList<Car>();
    
    /**
     * Seznam vsech vrtulniku.
     */
    public ArrayList<Helicop> allHelicop = new ArrayList<Helicop>();
    
    private volatile boolean isRun = true;
    
    /**
     * Vytvori novy kalendar s prioritni frontou a spusti simulaci.
     * 
     * @param g Mapa uzemi, tj. graf, nad kterym probiha simulace.
     */
    public Calendar(Graph g)
    {
        super("Calendar");
        time = 0;
        q = new PriorityQueue<Process>(10000, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.time - o2.time;
            }});
        this.g = g;
        start();
    }
    /**
     * vrati graf
     */
    public Graph getGraph()
    {
        return this.g;
    }
    /**
     * vrati frontu
     */
    public PriorityQueue<Process> getQueue()
    {
        return this.q;
    }
    /**
     * metoda pro spusteni simulace
     */
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
        Core.log("Pripravuji data pro simulaci (nejakou dobu to muse trvat)");
        g.generatePath();
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
    /**
     * metoda pro pozastavovani simulace
     */
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
     * metoda ve ktre probiha simulace 
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
           if (time == 10800) {
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
    /**
     * vytvareni procesu k vrcholum a plneni fronty procesy
     */     
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
    /**
     * vytvori statistiku k simulaci
     */
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

        out3.write("Celkovy pocet nakladnich aut je "+allCar.size()+"\n");
        out3.write("Celkovy pocet vrtulniku je "+allHelicop.size()+"\n");

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


