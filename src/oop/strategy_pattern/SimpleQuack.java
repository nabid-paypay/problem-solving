package oop.strategy_pattern;

// Concrete strategies implement the algorithm while following
// the base strategy interface. The interface makes them
// interchangeable in the context.
public class SimpleQuack implements  QuackBehaviour{
    @Override
    public void quack() {
        System.out.println("simple quack");
    }
}
