package ds_mania;
import java.util.*;

public class LFUCache {
    private Map<Integer, Integer> cache = new HashMap<>();
    private Map<Integer, Integer> keyCount = new HashMap<>();
    private Map<Integer, LinkedHashSet<Integer>> freqMapAgainstSet = new HashMap<>();
    private int min, capacity, bla;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        min = -1;
    }

    public int get(int key) {
        if(!cache.containsKey(key)) return -1;

        int count = keyCount.get(key);
        freqMapAgainstSet.get(count).remove(key);  // remove key from current count (since we will inc count)

        if(count == min && freqMapAgainstSet.get(count).size() == 0) min++;

        putCount(key, count+1);
        return cache.get(key);
    }

    private void putCount(int key, int count) {
        keyCount.put(key, count);
        freqMapAgainstSet.putIfAbsent(count, new LinkedHashSet<>());
        freqMapAgainstSet.get(count).add(key);
    }

    public void put(int key, int value) {
        if(capacity<=0) return;

        if(cache.containsKey(key)){
            cache.put(key, value);
            get(key); // update key's count
            return;
        }

        if(cache.size()>=capacity){
            evict(freqMapAgainstSet.get(min).iterator().next());
        }

        min = 1; //so important
        putCount(key, min);
        cache.put(key, value);
    }

    private void evict(int key) {
        freqMapAgainstSet.get(min).remove(key);
        cache.remove(key);
        keyCount.remove(key);
    }

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(3);
        System.out.println(lfu.bla);
        lfu.put(1, 1);
        lfu.put(2, 2);
        lfu.put(3, 3);

        lfu.get(1);
        lfu.get(3);

        lfu.put(4, 4);

    }
}
