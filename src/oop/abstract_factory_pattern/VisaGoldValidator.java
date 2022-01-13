package oop.abstract_factory_pattern;

public class VisaGoldValidator implements Validator{
    @Override
    public boolean isValid(CreditCard creditCard) {
        return true;
    }
}
