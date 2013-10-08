import java.util.*;
import java.io.*;

public class Graph
{
    /**
     * Asociativni pole vrcholu.
     */
    public Node[] nodes;
    
    /**
     * Konstruktor objektu tridy Graph
     */
    public Graph()
    {
        nodes = new Node[0];
    }
    
    public void addNode()
    {
    }
    public void generate(){
        nodes = new Node[3006]; //3000 settle and 5 aiport, index 0 is NULL
        Random r = new Random(); //random
        boolean isGood;
        int distance, x,y,xx,yy,i;
            
        i = 1;
        for (x = 2000;x<498000 ;x+=9050 ) {
            for (y = 2000;y<498000 ;y+=9050 ) {
                xx  = r.nextInt(4000) - 2000;
                yy = r.nextInt(4000) - 2000;

                nodes[i] = new SettleNode(i,x+xx,y+yy);
                //System.out.print(i);
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
        nodes[3001] = new AirportNode(3001,40000,40000);
        nodes[3002] = new AirportNode(3002,40000,460000);
        nodes[3003] = new AirportNode(3003,460000,460000);
        nodes[3004] = new AirportNode(3004,460000,40000);
        nodes[3005] = new AirportNode(3005,40000,250000);

    }
    public void load(String filename){

    }
    public void save(String filename) throws Exception{
        FileWriter out = new FileWriter(filename);
        for (int i = 1;i<nodes.length ;i++ ) {
            if (nodes[i] != null) {
                out.write(nodes[i].id+" ");
                out.write(nodes[i].x+" ");
                out.write(nodes[i].y+" ");
                for (int j = 0;j<nodes[i].edges.length ;j++ ) {
                    out.write(nodes[i].edges[j].node.id+" ");
                    out.write(nodes[i].edges[j].cost+" ");
                }
                out.write("\n");
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
        for (int i = 1;i<nodes.length ;i++ ) {
            //System.out.println(i);
            for (int j = 1;j<nodes.length;j++ ) {
                if (j != i) {
                    //System.out.println(nodes[j].x);
                    distance = (long) Math.sqrt(((long)(nodes[j].x-nodes[i].x)*(nodes[j].x-nodes[i].x))+((long)(nodes[j].y-nodes[i].y)*(nodes[j].y-nodes[i].y)));
                    //System.out.println(distance);
                    distances[j] = (int) distance;
                    
                }
                else {
                    distances[j] = 0;
                }
            }
            //distancesCopy = null;
            distancesCopy = Arrays.copyOf(distances,distances.length);
            Arrays.sort(distancesCopy);
            if (i<3001) {
               r = distancesCopy[11];
               ppZarazka = 10;
            }
            else {
                 r = distancesCopy[61];
                 ppZarazka = 60;
            }
                
            //int r = distancesCopy[11];
            int pp = 0;
            for (int j = 1; j<nodes.length;j++ ) {
                if (r >= distances[j] && distances[j] != 0) {
                    //System.out.println(distancesCopy[pp+2]);
                    nodes[i].edges[pp] = new Edge(nodes[j],distances[j]);
                    pp++;
                    if (pp == ppZarazka) {
                        break;
                    }
                }
            }
            
            
        }

    }
    public void generatePeople(){
         Random fRandom = new Random();
         double c =   5000 + fRandom.nextGaussian() * 10000;
    }
}