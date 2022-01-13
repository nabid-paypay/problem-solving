package oop.factory_method_pattern;

public class PlanFactory {
    public Plan getPlan(String plan){
        switch (plan){
            case "domestic" : return new DomesticPlan();
            case "industry" : return new IndustrialPlan();
            default: return null;
        }
    }
}
