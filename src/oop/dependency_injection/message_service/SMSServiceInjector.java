package oop.dependency_injection.message_service;

public class SMSServiceInjector implements MessageServiceInjector{
    @Override
    public DIApp getDIApp() {
        return new DIApp(new SMSService());
    }
}
