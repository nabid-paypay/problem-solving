package stack;
import java.util.*;

public class DecodeString {
    String result = "";
    public String decodeString(String s) {
        String temp = "";
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars){
            if(c == ']') {
                buildString(stack, this.result);
            }
            else stack.push(c);
        }
        while (!stack.empty()){
            temp = stack.pop() + temp;
        }

        return temp;
    }

    private void buildString(Stack<Character> stack, String result) {
        String temp = "", reverse = "";
        while (stack.peek() != '['){
            temp = stack.pop() + temp ;
        }
        stack.pop();

        int repeat = getRepeatCount(stack);
        temp = temp.repeat(repeat);

        char[] chars = temp.toCharArray();
        for (char c : chars){
            stack.push(c);
        }
    }

    private int getRepeatCount(Stack<Character> stack) {
        String temp = "";
        while (!stack.empty() && (stack.peek() >= 48 && stack.peek()<= 57 ))
            temp = stack.pop() + temp;
        return Integer.parseInt(temp);
    }

    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
        System.out.println("3[a]2[bc]: " + decodeString.decodeString("3[a]2[bc]"));
        System.out.println("3[a2[c]]: " + decodeString.decodeString("3[a2[c]]"));
        System.out.println("2[abc]3[cd]ef: " + decodeString.decodeString("2[abc]3[cd]ef"));
        System.out.println("abc3[cd]xyz: " + decodeString.decodeString("abc3[cd]xyz"));
        System.out.println("100[leetcode]: " + decodeString.decodeString("100[leetcode]"));
    }
}
