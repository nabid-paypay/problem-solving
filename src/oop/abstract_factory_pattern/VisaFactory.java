package oop.abstract_factory_pattern;

import java.util.Objects;

public class VisaFactory extends CreditCardFactory {
    @Override
    public CreditCard getCreditCard(CardType cardType) {
        switch (cardType){
            case GOLD : return new VisaGoldCreditCard();
            case PLATINUM: return new VisaPlatinumCard();
            default: return null;
        }
    }

    @Override
    public Validator getValidator(CardType cardType) {
        switch (cardType){
            case PLATINUM : return new VisaPlatinumValidator();
            case GOLD :  return new VisaGoldValidator();
            default: return Objects::nonNull;
        }
    }
}
