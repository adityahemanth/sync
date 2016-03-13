import java.util.Comparator;

public class PriorityQueueComparator implements Comparator<Scheduler2PL>
{
    
    public int compare(Scheduler2PL x, Scheduler2PL y)
    {
        // Assume neither string is null. Real code should
        // probably be more robust
        // You could also just return x.length() - y.length(),
        // which would be more efficient.
        if (x.length() < y.length())
        {
            return -1;
        }
        if (x.length() > y.length())
        {
            return 1;
        }
        return 0;
    }
}