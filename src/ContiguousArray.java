import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ContiguousArray {
    public static int findMaxLength(int[] nums) {
        int maxlen = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); //90 percent people fails because of this line. UNDERSTAND IT PERFECTLY.
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 0 ? -1 : 1);
            if(map.containsKey(count)){
                maxlen = Math.max(maxlen, i - map.get(count));
            }
            else {
                map.put(count, i);
            }
        }
        return maxlen;
    }

    public static void main(String[] args) {
        System.out.println(findMaxLength(new int[]{0, 1, 0, 1}));
    }
}
