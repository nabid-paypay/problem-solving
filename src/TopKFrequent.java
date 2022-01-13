import javax.print.attribute.HashAttributeSet;
import java.util.*;

public class TopKFrequent {
    /*public int[] topKFrequent(int[] nums, int k) {
        if(nums.length == k) return nums;

        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums){
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        Queue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(count::get));

        for(int n : count.keySet()){
            heap.add(n);
            if(heap.size() > k) heap.poll();
        }

        int[] top = new int[k];
        for (int i = k-1; i >=0 ; i--) {
            top[i] = heap.poll();
        }

        return top;
    }*/

    public static void main(String[] args) {
        System.out.println(Math.round(10.5));
    }
}
