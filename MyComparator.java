import java.util.*;

public class MyComparator implements Comparator<Process>{
    @Override
    public int compare(Process o1, Process o2) {
        return o1.time - o2.time;
    }
}