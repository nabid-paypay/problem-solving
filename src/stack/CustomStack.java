package stack;

import java.util.ArrayList;
import java.util.List;

public class CustomStack {
    List<Integer> list;
    int size;
    int maxSize;
    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        this.list = new ArrayList<>();
        this.size = 0;
    }

    public void push(int x) {
        if(this.size >= this.maxSize){}
        else {
            list.add(x);
            this.size++;
        }
    }

    public int pop() {
        if(list.isEmpty()) return -1;
        else {
           int elem =  this.list.get(this.size -1);
           this.list.remove(this.size -1);
           this.size--;
           return elem;
        }
    }

    public void increment(int k, int val) {
        int limit;

        if(k< list.size()) limit = k;
        else limit = list.size();

        for (int i = 0; i < limit; i++) {
            int elem = list.get(i);
            list.remove(i);
            list.add(i, elem + val);
        }
    }

    public static void main(String[] args) {
        CustomStack customStack = new CustomStack(3); // Stack is Empty []
        customStack.push(1);                          // stack becomes [1]
        customStack.push(2);                          // stack becomes [1, 2]
        System.out.println(customStack.pop());           // return 2 --> Return top of the stack 2, stack becomes [1]
        customStack.push(2);                          // stack becomes [1, 2]
        customStack.push(3);                          // stack becomes [1, 2, 3]
        customStack.push(4);                          // stack still [1, 2, 3], Don't add another elements as size is 4
        customStack.increment(5, 100);           // stack becomes [101, 102, 103]
        customStack.increment(2, 100);           // stack becomes [201, 202, 103]
        System.out.println(customStack.pop());            // return 103 --> Return top of the stack 103, stack becomes [201, 202]
        System.out.println(customStack.pop());             // return 202 --> Return top of the stack 102, stack becomes [201]
        System.out.println(customStack.pop());               // return 201 --> Return top of the stack 101, stack becomes []
        System.out.println(customStack.pop());               // return -1 --> Stack is empty return -1.
    }
}
