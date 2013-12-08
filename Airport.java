import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author  kolovsky
 * @author  jmacura
 * @version 1.00.000
 */
class Airport extends Process{

    int allFood = 500000;
    public PriorityQueue<Supply> pq = new PriorityQueue<Supply>(10, new Comparator<Supply>() {
        @Override
        public int compare(Supply o1, Supply o2) {
            return o1.expire - o2.expire;
        }});
    public LinkedList<Car> garage;
    
    /**
     * konstruktor
     * @param time Cas prvniho spusteni
     * @param node Prislusnost k uzlu
     */
    public Airport(int time,Node node){
        super(time);
        this.node = node;
        this.node.proces = this;
        pq.add(new Supply(500000,Core.c.time+2880));
        garage = new LinkedList<Car>();

    }
    /**
     * metoda volana po vyberu z fronty.
     */
    @Override
    public void goOn(){
        //actualFood += 500000;
        pq.add(new Supply(500000,Core.c.time+2880));
        allFood += 500000;
        time = Core.c.time + 60;
        Core.c.getQueue().add(this);
        Core.log("Nove jidlo");

    }
    /**
     * metoda pro zjisteni jestli je dostatek jidla na letisti
     * @param kolik Mnozstvi
     * @return {@code true}, je-li jidla dostatek; {@code false} jindy.
     */
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
    /**
     * vybira jidlo z letiste
     * @param quant Mnozstvi
     * @return Mnozstvi
     */
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
    /**
     * posila nakladni auto na cestu
     * @param time Kdy
     * @param path Kudy
     * @param kolik
     * @param helicop Koncovy vrchol pro sidlo bez silnice jinak null
     * @param isFull Doplnit nakladak?
     */
    public void sendCar(int time,Node [] path,int kolik,Node helicop,boolean isFull){
        Car ncar;
        if (garage.size() == 0) {
            ncar = new Car(time,path,kolik,helicop,isFull);
            Core.c.allCar.add(ncar);
            Core.c.getQueue().add(ncar);
        }
        else {
            ncar = garage.poll();
            ncar.newWork(time,path,kolik,helicop,isFull);
            Core.c.getQueue().add(ncar);
        }
    }
    /**
     * vypis
     * @return ID letiste
     */
    @Override
    public String toString(boolean legend)
    {
        return "Toto je letiste" + node.id;
    }
}