import java.util.*;

class Airport extends Process{
	int allFood = 500000;
	public PriorityQueue<Supply> pq = new PriorityQueue<Supply>(10, new MyComparatorSupply());
	public Airport(int time){
		super(time);
		pq.add(new Supply(500000,Calendar.time+2880));
	}
	public void go(){
		//actualFood += 500000;
		pq.add(new Supply(500000,Calendar.time+2880));
		allFood += 500000;
		time = Calendar.time + 60;
		Calendar.q.add(this);
		Core.log("Nove jidlo");

	}
	public boolean isFood(int kolik){
		if (pq.peek().expire < Calendar.time) {
			pq.poll();
		}
		if (allFood < kolik) {
			return false;
		}
		else {
			return true;
		}
		//return pq.peek().quant;
	}
	public int getFood(int quant){
		//pq.peek().quant -= quant;
		if (pq.peek().expire < Calendar.time) {
			pq.poll();
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
	
}