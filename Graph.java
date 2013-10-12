import java.util.*;
import java.io.*;

public class Graph
{
    /**
     * Asociativni pole vrcholu.
     */
    //public Node[] nodes;
    public Node firstNode = null;
    public Node lastNode = null;
    
    /**
     * Konstruktor objektu tridy Graph
     */
    public Graph()
    {
        //nodes = new Node[0];
    }
    
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
        addNode(new AirportNode(3001,40000,40000));
        addNode(new AirportNode(3002,40000,460000));
        addNode(new AirportNode(3003,460000,460000));
        addNode(new AirportNode(3004,460000,40000));
        addNode(new AirportNode(3005,40000,250000));

    }
    public void load(String filename){

    }
    public void save(String filename) throws Exception{
        FileWriter out = new FileWriter(filename);
        Node node = firstNode;
        for (int i = 1;node != null ;i++ ) {
            if (node != null) {
                out.write(node.id+" ");
                out.write(node.people+" ");
                out.write(node.x+" ");
                out.write(node.y+" ");
                for (int j = 0;j<node.edges.length ;j++ ) {
                    if (node.edges[j] != null) {
                        out.write(node.edges[j].node.id+" ");
                        out.write(node.edges[j].cost+" ");
                    }
                }
                out.write("\n");
                node = node.next;
            }
        }
        out.close();

    }
    public void createEdge(){
        long distance;
        int distances[] = new int[3006];
        int distancesCopy[];
        int ppZarazka = 0;
        int r = 0;
        Node node = firstNode;
        
        while(node != null) { // i = node
            //System.out.println(i);
            if (!node.isSimple) { 
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
                //distancesCopy = null;
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
                    
                int pp = 0;
                Node node3 = firstNode;
                for (int j = 1; node3 != null;j++ ) {
                    if (r >= distances[j] && distances[j] != 0) {
                        //System.out.println(distancesCopy[pp+2]);
                        node.edges[pp] = new Edge(node3,distances[j]);
                        pp++;
                        if (pp == ppZarazka) {
                            break;
                        }
                    }
                    node3 = node3.next;
                }
            
            }
            if (node.isSimple) {
                Node node2 = firstNode;
                int minDis = Integer.MAX_VALUE;
                Node minNode = null;
                for(;node2 != null;) {
                    if(node2.isHeliport){
                        distance = (long) Math.sqrt(((long)(node2.x-node.x)*(node2.x-node.x))+((long)(node2.y-node.y)*(node2.y-node.y)));
                        if (minDis > distance) {
                            minDis = (int)distance;
                            minNode = node2;
                        }
                    }
                    node2 = node2.next;
                }
                node.edges[0] = new Edge(minNode,minDis);
                node.edges[0].isRoad = false;
            }
            node = node.next;
        }


    }
    public void generatePeople(){
         Random fRandom = new Random();
         int pp = 0;
         Node node = firstNode;
         while(node != null) {
            if(node instanceof SettleNode){
                 int c =  (int) (fRandom.nextGaussian() *1900)+5500;

                 if (c < 0) {
                     c = 10;
                 }
                 if (c<2000) {
                     pp++;
                 }
                 node.people = c;
                 if (node.people > 10000) {
                     node.isHeliport = true;
                 }
             }
             node = node.next;
         }
         int p = 0;
         //System.out.println(pp*0.3);
         node = firstNode;
         int ppp = 0;
         for(int j = 1;node != null;j++) {
            if (node instanceof SettleNode){
                 if (node.people < 2000) {
                     if (ppp%3 == 0) {
                         node.isSimple = true;
                         p++;
                     }
                     if (p >= pp*0.3) {
                        break; 
                     }
                     ppp++;
                 }
            }
            node = node.next;
         }
    }
}