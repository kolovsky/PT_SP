import java.util.ArrayList;

/**
 * Instance tridy {@code Helicop} predstavuji proces vrtulniku v simulaci.
 * @author  kolovsky
 * @author  jmacura
 * @version 1.00.000
 */
class Helicop extends Process{
	
	public Node start;
	public Node end;
	public int kolik;
    public static int lastID;
    public int id;
    ArrayList<String> log = new ArrayList<String>();
    int startTime;
    /**
     * konstruktor
     * @param time Kdy ma vrtulnik vyletet
     * @param start Odkud
     * @param end Kam
     * @param kolik Kolik veze
     */
    public Helicop(int time,Node start, Node end, int kolik)
    {
        super(time);
        this.id = lastID;
        incrementID();
        this.start = start;
        this.end = end;
        nextWork = 0;
        this.kolik = kolik;
        this.startTime = time;
    }
    /**
     * metoda volana po vybrani z fronty
     */
    public void goOn(){
    	if (nextWork == 0) { //nakladani do vrtulniku
    		deal();
    		nextWork = 1;
    		return;
    	}
    	if (nextWork == 4) {
    		Core.log("Vrtulnik id "+ id);
    		Core.log("Ukol splnen!");
            log.add(""+startTime+" "+Core.c.time+ " "+kolik+" "+end.id);
            ((Settle)start.proces).garage.addLast(this);
    		return;
    		
    	}
    	if (nextWork == 1) { //let tam
    		shift(start,end);
    		nextWork = 2;
    		return;
    		
    	}
    	if (nextWork == 2) { //vykladka z vrtulniku
    		deal();
    		nextWork = 3;
    		return;
    		
    	}
    	if (nextWork == 3) { //let zpet
    		((Settle)end.proces).addFood(kolik);
    		shift(end,start);
    		nextWork = 4;
    		return;
    		
    	}
    	
    	
    	
    }
    /**
     * nakladat/vykladat
     */
    public void deal(){
    	time = (int)((kolik/1000.0)*30.0) + Core.c.time;
		Core.log("Vrtulnik id "+ id);
		Core.log("Nakladam/vykladam do casu "+time);
		Core.c.getQueue().add(this);
    }
    /**
     * posun vrtulniku
     * @param from Odkud
     * @param to Kam
     */
    public void shift(Node from, Node to){
    	Edge edge = from.firstEdge;
		while (edge != null) {
			if (edge.node == to) {
				time = (int) (edge.cost/2500.0) + Core.c.time;//2500 metru/min
			}
			edge = edge.next;
		}
		Core.log("Vrtulnik id "+ id);
		Core.log("Letim do uzlu id = "+ to.id);
		Core.log("Budu tam v "+time);
		Core.c.getQueue().add(this);
    }
    /**
     * prideluje novou praci pro vrtulnik (recyklace vrtulniku)
     * @param time Kdy ma vrtulnik vyletet
     * @param start Odkud
     * @param end Kam
     * @param kolik Kolik veze
     */
    public void newWork(int time,Node start, Node end, int kolik){
        this.time = time;
        this.start = start;
        this.end = end;
        nextWork = 0;
        this.kolik = kolik;
        this.startTime = time;
    }
    /**
     * statistika k danemu vrtulniku
     * @param legend {@code true} vypise legendu
     */
    public String toString(boolean legend){
        String out = "Id: "+id+"\n";
        //out += "Actual place: "+ path[nextWork-1].id;
        if (nextWork <= 1 || nextWork == 4 ) {
            out += "Actual place: "+ start.id;
        }
        else {
            out += "Actual place: "+ end.id;
        }
        if (legend) {
            out += "Start End Quant Settle\n";
        }
        for (int i = 0;i<log.size() ;i++ ) {
            out += log.get(i)+"\n";

        }
        return out;
    }
    
    /**
     * zajistuje aktualni cislovani vrtulniku
     */
    private void incrementID()
    {
        lastID++;
    }
}