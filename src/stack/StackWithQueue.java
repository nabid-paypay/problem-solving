package stack;
import java.util.*;

public class StackWithQueue {
    /** Initialize your data structure here. */
    Queue<Integer> queue;
    int top;
    public StackWithQueue() {
        this.queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        this.queue.add(x);
        this.top = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int topElem = this.top;
        this.queue.remove(topElem);
        if(!queue.isEmpty()) this.top = getTop();
        return topElem;
    }

    private int getTop() {
        Integer[] array = new Integer[this.queue.size()];
        array = this.queue.toArray(array);
        return array[ this.queue.size() -1 ];
    }

    /** Get the top element. */
    public int top() {
        return this.top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return this.queue.isEmpty();
    }

    public static void main(String[] args) {
        StackWithQueue stack = new StackWithQueue();

        stack.push(1);
        System.out.println(stack.pop());   // returns 1
        stack.empty(); // returns false
    }
}
