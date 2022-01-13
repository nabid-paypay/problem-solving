package ds_mania;
import java.util.*;

public class OrderedMap {
    public boolean isNStraightHand(int[] hand, int W) {
        int len = hand.length;
        if(W*W>len) return false;
        return true;
    }

    public boolean isPossibleDivide(int[] nums, int k) {
       // if(nums.length%k == 1) return false;

        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(map.keySet());

        while (!pq.isEmpty()){
            Set<Integer> set = new HashSet<>();
            if(pq.size()>=k){
                for (int i = 0; i < k; i++) {
                    int num = pq.poll();
                    if(!set.isEmpty() && !set.contains(num-1)) return false;
                    set.add(num);

                }
            }
            else return false;

            for (int num : set){
                map.put(num, map.get(num)-1);
                if(map.get(num)>0) pq.add(num);
            }
            set.clear();

        }
        // if()
        return pq.size() == 0;
    }

    public boolean isPossibleDividett(int[] nums, int k) {
        int len = nums.length;
        if(len % k != 0) return false;
        int n = len/k;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for(int num: nums){
            treeMap.put(num, treeMap.getOrDefault(num, 0) +1);
        }
        for(int x = 0; x < n; x++){
            int first = treeMap.firstKey();
            for(int i = 0; i < k; i++){
                if(treeMap.containsKey(first)){
                    treeMap.put(first, treeMap.get(first) - 1);
                    if(treeMap.get(first) == 0)
                        treeMap.remove(first);
                    first += 1;
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        OrderedMap om = new OrderedMap();
        System.out.println(om.isPossibleDivide(new int[]{1,2,3,3,4,4,5,6}, 4)); //t
        System.out.println(om.isPossibleDivide(new int[]{3,2,1,2,3,4,3,4,5,9,10,11},3 )); //t
        System.out.println(om.isPossibleDivide(new int[]{3,3,2,2,1,1}, 3)); //t
        System.out.println(om.isPossibleDivide(new int[]{1,2,3,4}, 3)); //f
        System.out.println(om.isPossibleDivide(new int[]{16, 21, 26, 35}, 4)); //f









    }
}
