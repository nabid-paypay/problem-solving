package oop.dependency_injection.message_service;

public class EmailService implements MessageService{
    @Override
    public void sendMessage(String receiver, String msg) {
        System.out.println(receiver + " : " + msg);
    }
}
