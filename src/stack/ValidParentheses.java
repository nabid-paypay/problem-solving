package stack;
import java.util.*;

public class ValidParentheses {
    public boolean isValid(String s) {
        if(s.isEmpty()) return true;
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(']', '[');
        map.put('}', '{');
        map.put(')', '(');
        char[] chars = s.toCharArray();
        for (char c : chars){
            if(map.containsValue(c)){
                stack.push(c);
            }
            else {
                if(!stack.empty()){
                    char ch = stack.pop();
                    if(ch != map.get(c)) return false;
                }
                else return false;

            }
        }
        return stack.empty();
    }
}
