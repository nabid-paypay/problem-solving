package string_manipulation;
import java.util.*;

public class RearrangeString {
    public String reorganizeString(String S) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : S.toCharArray()){
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> counts.get(b) - counts.get(a));
        maxHeap.addAll(counts.keySet());

        StringBuilder sb = new StringBuilder();
        while (maxHeap.size() > 1){
            char current = maxHeap.remove();
            char next = maxHeap.remove();
            sb.append(current);
            sb.append(next);
            counts.put(current, counts.get(current) - 1);
            counts.put(next, counts.get(next) - 1);

            if(counts.get(current) > 0){
                maxHeap.add(current);
            }
            if(counts.get(next) > 0){
                maxHeap.add(next);
            }
        }

        if(!maxHeap.isEmpty()){
           char last =  maxHeap.remove();
           if(counts.get(last) > 1)
               return "";
           else sb.append(last);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RearrangeString obj = new RearrangeString();
        System.out.println(obj.reorganizeString("aaa"));
    }


}


