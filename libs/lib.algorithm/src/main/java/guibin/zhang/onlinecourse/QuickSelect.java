package guibin.zhang.onlinecourse;

import java.util.Arrays;

/**
 * Given an array of N items, find a kth smallest item.
 * 
 * This issue can be extended to as the distributed kth issue.
 * http://www.quora.com/Distributed-Algorithms/What-is-the-distributed-algorithm-to-determine-the-median-of-arrays-of-integers-located-on-different-computers
 * 
 * Idea:
 * 1. Have a master node.
 * 2. master node select a random number as the pivot, and distribute this pivot to all the nodes.
 * 3. on each node perform the partition, and we can get index of the pivot on each node.
 * 4. sum(idx) < k, discard all the items that less than a[idx], otherwise discard all the greater than a[idx].
 * 5. if (sum(idx) == k), then, we get the kth item.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class QuickSelect {
    
    public Comparable selectK(Comparable[] a, int k) {
        
        Comparable result = null;
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {  // Note here is the equal
            int j = partition(a, lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                result = a[j];
                break;
            }
        }
        
        return result;
    }
    
    public int partition(Comparable[] a, int lo, int hi) {
        
        int i = lo + 1;
        int j = hi;
        while (true) {
            //Scan from lo to hi to reach the first element that is greater than pivot
            while (i < hi && a[i].compareTo(a[lo]) <= 0) {
                i++;
            }
            //Scan from hi to lo to reach the first element that is less than pivot
            while (j > lo && a[j].compareTo(a[lo]) >= 0) {
                j--;
            }
            
            if (i >= j) break;
            
            swap(a, i, j);
        }
        swap(a, lo, j);
        
        return j;
    }
    
    private void swap(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    
    public static void main(String[] args) {
        Comparable[] a = {50, 61, 44, 32, 99, 87, 50, 52, 53, 12};
        QuickSelect qs = new QuickSelect();
        int k = 6;
        System.out.println("Target array: ");
        Arrays.stream(a).forEach((i) -> System.out.print(i + ","));
        System.out.println();
        System.out.println(k + "th is: " + qs.selectK(a, k));
    }
}
