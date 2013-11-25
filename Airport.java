import java.util.*;

class Airport extends Process{

	int allFood = 500000;
	public PriorityQueue<Supply> pq = new PriorityQueue<Supply>(10, new SupCompare());
	public LinkedList<Car> garage;

	public Airport(int time,Node node){
		super(time);
		this.node = node;
        this.node.proces = this;
		pq.add(new Supply(500000,Core.c.time+2880));
		garage = new LinkedList<Car>();

	}
	public void goOn(){
		//actualFood += 500000;
		pq.add(new Supply(500000,Core.c.time+2880));
		allFood += 500000;
		time = Core.c.time + 60;
		Calendar.q.add(this);
		Core.log("Nove jidlo");

	}
	public boolean isFood(int kolik){
		if (pq.peek().expire < Core.c.time) {
			
			allFood -= pq.poll().quant;
		}
		if (allFood < kolik) {
			return false;
		}
		//else {
			return true;
		//}
		//return pq.peek().quant;
	}
	public int getFood(int quant){
		//pq.peek().quant -= quant;
		if (pq.peek().expire < Core.c.time) {
			allFood -= pq.poll().quant;
		}
		if (pq.peek().quant < quant) {
			Supply first = pq.poll();
			pq.peek().quant -= quant - first.quant;
			allFood -= quant;
			return quant;
		}
		else {
			pq.peek().quant -= quant;
			allFood -= quant;
			return quant;
		}
	}
	public void sendCar(int time,Node [] path,int kolik,Node helicop,boolean isFull){
		Car ncar;
		if (garage.size() == 0) {
			ncar = new Car(time,path,kolik,helicop,isFull);
			Calendar.allCar.add(ncar);
			Calendar.q.add(ncar);
		}
		else {
			ncar = garage.poll();
			ncar.newWork(time,path,kolik,helicop,isFull);
			Calendar.q.add(ncar);
		}
	}
	
}