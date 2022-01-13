package oop;

public abstract class AbstractTest {
    public AbstractTest(String name) {
        this.name = name;
    }

    public AbstractTest(int num) {
      this("");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    public abstract boolean setPass(String pass);

    public void AbstractTest(){
        System.out.println("ff");
    }

    public static void main(String[] args) {
        Bank bank = new Bank("");
        bank.AbstractTest();
    }

}
