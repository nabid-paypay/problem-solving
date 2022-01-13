package oop.decorator_pattern;

public class Main {
    /*
    * Decorator HAS a component and IS a component.
    * Decorator attach additional properties dynamically.
    * Wrapping a gift, putting it in a box, and wrapping the box.
    * Decorator pattern suggests giving the client the ability to specify whatever combination of "features" is desired.
    * Decorator supports recursive composition
    * It's worth noting that the Java i/o model is based on the decorator pattern
    * Looks like combination is the key here.
    * */
//https://www.codeproject.com/Articles/1068917/A-Problem-Solution-Approach-to-Design-Patterns-Ext

    public static void main(String[] args) {
        Beverage espresso = new Espresso();
        MilkDecorator milkDecoratorEspresso = new MilkDecorator(espresso);
        ChocolateDecorator chocolateDecoratorEspresso = new ChocolateDecorator(espresso);

        System.out.println("cost of espresso with milk: " + milkDecoratorEspresso.cost());
        System.out.println("cost of chocolate with milk: " + chocolateDecoratorEspresso.cost());

        Mocha mocha = new Mocha();
        MilkDecorator milkDecoratorMocha = new MilkDecorator(mocha);
        ChocolateDecorator chocolateDecoratorMocha = new ChocolateDecorator(mocha);

        System.out.println("cost of Mocha with milk: " + milkDecoratorMocha.cost());
        System.out.println("cost of Mocha with milk: " + chocolateDecoratorMocha.cost());
    }
}
