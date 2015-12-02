import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
    public static void main(String[] args) {
        int k = 5;
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals(" ")) q.enqueue(item);
        }
        for(String a : q) {
            if(k > 0) StdOut.println(a);
            k--;
        }
    }
}