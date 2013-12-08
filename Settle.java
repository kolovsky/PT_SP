import java.util.LinkedList;
import java.util.Arrays;

/**
 * Instance tridy {@Settle} reprezentuji proces mesta v simulaci.
 * @author  kolovsky
 * @author  jmacura
 * @version 1.00.000
 */
public class Settle extends Process
{	public int actualFood = 0;
	public int lastTime = 0;
    public LinkedList<Helicop> garage;
    public static int lastID;
    public int id;
    /**
     * konstruktor
     * @param time Cas prvniho objednani (nacasovani procesu)
     * @param node Prislusnost k vrcholu grafu (k sidlu)
     */
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
    
    /**
     * pridat jidlo do sidla
     * @param kolik Kolik jidla
     */
    public void addFood(int kolik){
        if (actualFood == 0) {
            lastTime = Core.c.time;
        }
    	//actualFood = actualFood - (int)((Calendar.time - lastTime)/(24.0*60.0)*2*node.people);
    	actualFood +=  kolik;
    	//lastTime = time;
    	if (kolik != 0) {
    		node.log.addLog(kolik);
    	}
    }
    /**
     * aktualizuje mnozstvi jidla v sidle
     */
    public void actualizatedFood(){
    	actualFood = actualFood - (int)((Core.c.time - lastTime)/(24.0*60.0)*2*node.people);
    	lastTime = Core.c.time;
    }
    /**
     * metoda volana po vyberu z fronty
     */
    public void goOn(){
    	actualizatedFood();
    	if (actualFood < 0) {
    		actualFood = 0;
    	}
    	int kolik;
    	if (node.isSimple) {
    		if (node.people*2*3 - getPrebytek() > 2000) { // maximum co muzu objednat
				kolik = 2000;

			}
			else {
				kolik = node.people*2*3 - getPrebytek();
			}
			if (!((Airport)node.suppliedFrom.proces).isFood(kolik)) {
				//lastTime = time;
				time = time + 60 - time%60 + 1;
				Core.c.getQueue().add(this);
				Core.log("precasovano");
            	return;
            }
			Node [] path = Arrays.copyOf(node.firstEdge.node.path,node.firstEdge.node.path.length);//Core.c.getGraph().dijkstra(node.suppliedFrom,node.firstEdge.node)
            ((Airport) node.suppliedFrom.proces).sendCar(Core.c.time,path,kolik,node,true);
			//Calendar.q.add(new Car(Calendar.time,path,kolik,node,true)); //!!!!!!!!!NEMAZAT!!!!!!!!!!
            
            //Core.log("aaa"); //?! <-- vyznam ?
            //node.firstEdge.node.proces.time += (int)(((12000.0-kolik)/(2.0+node.people))*60.0*24.0); //!!!!!!!!!NEMAZAT!!!!!!!!!!

    	}
    	else {
			if (node.people*2*3 - getPrebytek() > 12000) {
				kolik = 12000;	
			}
			else {
				kolik = node.people*2*3 - getPrebytek();
			}
			if (!((Airport)node.suppliedFrom.proces).isFood(kolik)) {
				//lastTime = time;
				time = time + 60 - time%60 + 1;
				Core.c.getQueue().add(this);
				Core.log("precasovano");
            	return;
            }
			Node [] path = Arrays.copyOf(node.path,node.path.length);//Core.c.getGraph().dijkstra(node.suppliedFrom,node); //!!!!!!!!!NEMAZAT!!!!!!!!!!
            ((Airport)node.suppliedFrom.proces).sendCar(Core.c.time,path,kolik,null,true);
			//Calendar.q.add(new Car(Calendar.time,path,kolik,null,false)); //!!!!!!!!!NEMAZAT!!!!!!!!!!
		}
		Core.log("Settle No "+node.id);
    	Core.log("Food: "+actualFood);
    	Core.log("Stav: Objednavam jidlo " + kolik + " kg");


    	((Airport) node.suppliedFrom.proces).getFood(kolik);

    	//lastTime = time;

		time = (int) (Core.c.time+(((kolik+getPrebytek())/(2.0*node.people))*24.0*60.0)-60);
		Core.log("priste: "+time);
		Core.c.getQueue().add(this);

    	
    }
    /**
     * posle vrtulnik
     * @param time Kdy ma vrtulnik vyletet
     * @param start Odkud
     * @param end Kam
     * @param kolik Kolik veze
     */
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
    /**
     * statistika pro dane sidlo
     * @param legend vytvorit statistiku
     */
    public String toString(boolean legend){
        return node.log.toString();
    }
    /**
     * prevede jidlo na cas (za jak dlouho sni urcite mnozstvi jidla)
     * @param kolik Kolik jidla
     */
    public int food2time(int kolik){
        return (int)((kolik/(2.0*node.people))*24.0*60.0);
    }
    /**
     * vrati prebytek jidla pri prijezdu dalsiho nakladniho auta (vrtulniku)
     */
    public int getPrebytek(){
        if (node.isSimple) {

            return (int)((food2time(actualFood) - (int)((node.firstEdge.node.costToAir/500.0)+((12000+4000)*30)/1000.0+(node.firstEdge.cost/2500.0)))/(24.0*60.0))*2*node.people;
        }
        else {
            return (int)((food2time(actualFood) - (int)((node.costToAir/500.0)+(12000*30*2)/1000))/(int)(24.0*60.0))*2*node.people;
        }
        
    }
    
    private void incrementID()
    {
        lastID++;
    }
}