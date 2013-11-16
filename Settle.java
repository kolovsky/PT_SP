import java.util.*;

public class Settle extends Process
{	public int actualFood = 0;
	public int lastTime = 0;
    public Settle(int time)
    {
        super(time);
        
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
    	actualFood = actualFood - (int)((Calendar.time - lastTime)/(24.0*60.0)*2*node.people);
    	lastTime = Calendar.time;
    }
    public void go(){
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
			if (((Airport)node.suppliedFrom.proces).isFood(kolik) == false) {
				//lastTime = time;
				time = time + 60 - time%60 + 1;
				Calendar.q.add(this);
				Core.log("precasovano");
            	return;
            }
			Node [] path = Calendar.g.dijkstra(node.suppliedFrom,node.firstEdge.node);
			Calendar.q.add(new Car(Calendar.time,path,kolik,node,true));
            //System.out.println(Arrays.toString(path));
            //System.out.println(node.toString());
            Core.log("aaa");
            //node.firstEdge.node.proces.time += (int)(((12000.0-kolik)/(2.0+node.people))*60.0*24.0);

    	}
    	else {
			
			if (node.people*2*3 - actualFood > 12000) {
				kolik = 12000;	
			}
			else {
				kolik = node.people*2*3 - actualFood;
			}
			if (((Airport)node.suppliedFrom.proces).isFood(kolik) == false) {
				//lastTime = time;
				time = time + 60 - time%60 + 1;
				Calendar.q.add(this);
				Core.log("precasovano");
            	return;
            }
			Node [] path = Calendar.g.dijkstra(node.suppliedFrom,node);
			Calendar.q.add(new Car(Calendar.time,path,kolik,null,false));
		}
		Core.log("Settle no "+node.id);
    	Core.log("Food: "+actualFood);
    	Core.log("Stav: Obednavam jidlo " + kolik + "kg");


    	((Airport) node.suppliedFrom.proces).getFood(kolik);

    	//lastTime = time;

		time = (int) (Calendar.time+(((kolik+actualFood)/(2.0*node.people))*24.0*60.0)-60);
		Core.log("priste: "+time);
		Calendar.q.add(this);

    	
    }
}