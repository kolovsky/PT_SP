abstract class Process{
	int time;
	Node node;
	int nextWork;
	//int actualFood;
	public Process(int time){
		this.time = time;
	}
	
	public abstract void goOn();
	public abstract String toString(boolean legend);
}