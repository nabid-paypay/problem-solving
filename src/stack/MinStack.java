package stack;
import java.util.*;

public class MinStack {
    List<Long> list;
    int size;
    Long min ;
    public MinStack() {
        this.list = new ArrayList<>();
        this.size = 0;
    }

    public void push(int x) {
        if(list.isEmpty()) {
            this.min = Long.valueOf(x);
            this.list.add(Long.valueOf(x));
        }
        else if(x>= this.min) this.list.add(Long.valueOf(x));
        else {
            this.list.add( 3*Long.valueOf(x) - this.min );
            this.min = Long.valueOf(x);
        }
        this.size++;
    }

    public void pop() {
        Long elem = list.get(this.size-1);
        if(elem < min) {
            min = 3 * min - elem;
        }
        this.list.remove(this.size-1);
        this.size--;
    }

    public int top() {
        Long elem =  this.list.get(this.size-1);
        if(elem>=min) return elem.intValue();
        else return min.intValue();
    }

    public int getMin() {
        return this.min.intValue();
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        /*["MinStack","push","push","push","top","pop","getMin","pop","getMin",
        "pop","push","top","getMin","push","top","getMin","pop","getMin"]
    [[],[2147483646],[2147483646],[2147483647],[],[],[],[]
    ,[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]*/
        /*stack.push(2147483646);
        stack.push(2147483646);
        stack.push(2147483647);
        System.out.println("top: " + stack.top());
        stack.pop();
        System.out.println("getMin: " + stack.getMin());
        stack.pop();
        System.out.println("getMin: " + stack.getMin());
        stack.pop();*/
        stack.push(2147483647);
        System.out.println("top: " + stack.top());
        System.out.println("getMin: " + stack.getMin());
        stack.push(-2147483648);
        System.out.println("top: " + stack.top());
        int a = 2 * -2147483648;
        System.out.println(a);
        /*System.out.println("getMin: " + stack.getMin());
        stack.pop();
        System.out.println("getMin: " + stack.getMin());*/

       /* [null,null,null,null,2147483647,null,2147483646,null
       ,2147483646,null,null,2147483647,2147483647,null,
       -2147483648,-2147483648,null,2147483647]

*/    }
}
