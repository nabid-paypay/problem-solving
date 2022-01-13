package oop.decorator_pattern;

//extends addondecorator chilo. kno need dekhtesi na eytar.
public class ChocolateDecorator extends Beverage{ //beverage extends also works
    Beverage beverage; //important
    String ingredient = "chocolate";
    public ChocolateDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public int cost() {
        return this.beverage.cost() + 5; //say chocolate costs 5 dollar.
    }
}
