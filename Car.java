import java.util.*;

class Car extends Process{

    Node [] path;
    int id;
    int kolik;
    Node helicop;
    int cast; //kolik do vrtulniku cca 2000kg
    public static int lastID;
    ArrayList<String> log = new ArrayList<String>();
    int startTime;
    boolean isEmpty = false;

    public Car(int time,Node [] path,int kolik,Node helicop,boolean isFull)
    {
        super(time);
        this.startTime = time;
        this.id = lastID;
        incrementID();
        this.path = path;
        nextWork = -1;
        //this.kolik = kolik;
        this.helicop = helicop;
        if (isFull) {
            this.kolik = 12000;
            this.cast = kolik;
        }
        else {
            this.cast = kolik;
            this.kolik = kolik;
        }

    }
    
    private void incrementID()
    {
        lastID++;
    }
    
    public void goOn(){
        if (nextWork == path.length-1) {
            deal();
            if (helicop != null) {
                //Calendar.q.add(new Helicop(Calendar.time,path[path.length-1],helicop,cast));
                ((Settle)path[path.length-1].proces).sendHelicop(Core.c.time,path[path.length-1],helicop,cast);
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
           ((Airport) path[path.length-1].proces).garage.add(this);
           log.add(""+startTime+" "+Core.c.time+ " "+kolik+" "+path[0]);
           return;
        }
        if (nextWork >= path.length) {
            if (nextWork == path.length && helicop == null) {
                ((Settle) path[0].proces).addFood(cast); ///tady asi cast misto kolik
            }
            if (nextWork == path.length && helicop != null) {
                ((Settle) path[0].proces).addFood(kolik - cast);
            }
            shift(nextWork-path.length);
            //vyklopeni na ceste zpet
            if (cast != kolik && helicop == null && path[nextWork-path.length].people > 5000 && !isEmpty) {
                ((Settle)path[nextWork-path.length].proces).addFood(kolik - cast);
                isEmpty = true;
            }
            //vyklopeni ba cest zpet
            nextWork++;
            return;
        }
        if (nextWork >= 0) {
            shift(nextWork);    
        }
        
        nextWork++;
    }
    
    public void deal(){
        time = (int)((kolik/1000.0)*30.0) + Core.c.time; //nakladka 30min na tunu
        Core.log("Vozidlo id "+ id);
        Core.log("Nakladam/vykladam do casu "+ time);
        Core.c.getQueue().add(this);
    }
    
    public void shift(int nextWork){
        Edge edge = path[nextWork].firstEdge;
        while (edge != null) {
            if (edge.node == path[nextWork+1]) {
                time = (int) (edge.cost/500.0) + Core.c.time;//500 metru/min
            }
            edge = edge.next;
        }
        Core.log("Vozidlo id "+ id);
        Core.log("Jedu do uzlu id = "+ path[nextWork+1].id);
        Core.log("Budu tam v "+time);
        Core.c.getQueue().add(this);
    }
    public void newWork(int time,Node [] path,int kolik,Node helicop,boolean isFull){
        this.time = time;
        this.path = path;
        nextWork = -1;
        //this.kolik = kolik;
        this.startTime = time;
        this.helicop = helicop;
        if (isFull) {
            this.kolik = 12000;
            this.cast = kolik;
        }
        else {
            this.cast = kolik;
            this.kolik = kolik;
        }
        isEmpty = false;
    }
    public String toString(boolean legend){
        String out = "Id: "+id+"\n";
        if (nextWork < 1) {
            out += "Actual place: "+ path[0].id;
        }
        else if(nextWork < path.length){
            out += "Actual place: "+ path[nextWork-1].id;
        }
        else {
            out += "Actual place: "+ path[nextWork-path.length].id;
        }
        out += " Vezu: "+kolik+"kg Primarni cil: "+path[path.length-1].id+"\n";
        if (legend) {
            out += "Start End Quant Settle\n";
        }
        for (int i = 0;i<log.size() ;i++ ) {
            out += log.get(i)+"\n";

        }
        return out;
    }
}