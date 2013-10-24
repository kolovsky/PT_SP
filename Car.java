class Car extends Process{
	Node [] path;
	int id;
	int kolik;
	public Car(int time,Node [] path,int kolik)
    {
        super(time);
        this.path = path;
        nextWork = -1;
        this.kolik = kolik;


    }
    public void go(){
    	if (nextWork == path.length-1) {
    		System.out.println("Vozidlo cíl "+ path[path.length-1].id);
    		System.out.println("Dojelo!");
    		return;
    	}
    	if (nextWork == -1 ) {
    		time = (kolik/1000)*30; //nakladka 30min na tunu
    		System.out.println("Vozidlo cíl "+ path[path.length-1].id);
    		System.out.println("Nakladam do casu "+ time);
    		Calendar.q.add(this);
    		
    	}
    	if (nextWork >= 0) {
    		Edge edge = path[nextWork].firstEdge;
    		while (edge != null) {
    			if (edge.node == path[nextWork+1]) {
    				time = (int) (edge.cost/500.0) + Calendar.time;//500 metru/min
    			}
    			edge = edge.next;
    		}
    		System.out.println("Vozidlo cíl "+ path[path.length-1].id);
    		System.out.println("Jedu do uzlu id = "+ path[nextWork+1].id);
    		System.out.println("Budu tam v "+time);
    		Calendar.q.add(this);
    		
    	}
    	nextWork++;
    	
    }
}