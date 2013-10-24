public class Settle extends Process
{	public int actualFood = 0;
	public int lastTime = 0;
    public Settle(int time)
    {
        super(time);
    }
    public void go(){
    	actualFood = actualFood - (Calendar.time - lastTime)/(24*60)*2*node.people;


    	
		int kolik;
		if (node.people*2*3 > 12000) {
			kolik = 12000 - actualFood;	
		}
		else {
			kolik = node.people*2*3 - actualFood;
		}
		Node [] path = Calendar.g.dijkstra(node.suppliedFrom,node);
		Calendar.q.add(new Car(Calendar.time,path,kolik));

		System.out.println("Settle no "+node.id);
    	System.out.println("Food: "+actualFood);
    	System.out.println("Stav: Obednavam jidlo " + kolik + "kg");

		time = (int) (Calendar.time+(((kolik+actualFood)/(2.0*node.people))*24.0*60.0)-60);
		Calendar.q.add(this);

    	
    }
}