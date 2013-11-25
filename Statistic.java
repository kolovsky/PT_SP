class Statistic{
	//public int allFoodHelp = 0;
	public String [] log = new String[8];
	public int [] threeFood = new int[3];

	public void addLog(int kolik){
		log[Core.c.time/1440] += "("+Core.c.time+" "+kolik+" kg)";
		threeFood[Core.c.time/4320] += kolik;
	}
	public String toString(){
		String out = "";
		for (int i = 0;i<7 ;i++ ) {
			out = out + i+". den ";
			out = out +log[i];
		}
		out = out + "\n";
		int allFood = 0;
		for (int j = 0;j<3 ;j++ ) {
			out = out + +j+". trojden "+threeFood[j]+" kg";
			allFood += threeFood[j];
		}
		out = out + "\n";
		out = out + "Celkova dovezena pomoc: "+allFood;
		return out;
	}	
}