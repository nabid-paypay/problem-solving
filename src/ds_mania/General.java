package ds_mania;
import java.util.*;

public class General {

    public int calPoints(String[] ops) {
        List<Integer> points = new ArrayList<>();

        for (String s : ops){
            if(s.equals("C")){
                points.remove(points.size()-1);
            }
            else if(s.equals("D")){
                int last = points.get(points.size()-1);
                points.add(last*2);
            }
            else if(s.equals("+")){
                int last = points.get(points.size()-1);
                int secondLast = points.get(points.size()-2);
                points.add(last + secondLast);
            }
            else{
                points.add(Integer.valueOf(s));
            }
        }

        return points.stream().mapToInt(point -> point).sum();
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph = paragraph.replaceAll("[^a-zA-Z ]", " ").toLowerCase();

        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));

        Map<String, Integer> count = new HashMap<>();
        //String[] words = paragraph.split(" ");

        for (String word : paragraph.split(" ")){
            if(!word.equals("")){
                count.put(word, count.getOrDefault(word, 0) +1);
            }
        }

        int max = Integer.MIN_VALUE;
        String ans = "";
        for (String key : count.keySet()){
            if(count.get(key)>max && !bannedSet.contains(key)){
                max = count.get(key);
                ans = key;
            }
        }
        return ans;
    }

    public int depthSum(List<NestedInteger> nestedList) {
        return depthSumHelper(nestedList, 1);
    }

    private int depthSumHelper(List<NestedInteger> nestedList, int depth) {
        int sum = 0;

        for (NestedInteger ni : nestedList){
            if(ni.isInteger()) sum+= ni.getInteger()*depth;
            else {
                sum+= depthSumHelper(ni.getList(), depth+1);
            }
        }
        return sum;
    }

    ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
    int [] nums;

    public void clean_deque(int i, int k) {
        // remove indexes of elements not from sliding window
        if (!deq.isEmpty() && deq.getFirst() == i - k)
            deq.removeFirst();

        // remove from deq indexes of all elements
        // which are smaller than current element nums[i]
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()])
            deq.removeLast();
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        // init deque and output
        this.nums = nums;
        int max_idx = 0;
        for (int i = 0; i < k; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            // compute max in nums[:k]
            if (nums[i] > nums[max_idx]) max_idx = i;
        }
        int [] output = new int[n - k + 1];
        output[0] = nums[max_idx];

        // build output
        for (int i  = k; i < n; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            output[i - k + 1] = nums[deq.getFirst()];
        }
        return output;
    }

    public static void main(String[] args) {
        General obj = new General();
        obj.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);

    }
}
