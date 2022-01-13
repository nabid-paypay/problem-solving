package oop.abstract_factory_pattern;

public class VisaPlatinumValidator implements Validator{
    @Override
    public boolean isValid(CreditCard creditCard) {
        return true;
    }
}
