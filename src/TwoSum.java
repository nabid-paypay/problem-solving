import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {

        for (int i=0; i<nums.length-1; i++){
            for(int j=i+1; j<nums.length; j++){
                if(nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }

        }
        throw new IllegalArgumentException("");
    }
    public static int[] twoSumTwoPassHashImpl(int[] nums, int target){
        Map<Integer, Integer> map= new HashMap<>();

        for (int i=0; i<nums.length; i++){
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if(map.containsKey(complement) && map.get(complement)!= i){
                return new int[] {i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("");
    }

    public static int[] twoSumSinglePassHashImpl(int[] nums, int target){
        Map<Integer, Integer> map= new HashMap<>();

        for (int i=0; i<nums.length; i++){
            int complement = target - nums[i];
            if(map.containsKey(complement)){
                return new int[] { map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("");
    }

    public static int [] readNumsFromCommandLine() {

        Scanner s = new Scanner(System.in);

        int count = s.nextInt();
        s.nextLine(); // throw away the newline.

        int [] numbers = new int[count];
        Scanner numScanner = new Scanner(s.nextLine());
        for (int i = 0; i < count; i++) {
            if (numScanner.hasNextInt()) {
                numbers[i] = numScanner.nextInt();
            } else {
                System.out.println("You didn't provide enough numbers");
                break;
            }
        }

        return numbers;
    }

    public static void main(String[] args) {
        int[] inputs = readNumsFromCommandLine();
        int [] outputs = twoSum(inputs, 6);
        System.out.println(outputs[1]);
    }
}
