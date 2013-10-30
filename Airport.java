class Airport extends Process{
	int actualFood = 500000;
	public Airport(int time){
		super(time);
	}
	public void go(){
		actualFood += 500000;
		time = Calendar.time + 60;
		Calendar.q.add(this);
		Core.log("Nove jidlo");

	}
	
}