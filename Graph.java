import java.util.*;

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
        
            /*while (true) {
                /*x = r.nextInt(500000);
                y = r.nextInt(500000);
                //System.out.print(x);
                //System.out.println(y);
                isGood = true;
                for (int j = 1;j<i ;j++ ) {
                    distance = (int) Math.sqrt((nodes[j].x-x)*(nodes[j].x-x)+(nodes[j].y-y)*(nodes[j].y-y));
                    if (distance < 50) {

                        isGood = false;
                        break;
                    }
                }
                if (isGood) {
                    break;
                }
                

                                
            }
            nodes[i] = new SettleNode(i,x,y); */
            
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

    }
    public void load(String filename){

    }
    public void save(String filename){

    }
    public void createEdge(){
        long distance;
        int distances[] = new int[3001];
        int distancesCopy[];
        for (int i = 1;i<nodes.length - 5 ;i++ ) {
            System.out.println(i);
            for (int j = 1;j<nodes.length -5;j++ ) {
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
            int r = distancesCopy[11];
            int pp = 0;
            for (int j = 1; j<3001;j++ ) {
                if (r >= distances[j] && distances[j] != 0) {
                    //System.out.println(distancesCopy[pp+2]);
                    nodes[i].edges[pp] = new Edge(nodes[j],distances[j]);
                    pp++;
                    if (pp == 10) {
                        break;
                    }
                }
            }
            
            
        }

    }
}