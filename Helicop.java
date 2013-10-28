class Helicop extends Process{
	
	public Node start;
	public Node end;
	public int kolik;

    public Helicop(int time,Node start, Node end, int kolik)
    {
        super(time);
        this.start = start;
        this.end = end;
        nextWork = 0;
        this.kolik = kolik; 
    }
    public void go(){
    	if (nextWork == 0) { //nakladani do vrtulniku
    		deal();
    		nextWork = 1;
    		return;
    	}
    	if (nextWork == 4) {
    		Core.lw.log("Vrtulník cíl "+ end.id);
    		Core.lw.log("Ukol zplnen!");
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
    		((Settle)end.proces).actualFood += kolik;
    		shift(end,start);
    		nextWork = 4;
    		return;
    		
    	}
    	
    	
    	
    }
    public void deal(){
    	time = (int)((kolik/1000.0)*30.0) + Calendar.time;
		Core.lw.log("Vrtulník cíl "+ end.id);
		Core.lw.log("Nakladam/vykladam do casu "+time);
		Calendar.q.add(this);
    }
    public void shift(Node from, Node to){
    	Edge edge = from.firstEdge;
		while (edge != null) {
			if (edge.node == to) {
				time = (int) (edge.cost/2500.0) + Calendar.time;//2500 metru/min
			}
			edge = edge.next;
		}
		Core.lw.log("Vrtulnik cíl "+ end.id);
		Core.lw.log("Letim do uzlu id = "+ to.id);
		Core.lw.log("Budu tam v "+time);
		Calendar.q.add(this);
    }
}