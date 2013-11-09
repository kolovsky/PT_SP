import java.util.*;

public class MyComparatorSupply implements Comparator<Supply>{
    @Override
    public int compare(Supply o1, Supply o2) {
        return o1.expire - o2.expire;
    }
}