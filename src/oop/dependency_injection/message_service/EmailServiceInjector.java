package oop.dependency_injection.message_service;

public class EmailServiceInjector implements MessageServiceInjector{
    @Override
    public DIApp getDIApp() {
        return new DIApp(new EmailService());
    }
}
