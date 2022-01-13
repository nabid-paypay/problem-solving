package oop.decorator_pattern;

public class MilkDecorator extends AddOnDecorator{ //beverage extends also works
    Beverage beverage;

    public MilkDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public int cost() {
        return this.beverage.cost() + 2; //say milk cost 2 dollar
    }
}
