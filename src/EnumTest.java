import oop.abstract_factory_pattern.CreditCard;
import oop.abstract_factory_pattern.Validator;

import java.util.Arrays;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public enum EnumTest implements Validator {
    DO(1), DID(1), DONE(3);
    //private int level;
    EnumTest(int val){
        //this.level = val;
    }

    public int getLevel(){
        return 0;//this.level;
    }

    private static void tt(String... args){
        for (String arg : args) {
            //System.out.println(arg);
        }
        Arrays.stream(args).forEach(System.out::println);
        System.out.println(args.length);
    }

    @Override
    public boolean isValid(CreditCard creditCard) {
        return false;
    }

    public static void main(String[] args) {
        EnumTest enumTest = EnumTest.DID;
        System.out.println(enumTest.getLevel());
        tt("na", "gg", "gg", "gfgfg");

        Deque<Integer> dq = new ArrayDeque<>();
        dq.push(1);
        dq.push(2);
        dq.push(3);
        dq.push(4);

        System.out.println(dq.peek());
        System.out.println(dq.peekFirst());
        System.out.println(dq.peekLast());

        ReentrantLock lock = new ReentrantLock();
        System.out.println(lock.isLocked());
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        sb.append(2);
        System.out.println(sb.toString());

        int[][] arr = new int[2][2];
        int[][] arr2 = arr.clone();
    }
}
