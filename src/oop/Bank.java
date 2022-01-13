package oop;

public class Bank extends AbstractTest{
    public Bank(String name) {
        super(name);
    }

    @Override
    public boolean setPass(String pass) {
        return false;
    }

    public static void main(String[] args) {
        Bank bank = new Bank("");
       // bank.setPass();
    }
}
