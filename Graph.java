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
        nodes = new Node[3005]; //3000 settle and 5 aiport, index 0 is NULL
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
        for (x = 1;x<500000 ;x+=9000 ) {
            for (y = 1;y<500000 ;y+=9000 ) {
                xx  = r.nextInt(4000) - 2000;
                yy = r.nextInt(4000) - 2000;
                nodes[i] = new SettleNode(i,x+xx,y+yy);
                System.out.print(i);
                i++;
                if (i == 3001) {
                    x = 1000000;
                    y = 1000000;
                    break;
                }
            }
                
            
        }

    }
    public void load(String filename){

    }
}