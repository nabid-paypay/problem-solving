package oop.rebirth.proxy.virtual_proxy;

public class ExpensiveObjectProxy implements ExpensiveObject{
    private static ExpensiveObject expensiveObject;
    @Override
    public void process() {
        if (expensiveObject == null){
            expensiveObject = new ExpensiveObjectImpl();
        }
        expensiveObject.process();
    }

    public static void main(String[] args) {
        ExpensiveObject expensiveObject = new ExpensiveObjectProxy();
        expensiveObject.process();
        System.out.println();
        expensiveObject.process();
    }
}
