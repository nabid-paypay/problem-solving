package oop;

import oop.strategy_pattern.Duck;
import oop.strategy_pattern.QuackBehaviour;

public class Test extends Duck {
    //protected int val = 0;
    private int dd = val;

      protected void over(){
        System.out.println("test4");
    }

    public static void main(String[] args) {
        Test te = new Test();
        te.over();

    }
}

