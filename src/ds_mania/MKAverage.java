package ds_mania;
import java.util.*;

class MKAverage {
    TreeMap<Integer, Integer> top = new TreeMap<>(); //max numbers
    TreeMap<Integer, Integer> middle = new TreeMap<>();
    TreeMap<Integer, Integer> bottom = new TreeMap<>(); //min numbers
    Queue<Integer> q = new ArrayDeque<>();
    int m,k, middleSum;
    int countTop, countBottom;
    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
    }

    public void addElement(int num) {
        if(q.size() == m){
            int pop = q.poll();
            if(top.containsKey(pop)){
                countTop--;
                remove(top, pop);
            }
            else if(middle.containsKey(pop)){
                remove(middle, pop);
                middleSum-= pop;
            }
            else {
                remove(bottom, pop);
                countBottom--;
            }
        }
        //insert to middle first
        add(middle, num);
        middleSum += num;
        q.add(num);

        // move item from middle to top, to fill k slots
        while(countTop<k && !middle.isEmpty()){
            countTop++;
            middleSum -= middle.lastKey();
            add(top, remove(middle, middle.lastKey()));
        }
        // rebalance middle and top
        while(!middle.isEmpty() && !top.isEmpty() && top.firstKey() < middle.lastKey()){
            middleSum += top.firstKey();
            add(middle, remove(top, top.firstKey()));
            middleSum -= middle.lastKey();
            add(top, remove(middle, middle.lastKey()));
        }

        // move item from middle to bottom, to fill k slots
        while(countBottom<k && !middle.isEmpty()){
            countBottom++;
            middleSum -= middle.firstKey();
            add(bottom, remove(middle, middle.firstKey()));
        }

        // rebalance middle and bottom
        while(!middle.isEmpty() && !bottom.isEmpty() && bottom.lastKey() > middle.firstKey()){
            middleSum += bottom.lastKey();
            add(middle, remove(bottom, bottom.lastKey()));
            middleSum -= middle.firstKey();
            add(bottom, remove(middle, middle.firstKey()));
        }

    }

    private void add(TreeMap<Integer, Integer> tm, int num){
        tm.put(num, tm.getOrDefault(num, 0) +1);
    }

    private int remove(TreeMap<Integer, Integer> tm, int num){
        tm.put(num, tm.get(num) -1);
        if(tm.get(num) == 0){
            tm.remove(num);
        }

        return num;
    }

    public int calculateMKAverage() {
        return q.size() == m ?  middleSum / (m - 2*k) : -1;
    }

    public static void main(String[] args) {
        MKAverage obj = new MKAverage(3, 1);
        obj.addElement(3);        // current elements are [3]
        obj.addElement(1);        // current elements are [3,1]
        obj.calculateMKAverage(); // return -1, because m = 3 and only 2 elements exist.
        obj.addElement(10);       // current elements are [3,1,10]
        obj.calculateMKAverage(); // The last 3 elements are [3,1,10].
        // After removing smallest and largest 1 element the container will be [3].
        // The average of [3] equals 3/1 = 3, return 3
        obj.addElement(5);        // current elements are [3,1,10,5]
        obj.addElement(5);        // current elements are [3,1,10,5,5]
        obj.addElement(5);        // current elements are [3,1,10,5,5,5]
        obj.calculateMKAverage(); // The last 3 elements are [5,5,5].
        // After removing smallest and largest 1 element the container will be [5].
        // The average of [5] equals 5/1 = 5, return 5
    }
}
// 17612 74607 8272 33433 15456 64938 99741

/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */
