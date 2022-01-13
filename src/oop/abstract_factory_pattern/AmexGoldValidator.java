package oop.abstract_factory_pattern;

public class AmexGoldValidator implements  Validator{
    @Override
    public boolean isValid(CreditCard creditCard) {
        return false;
    }
}
