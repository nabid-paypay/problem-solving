package stack;
import java.util.*;

public class ReverseSubstringsBetweenEachPair {
    String result = "";
    public String reverseParentheses(String s) {

        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();
        for (char c : chars){
            if(c == ')') buildString(stack);
            else stack.push(c);
        }
        //brilliant
        while (!stack.isEmpty()){
            this.result = stack.pop() + this.result;
        }
        return this.result;
    }

    private void buildString(Stack<Character> stack) {
        while (stack.peek()!= '('){
            this.result += stack.pop();
        }
        stack.pop();
        for (int i = 0; i < this.result.length(); i++) {
            stack.push(this.result.charAt(i));
        }
        this.result = "";
    }


    public static void main(String[] args) {
        ReverseSubstringsBetweenEachPair obj = new ReverseSubstringsBetweenEachPair();
        System.out.println(obj.reverseParentheses("(u(love)i)"));
    }
}
