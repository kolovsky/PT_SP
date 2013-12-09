import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author  kolovsky
 * @author  jmacura
 * @version 2.00.000
 */
public class Graph
{
    /**
     * Prvni vrchol ve spojovem seznamu.
     */
    public Node firstNode = null;
    
    /**
     * Posledni vrchol ve spojovem seznamu.
     */
    public Node lastNode = null;
    
    /**
     * Pole s letisti.
     */
    public AirportNode [] arrayAirport = new AirportNode[5];
    
    /**
     * Konstruktor objektu tridy {@code Graph}.
     */
    public Graph()
    {
        //nodes = new Node[0];
    }
    /**
     * pridani vrcholu do grafu
     * @param newNode Novy vrchol.
     */
    public void addNode(Node newNode){
        if (firstNode == null) {
            firstNode = newNode;
            lastNode = newNode;    
        }
        else {
            lastNode.next = newNode;
            lastNode = newNode;
        }
    }
    /**
     * generovani pozic sidel a letist
     */
    public void generate(){
        //nodes = new Node[3006]; //3000 settle and 5 aiport, index 0 is NULL
        Random r = new Random(); //random
        int x,y,xx,yy,i;
            
        i = 1;
        for (x = 2000;x<498000 ;x+=9050 ) {
            for (y = 2000;y<498000 ;y+=9050 ) {
                xx  = r.nextInt(4000) - 2000;
                yy = r.nextInt(4000) - 2000;

                //nodes[i] = new SettleNode(i,x+xx,y+yy);
                //System.out.print(i);
                addNode(new SettleNode(i,x+xx,y+yy));
                i++;
                if (i == 3001) {
                    //System.out.print(x);
                    //System.out.print(y);
                    x = 1000000;
                    y = 1000000;
                    break;
                }
            }
                
            
        }
        AirportNode a = new AirportNode(3001,40000,40000);
        addNode(a);
        arrayAirport[0] = a;
        AirportNode b = new AirportNode(3002,40000,460000);
        addNode(b);
        arrayAirport[1] = b;
        AirportNode c = new AirportNode(3003,460000,460000);
        addNode(c);
        arrayAirport[2] = c;
        AirportNode d = new AirportNode(3004,460000,40000);
        addNode(d);
        arrayAirport[3] = d;
        AirportNode e = new AirportNode(3005,40000,250000);
        addNode(e);
        arrayAirport[4] = e;


    }
    /**
     * nacitani grafu ze souboru
     * @param filenam Jmeno souboru.
     * @throws IOException Chyba cteni ze souboru.
     */
    public void load(String filename) throws IOException{
        File file = new File(filename);
        Scanner s = new Scanner(file);
        int air = 0;
        while (s.hasNextLine()) {
            String pole [] = s.nextLine().split(" ");

            int id = Integer.parseInt(pole[0]);
            int x = Integer.parseInt(pole[2]);
            int y = Integer.parseInt(pole[3]);
            int people = Integer.parseInt(pole[1]);


            //addNode(new Node(id,x,y));
            if (id>3000 && id<3006) {
                addNode(new AirportNode(id,x,y));
                arrayAirport[air] = (AirportNode) lastNode;
                air++; 
            }
            else {
                addNode(new SettleNode(id,x,y));
                if (people < 2000) {
                    
                    lastNode.isSimple = true;
                }
                
                if (people > 10000) {
                    //addNode(new Node(id,x,y));
                    lastNode.isHeliport = true;
                }
                lastNode.people = people; 
            }
            
        }
        createEdge();

        
        createSupplied();

    }
    /**
     * ukladani grafu do souboru
     * @param filename jmeno souboru
     * @throws IOException Chyba zapisu do souboru.
     */
    public void save(String filename) throws IOException{
        FileWriter out = new FileWriter(filename);
        Node node = firstNode;
        while (node != null) {
            out.write(node.id+" ");
            out.write(node.people+" ");
            out.write(node.x+" ");
            out.write(node.y+" ");
            Edge edge = node.firstEdge;
            while (edge != null) {
                out.write(edge.node.id+" ");
                out.write(edge.cost+" ");
                edge = edge.next;
            }
            out.write("\n");
            node = node.next;
        }
        out.close();

    }
    /**
     * vytvari hrany v grafu
     * @throws RuntimeException Neocekavana chyba pri behu programu.
     */
    public void createEdge() throws RuntimeException {
        long distance;
        int distances[] = new int[3006];
        //int distancesCopy[];
        //int ppZarazka = 0;
        //int r = 0;
        Node node = firstNode;
        
        while(node != null) { // i = node
            //System.out.println(i);
            if (!node.isSimple) {
                distances = distancesFromNode(node);
                /*==================================================================================
                Node node2 = firstNode;
                for(int j = 1;node2 != null;j++) {
                    if (node != node2) {
                        //System.out.println(nodes[j].x);
                        distance = (long) Math.sqrt(((long)(node2.x-node.x)*(node2.x-node.x))+((long)(node2.y-node.y)*(node2.y-node.y)));
                        if (node2.isSimple) {
                            distance = Integer.MAX_VALUE;
                        }
                        distances[j] = (int) distance;

                        
                    }
                    else {
                        distances[j] = 0;
                    }
                    node2 = node2.next;
                }
                *///=====================================================================================
                //distancesCopy = null;
                createRoads(node,distances);
                /*distancesCopy = Arrays.copyOf(distances,distances.length);
                Arrays.sort(distancesCopy);
                if (node instanceof SettleNode) {
                   r = distancesCopy[11];
                   ppZarazka = 10;
                }
                else {
                    r = distancesCopy[61];
                    ppZarazka = 60;
                }
                
                //================prizareni hran=====================
                int pp = 0;
                Node node3 = firstNode;
                for (int j = 1; node3 != null;j++ ) {
                    if (r >= distances[j] && distances[j] != 0) {
                        
                        node.addEdge(new Edge(node3,distances[j]));
                        pp++;
                        if (pp == ppZarazka) {
                            break;
                        }
                    }
                    node3 = node3.next;
                }
                //===================/prizarebi harn==========================*/
            
            }
            if (node.isSimple) {
                //===================================find nearest heliport============================
                Node node2 = firstNode;
                int minDis = Integer.MAX_VALUE;
                Node minNode = null;
                while(node2 != null) {
                    if(node2.isHeliport){
                        distance = (long) Math.sqrt(((long)(node2.x-node.x)*(node2.x-node.x))+((long)(node2.y-node.y)*(node2.y-node.y)));
                        if (minDis > distance) {
                            minDis = (int)distance;
                            minNode = node2;
                        }
                    }
                    node2 = node2.next;
                }
                //===================================/find nearest heliport============================

                //node.edges[0] = new Edge(minNode,minDis);
                //node.edges[0].isRoad = false;
                
                node.addEdge(new Edge(minNode,minDis));
                node.lastEdge.isRoad = false;

                minNode.addEdge(new Edge(node,minDis));
                minNode.lastEdge.isRoad = false;

                
            }
            node = node.next;
        }


    }
    /**
     * generuje pocty obyvatel v sidlech
     */
    public void generatePeople(){
         Random fRandom = new Random();
         int pp = 0;
         Node node = firstNode;
         while(node != null) {
            if(node instanceof SettleNode){
                 int c =  (int) (fRandom.nextGaussian() *1900)+5300;
                 
                 if (c < 0) {
                     c = 10;
                 }
                 if (c < 2000) {
                     pp++;
                 }
                 node.people = c;
                 if (c > 10000) {
                     node.isHeliport = true;
                 }
             }
             node = node.next;
         }
         /*int p = 0;
         //System.out.println(pp*0.3);
         // Oznaceni sidel bez cest
         node = firstNode;
         int ppp = 0; // <----------------------------------- HERE''''''!
         while(node != null) {
            if (node instanceof SettleNode){
                 if (node.people < 2000 && ppp%3 == 0) { // <----------------------- ALSO HERE!!!!!!
                     node.isSimple = true;
                     p++;
                     ppp++; // <------------------------------AND HERE !!!!
                 }
                 if (node.people < 2000 && p >= pp*0.3) {
                     break; 
                 }
            }
            node = node.next;
         }
         // /Oznaceni sidel bez cest*/
         setSimpleNodes(pp);
    }
    /**
     * Oznaci mesta bez silnic.
     * @param pp Pocet mest pod 2000 obyvatel.
     */
    private void setSimpleNodes(int pp)
    {
        int p = 0;
         //System.out.println(pp*0.3);
         // Oznaceni sidel bez cest
         Node node = firstNode;
         int ppp = 0; // <----------------------------------- HERE''''''!
         while(node != null) {
            if (node instanceof SettleNode){
                
                 if (node.people < 2000 && ppp%3 == 0) { // <----------------------- ALSO HERE!!!!!!
                     node.isSimple = true;
                     
                     p++;
                     
                 }
                 if (node.people < 2000) {
                     ppp++; // <------------------------------AND HERE !!!!
                 }
                 if (node.people < 2000 && p >= pp*0.3) {
                     break; 
                 }
            }
            node = node.next;
         }
         // /Oznaceni sidel bez cest
    }
    /**
     * vraci nekratsi cestu z vrcholu do vrcholu
     * @param from Pocatecni vrchol
     * @param to Koncovy vrchol
     * @param build
     * @return Pole s vrcholy na nejkratsi ceste.
     */
    public Node[] dijkstra(Node from, Node to,boolean build){
        if (build) {
           dijkstra(from); 
        }
        Node node = to;
        //System.out.println(node.id);
        int i;
        for (i = 0;node != null;i++) {
            node = node.prev;
            //System.out.println("aa");   
        }   
        Node [] arrayNode = new Node[i];
        node = to;
        for (int j = 0;node != null;j++) {
            arrayNode[j] = node;
            node = node.prev;
            //System.out.println(node.id);  
        }
        Node[] pp = Arrays.copyOf(arrayNode, arrayNode.length);
        for (int x = 0 ; x<pp.length ;x++ ) {
            arrayNode[x] = pp[pp.length-1-x];
        }
        return arrayNode;

    }
    /**
     * ohodnoti graf (pojde graf a  zapise do promene {@code cost} hodnoty)
     * @param from Pocatecni vrchol
     */
    public boolean dijkstra(Node from){
        cleanGraph();
        from.isCloud = true;
        Edge edge;
        Node node = from;
        node.cost = 0;
        while (node != null) {
            edge = node.firstEdge;
            while(edge != null) {
                if (edge.node.cost > node.cost+edge.cost) {
                    edge.node.cost = node.cost+edge.cost;
                    edge.node.prev = node;
                }
                edge = edge.next;
            }
            //System.out.println(node.cost);
            node = addToCloud();


        }
        //System.out.println("HHHHHH");
        return true;
    }
    /**
     * pridani vrcholu do mraku (dijkstra)
     */
    private Node addToCloud(){
        Node minCost = new Node(1,1,1);
        minCost.cost = Integer.MAX_VALUE;
        Node node = firstNode;
        while (node != null) {
            if (!node.isCloud && node.cost < minCost.cost) {
                minCost = node;
            }
            node = node.next;
        }
        minCost.isCloud = true;
        if (minCost.cost == Integer.MAX_VALUE) {
            return null;
        }
        return minCost;

    }
    /**
     * inicializuje graf do puvodni podoby
     */
    public void cleanGraph(){
        Node node = firstNode;
        while (node != null) {

            node.cost = Integer.MAX_VALUE;
            node.isCloud = false;
            node.prev = null;

            node = node.next;
            
        }

    }
    /**
     * vypise statistiku o generovani vstupnich dat
     * @return Statisticka data
     */
    public String statistic(){
        int sumaP = 0;
        int sumaSimple = 0;
        int suma10 = 0;
        Node node = firstNode;
        int l1 = 0,l2 = 0,l3 = 0,l4 = 0,l5 = 0,ost = 0;
        while (node != null) {
            sumaP += node.people;
            if (node.people > 10000) {
                suma10++;
            }
            if (node.isSimple) {
                sumaSimple++;
            }
            if (arrayAirport[0] == node.suppliedFrom) {
               l1++; 
            }
            else if (arrayAirport[1] == node.suppliedFrom) {
               l2++; 
            }
            else if (arrayAirport[2] == node.suppliedFrom) {
               l3++; 
            }
            else if (arrayAirport[3] == node.suppliedFrom) {
               l4++; 
            }
            else if (arrayAirport[4] == node.suppliedFrom) {
               l5++; 
            }
            else {
                ost++;
                /*System.out.println(node.id);
                System.out.println(node.edges[0].node.people);
                
                for (int i = 0; i<node.edges[0].node.edges.length;i++ ) {
                    if (node.edges[0].node.edges[i] != null) {
                       System.out.print( node.edges[0].node.edges[i].node.id+" ");

                    }
                   
                }*/
            }
            node = node.next;   
        }
        return "people: "+sumaP+"\n" + "city without roads: "+sumaSimple+"\n"+ "city > 10000: "+suma10+"\n" + " l1: "+ l1
         + " l2: "+ l2 + " l3: "+ l3 + " l4: "+ l4 + " l5: "+ l5+"\n"+" ost: " + ost;
    }
    /**
     * ohodnoti graf (pojde graf a  zapise do promene {@code cost} hodnoty)
     */
    public void createSupplied(){
        Node node;
        int min = Integer.MAX_VALUE;
        AirportNode minNode = null;
        for (int i = 0;i<arrayAirport.length ;i++ ) {
            dijkstra(arrayAirport[i]);

            node = firstNode;
            while (node != null) {
                if (node instanceof SettleNode) {
                    node.costToAirAll[i] = node.cost;
                    
                }
                node = node.next; 
            }
        }
        node = firstNode;
        while (node != null) {
            if (node instanceof SettleNode) {
                min = Integer.MAX_VALUE;
                minNode = null;
                for (int j = 0;j<node.costToAirAll.length ;j++ ) {
                    if (min > node.costToAirAll[j]) {
                        min = node.costToAirAll[j];
                        minNode = arrayAirport[j];
                    }
                }
                node.costToAir = min;
                node.suppliedFrom = minNode;
                
                      
            }
            node = node.next;  
        }
    }
    /**
     * vrati vrchol
     * @param id ID vrcholu
     * @return objekt vrcholu
     */
    public Node get(int id){
        Node node  = firstNode;
        while (node != null) {
            if (node.id == id) {
                return node;
            }
            node = node.next;
        }
        return null;
    }
    /**
     * vrati vzdalenost ke vsem ostatim vrcholum
     * @param node Pocatecni vrchol
     * @return Pole se vzdalenostmi
     */
    public int[] distancesFromNode(Node node){
        long distance;
        int distances[] = new int[lastNode.id+1];
        Node node2 = firstNode;
        for(int j = 1;node2 != null;j++) {
            if (node != node2) {
                //System.out.println(nodes[j].x);
                distance = (long) Math.sqrt(((long)(node2.x-node.x)*(node2.x-node.x))+((long)(node2.y-node.y)*(node2.y-node.y)));
                if (node2.isSimple) {
                    distance = Integer.MAX_VALUE;
                }
                distances[j] = (int) distance;

                
            }
            else {
                distances[j] = 0;
            }
            node2 = node2.next;
        }
        return distances;
    }
    /**
     * vytvori hrany od daneho vrcholu
     * @param node Pocatecni vrchol
     * @param distances Pole se vzdalenostmi k ostatnim vrcholum
     */
    public void createRoads(Node node, int[] distances){
        int[] distancesCopy;
        int ppZarazka,r;
        distancesCopy = Arrays.copyOf(distances,distances.length);
        Arrays.sort(distancesCopy);
        if (node instanceof SettleNode) {
           r = distancesCopy[11];
           ppZarazka = 10;
        }
        else {
            r = distancesCopy[61];
            ppZarazka = 60;
        }
        
        //================prizareni hran=====================
        int pp = 0;
        Node node3 = firstNode;
        for (int j = 1; node3 != null;j++ ) {
            if (r >= distances[j] && distances[j] != 0) {
                
                node.addEdge(new Edge(node3,distances[j]));
                pp++;
                if (pp == ppZarazka) {
                    break;
                }
            }
            node3 = node3.next;
        } 
    }
    /**
     * prida nove sidlo
     * @param x Souradnice x
     * @param y Souradnice y
     * @param people Pocet obyvatel
     */
    public void addSettle(int x,int y,int people){
        Node newNode = new SettleNode(lastNode.id +1,x,y);
        addNode(newNode);

        int distances[] = new int[lastNode.id+1];
        //int distancesCopy[];

        newNode.people = people;
        distances = distancesFromNode(newNode);
        createRoads(newNode,distances);

        Edge arrival = newNode.firstEdge;
        while (arrival != null) {
            arrival.node.addEdge(new Edge(newNode,arrival.cost));
            arrival = arrival.next;
        }

        int min = Integer.MAX_VALUE;
        AirportNode minNode = null;
        for (int i = 0;i<arrayAirport.length ;i++ ) {
            dijkstra(arrayAirport[i]);
            newNode.costToAirAll[i] = newNode.cost;
            if (min > newNode.cost) {
                min = newNode.cost;
                minNode = arrayAirport[i];
                
            }
            
            
        }
        newNode.suppliedFrom = minNode;
        newNode.costToAir = min;
        
    }
    /**
     * doplni ke kazdemu vrcholu cestu k nejblizsimu letisti
     */
    public void generatePath(){
        for (int i = 0;i<arrayAirport.length ;i++ ) {
            dijkstra(arrayAirport[i]);
            Node node = firstNode;
            while (node != null) {
                if (arrayAirport[i] == node.suppliedFrom) {
                    node.path = dijkstra(arrayAirport[i],node,false);
                }
                node = node.next;
            } 
        }
    }
}