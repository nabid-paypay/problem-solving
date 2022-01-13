package ds_mania;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomizedSet {
    Map<Integer, Integer> map = new HashMap<>();
    List<Integer> list = new ArrayList<>();
    public RandomizedSet() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;
        list.add(val);
        map.put(val, list.size()-1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;

        int listLast = list.size()-1;
        int lastElem = list.get(listLast);
        int idx = map.get(val);


        list.set(idx, lastElem);
        list.remove(listLast);

        map.put(lastElem, idx);
        map.remove(val);

        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, list.size());
        return list.get(randomNum);
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
        randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
        randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
        randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
        randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
        randomizedSet.insert(2); // 2 was already in the set, so return false.
        randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.

        randomizedSet.insert(4);
        randomizedSet.insert(5);
        randomizedSet.remove(2);
        randomizedSet.remove(5);
        randomizedSet.remove(4);

  /*      List<Integer> list = new ArrayList<>();
        list.add(0, 2);
        list.remove(0);
        list.add(0, 5);
        System.out.println(list.get(0));*/


    }
}
