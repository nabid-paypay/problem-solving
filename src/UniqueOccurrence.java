import java.util.*;
import java.util.stream.Stream;

public class UniqueOccurrence {
    public static boolean uniqueOccurrences(int[] arr) {
        //boolean[] memo = new boolean[arr.length];
        //System.out.println(memo[0]);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        Collection<Integer> values =  map.values();
        Set<Integer> set = new HashSet<>(map.values());

        if(values.size() == set.size()) return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(uniqueOccurrences(new int[]{1,2,2,1,3}));
    }
}
