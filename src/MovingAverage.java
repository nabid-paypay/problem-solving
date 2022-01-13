
import java.util.*;

class MovingAverage {

    /** Initialize your data structure here. */
    int sum;
    int count;
    LinkedList<Integer> list;
    int size;
    public MovingAverage(int size) {
        list = new LinkedList<>();
        this.size = size;
    }

    public double next(int val) {
        sum+=val;
        count++;
        list.addLast(val);
        if(count > size){
            sum -= list.removeFirst();
            //count--;
        }
        return count<size ? 1.0*sum/count : 1.0*sum/size;
    }

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(3);
        System.out.println(movingAverage.next(1)); // return 1.0 = 1 / 1
        System.out.println(movingAverage.next(10)); // return 5.5 = (1 + 10) / 2
        System.out.println(movingAverage.next(3)); // return 4.66667 = (1 + 10 + 3) / 3
        System.out.println(movingAverage.next(5)); // return 6.0 = (10 + 3 + 5) / 3
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */