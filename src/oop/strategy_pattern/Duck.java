package oop.strategy_pattern;

  public class Duck {
    public Duck(){

    }
    //composition rather inheritance
    // The context maintains a reference to one of the strategy
    // objects. The context doesn't know the concrete class of a
    // strategy. It should work with all strategies via the
    // strategy interface.
    private QuackBehaviour quackBehaviour;
    protected int val = 4;

    // Usually the context accepts a strategy through the
    // constructor, and also provides a setter so that the
    // strategy can be switched at runtime.
    public Duck(QuackBehaviour quackBehaviour) {
        this.quackBehaviour = quackBehaviour;
    }

    // The context delegates some work to the strategy object
    // instead of implementing multiple versions of the
    // algorithm on its own.

    public void quack(){
        quackBehaviour.quack();
    }

    protected void over(){
        System.out.println("test");
    }
    /*
    * Strategy is a behavioral design pattern that lets you define a family of algorithms,
    * put each of them into a separate class, and make their objects interchangeable.
    * Enables selecting an algorithm at runtime.
    * pq can be said implementation of this pattern. we provide algorithm runtime.
    * */
    public static void main(String[] args) {
        ComplexQuack complexQuack = new ComplexQuack();
        SimpleQuack simpleQuack = new SimpleQuack();
        Duck complexDuck = new Duck(complexQuack);
        Duck simpleDuck = new Duck(simpleQuack);

        complexDuck.quack();
        simpleDuck.quack();
    }
}
