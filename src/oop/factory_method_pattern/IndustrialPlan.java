package oop.factory_method_pattern;

public class IndustrialPlan extends Plan{

    @Override
    public void calculateBill(int units) {
        System.out.println(10.50*units);
    }
}
