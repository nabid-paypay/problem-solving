package oop.factory_method_pattern;

public class GenerateBill {
    public static void main(String[] args) {
        PlanFactory planFactory = new PlanFactory();
        Plan domesticPlan = planFactory.getPlan("domestic");
        Plan industryPlan = planFactory.getPlan("industry");

        domesticPlan.calculateBill(10);
        industryPlan.calculateBill(20);
    }
}
