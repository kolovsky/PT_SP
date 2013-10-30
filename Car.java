import java.util.*;

class Car extends Process{

	Node [] path;
	int id;
	int kolik;
    Node helicop;
	public Car(int time,Node [] path,int kolik,Node helicop)
    {
        super(time);
        this.path = path;
        nextWork = -1;
        this.kolik = kolik;
        this.helicop = helicop;


    }
    public void go(){
    	if (nextWork == path.length-1) {
            deal();
            if (helicop != null) {
                Calendar.q.add(new Helicop(Calendar.time,path[path.length-1],helicop,kolik));
            }
            
            List<Node> list = Arrays.asList(path);
            Collections.reverse(list);
            path = (Node[]) list.toArray();
            nextWork++;
            return;
            

    	}
    	if (nextWork == -1 ) {
    		deal();
           
    		
    	}
        if (nextWork == 2*path.length-1) {
           Core.log("Vozidlo cil "+ path[path.length-1].id);
           Core.log("Dojelo!");
           return;
        }
        if (nextWork >= path.length) {
            if (nextWork == path.length) {
                if (helicop == null) {
                    ((Settle) path[0].proces).actualFood += kolik;
                }
            }
            shift(nextWork-path.length);
            nextWork++;
            return;
            
        }
    	if (nextWork >= 0) {
    		shift(nextWork);   	
    	}
        
        
        
    	nextWork++;
    	
    }
    public void deal(){
        time = (int)((kolik/1000.0)*30.0) + Calendar.time; //nakladka 30min na tunu
        Core.log("Vozidlo cil "+ path[path.length-1].id);
        Core.log("Nakladam/vykladam do casu "+ time);
        Calendar.q.add(this);
    }
    public void shift(int nextWork){
        Edge edge = path[nextWork].firstEdge;
        while (edge != null) {
            if (edge.node == path[nextWork+1]) {
                time = (int) (edge.cost/500.0) + Calendar.time;//500 metru/min
            }
            edge = edge.next;
        }
        Core.log("Vozidlo cil "+ path[path.length-1].id);
        Core.log("Jedu do uzlu id = "+ path[nextWork+1].id);
        Core.log("Budu tam v "+time);
        Calendar.q.add(this);
    }
}