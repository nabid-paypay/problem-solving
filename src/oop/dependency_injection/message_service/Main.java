package oop.dependency_injection.message_service;
//https://www.journaldev.com/2394/java-dependency-injection-design-pattern-example-tutorial
//https://www.freecodecamp.org/news/a-quick-intro-to-dependency-injection-what-it-is-and-when-to-use-it-7578c84fa88f/
public class Main {

    /*
    * Entities must depend on abstractions, not on concretions.
    * It states that the high-level module must not depend on the low-level module,
    * but they should depend on abstractions.
    * */

    public static void main(String[] args) {
        String msg = "Hi";
        String email = "nabid@gmail.com";
        String phone = "01780171423";
        MessageServiceInjector injector;
        DIApp app;

        //Send email
        injector = new EmailServiceInjector();
        app = injector.getDIApp();
        app.processMsg(email, msg);

        //Send SMS
        injector = new SMSServiceInjector();
        app = injector.getDIApp();
        app.processMsg(phone, msg);
    }
}
