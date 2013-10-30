public class Settle extends Process
{	public int actualFood = 0;
	public int lastTime = 0;
    public Settle(int time)
    {
        super(time);
    }
    public void go(){
    	actualFood = actualFood - (int)((Calendar.time - lastTime)/(24.0*60.0)*2*node.people);
    	if (actualFood < 0) {
    		actualFood = 0;
    	}
    	int kolik;
    	if (node.isSimple) {
    		if (node.people*2*3 > 2000) {
				kolik = 2000 - actualFood;	
			}
			else {
				kolik = node.people*2*3 - actualFood;
			}
			if (((Airport)node.suppliedFrom.proces).actualFood < kolik) {
				lastTime = time;
				time = time + 60 - time%60 + 1;
				Calendar.q.add(this);
				Core.log("precasovano");
            	return;
            }
			Node [] path = Calendar.g.dijkstra(node.suppliedFrom,node.firstEdge.node);
			Calendar.q.add(new Car(Calendar.time,path,kolik,node));
    	}
    	else {
			
			if (node.people*2*3 > 12000) {
				kolik = 12000 - actualFood;	
			}
			else {
				kolik = node.people*2*3 - actualFood;
			}
			if (((Airport)node.suppliedFrom.proces).actualFood < kolik) {
				lastTime = time;
				time = time+ 60 - time%60 + 1;
				Calendar.q.add(this);
				Core.log("precasovano");
            	return;
            }
			Node [] path = Calendar.g.dijkstra(node.suppliedFrom,node);
			Calendar.q.add(new Car(Calendar.time,path,kolik,null));
		}
		Core.log("Settle no "+node.id);
    	Core.log("Food: "+actualFood);
    	Core.log("Stav: Obednavam jidlo " + kolik + "kg");


    	( (Airport) node.suppliedFrom.proces).actualFood = ((Airport) node.suppliedFrom.proces).actualFood - kolik;

    	lastTime = time;

		time = (int) (Calendar.time+(((kolik+actualFood)/(2.0*node.people))*24.0*60.0)-60);
		Core.log("priste: "+time);
		Calendar.q.add(this);

    	
    }
}