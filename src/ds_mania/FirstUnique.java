package ds_mania;
import java.util.*;

class FirstUnique {
    Map<Integer, Integer> map = new HashMap<>();
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> map.get(a)));

    public FirstUnique(int[] nums) {
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) +1);
            pq.add(n);
        }
        //pq.addAll(map.keySet());
    }

    public int showFirstUnique() {
        int top = pq.peek();
        if(map.get(top) == 1){
            return top;
        }
        return -1;
    }

    public void add(int value) {
        pq.remove(value);
        map.put(value, map.getOrDefault(value, 0) +1);
        pq.add(value);
    }

    public static void main(String[] args) {
        FirstUnique firstUnique = new FirstUnique(new int[]{2,3,5});
        System.out.println(firstUnique.showFirstUnique());
        firstUnique.add(5);
        System.out.println(firstUnique.showFirstUnique());
        firstUnique.add(2);
        System.out.println(firstUnique.showFirstUnique());
        firstUnique.add(3);
        System.out.println(firstUnique.showFirstUnique());
    }
}
