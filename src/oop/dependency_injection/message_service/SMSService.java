package oop.dependency_injection.message_service;

public class SMSService implements MessageService{
    @Override
    public void sendMessage(String receiver, String msg) {
        System.out.println(receiver + " : " + msg);
    }
}
