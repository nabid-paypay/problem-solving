package oop.factory_method_pattern;

public class DomesticPlan extends Plan{

    @Override
    public void calculateBill(int units) {
        System.out.println(3.50*units);
    }
}
