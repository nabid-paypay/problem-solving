import java.util.HashMap;
import java.util.Map;

public class NumberOfGoodPairs {
    public static int numIdenticalPairs(int[] nums) {
        int[] counter = new int[100];
        int pair = 0;
        Map<Integer, Integer> map = new HashMap<>();
        /*for (int i = 0; i < nums.length -1; i++) {
            for (int j = i +1 ; j < nums.length; j++) {
                if(nums[i] == nums[j]) pair+=1;
            }
        }*/
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for (int val : map.values()){
            pair += (val * (val -1))/2;
        }
        return pair;
    }

    public static void main(String[] args) {
        System.out.println(numIdenticalPairs(new int[]{1, 1, 1, 1}));
    }
}
