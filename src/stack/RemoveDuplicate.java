package stack;
import java.util.*;
public class RemoveDuplicate {
    //TODO; not done
    public String removeDuplicates(String s, int k) {
       Map<Character, Integer> map = new HashMap<>();
       Stack<Character> stack = new Stack<>();

       for (char c : s.toCharArray()){
           map.put(c, map.getOrDefault(c, 0) + 1);

       }

       for (char c: s.toCharArray()){
           if(stack.empty()){
               stack.push(c);
           }
           else {
               char top = stack.peek();
               if(top == c){
                   if(map.get(top) == k){
                        stack.pop();
                   }
                   else {
                      // map.put(c, map.get(c) -1);
                   }

               }
               else {
                   stack.push(c);
               }
           }
       }
       stack.stream().forEach(System.out::println);
        return "";
    }


    public static void main(String[] args) {
        RemoveDuplicate obj = new RemoveDuplicate();
        System.out.println(obj.removeDuplicates("pbbcggttciiippooaais", 2));
    }
}
