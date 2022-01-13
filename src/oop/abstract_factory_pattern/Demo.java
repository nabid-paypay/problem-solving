package oop.abstract_factory_pattern;

public class Demo {

    public static void main(String[] args) {
        CreditCardFactory abstractFactory = CreditCardFactory.getCreditCardFactory(775);
        CreditCard card = abstractFactory.getCreditCard(CardType.GOLD);
        System.out.println(card.getClass());

        CreditCardFactory abstractFactory2 = CreditCardFactory.getCreditCardFactory(600);
        CreditCard card2 = abstractFactory2.getCreditCard(CardType.PLATINUM);
        System.out.println(card2.getClass());
    }
}
