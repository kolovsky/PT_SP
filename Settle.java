import java.util.*;

public class Settle extends Process
{	public int actualFood = 0;
	public int lastTime = 0;
    public LinkedList<Helicop> garage;
    public static int lastID;
    public int id;

    public Settle(int time, Node node)
    {
        super(time);
        incrementID();
        this.id = lastID;
        this.node = node;
        this.node.proces = this;
        if (this.node.people > 10000) {
            garage = new LinkedList<Helicop>();
        }

    }
    private void incrementID()
    {
        lastID++;
    }
    public void addFood(int kolik){
    	//actualFood = actualFood - (int)((Calendar.time - lastTime)/(24.0*60.0)*2*node.people);
    	actualFood +=  kolik;
    	//lastTime = time;
    	if (kolik != 0) {
    		node.log.addLog(kolik);
    	}
    }
    public void actualizatedFood(){
    	actualFood = actualFood - (int)((Core.c.time - lastTime)/(24.0*60.0)*2*node.people);
    	lastTime = Core.c.time;
    }
    public void goOn(){
    	actualizatedFood();
    	if (actualFood < 0) {
    		actualFood = 0;
    	}
    	int kolik;
    	if (node.isSimple) {
    		if (node.people*2*3 - actualFood > 2000) { // maximum co muzu objednat
				kolik = 2000;

			}
			else {
				kolik = node.people*2*3 - actualFood;
			}
			if (!((Airport)node.suppliedFrom.proces).isFood(kolik)) {
				//lastTime = time;
				time = time + 60 - time%60 + 1;
				Core.c.getQueue().add(this);
				Core.log("precasovano");
            	return;
            }
			Node [] path = Core.c.getGraph().dijkstra(node.suppliedFrom,node.firstEdge.node);
            ((Airport) node.suppliedFrom.proces).sendCar(Core.c.time,path,kolik,node,true);
			//Calendar.q.add(new Car(Calendar.time,path,kolik,node,true)); //!!!!!!!!!NEMAZAT!!!!!!!!!!
            
            //Core.log("aaa"); //?! <-- vyznam ?
            //node.firstEdge.node.proces.time += (int)(((12000.0-kolik)/(2.0+node.people))*60.0*24.0); //!!!!!!!!!NEMAZAT!!!!!!!!!!

    	}
    	else {
			if (node.people*2*3 - actualFood > 12000) {
				kolik = 12000;	
			}
			else {
				kolik = node.people*2*3 - actualFood;
			}
			if (!((Airport)node.suppliedFrom.proces).isFood(kolik)) {
				//lastTime = time;
				time = time + 60 - time%60 + 1;
				Core.c.getQueue().add(this);
				Core.log("precasovano");
            	return;
            }
			Node [] path = Core.c.getGraph().dijkstra(node.suppliedFrom,node);
            ((Airport)node.suppliedFrom.proces).sendCar(Core.c.time,path,kolik,null,true);
			//Calendar.q.add(new Car(Calendar.time,path,kolik,null,false)); //!!!!!!!!!NEMAZAT!!!!!!!!!!
		}
		Core.log("Settle No "+node.id);
    	Core.log("Food: "+actualFood);
    	Core.log("Stav: Objednavam jidlo " + kolik + " kg");


    	((Airport) node.suppliedFrom.proces).getFood(kolik);

    	//lastTime = time;

		time = (int) (Core.c.time+(((kolik+actualFood)/(2.0*node.people))*24.0*60.0)-60);
		Core.log("priste: "+time);
		Core.c.getQueue().add(this);

    	
    }
    public void sendHelicop(int time,Node start, Node end, int kolik){
        Helicop ncar;
        if (garage.size() == 0) {
            ncar = new Helicop(time,start,end,kolik);
            Core.c.allHelicop.add(ncar);
            Core.c.getQueue().add(ncar);
        }
        else {
            ncar = garage.poll();
            ncar.newWork(time,start,end,kolik);
            Core.c.getQueue().add(ncar);
        }
    }
    public String toString(boolean legend){
        return node.log.toString();
    }
}