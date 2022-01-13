package oop.rebirth.proxy.virtual_proxy;

public class ExpensiveObjectImpl implements ExpensiveObject{
    public ExpensiveObjectImpl() {
        loadHeavyConfig();
    }

    private void loadHeavyConfig() {
        System.out.println("loading heavy config");
    }

    @Override
    public void process() {
        System.out.println("process complete");
    }
}
