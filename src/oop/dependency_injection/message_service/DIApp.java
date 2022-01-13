package oop.dependency_injection.message_service;

public class DIApp {
    private MessageService messageService;

    public DIApp(MessageService messageService) {
        this.messageService = messageService;
    }

    public void processMsg(String receiver, String msg){
        messageService.sendMessage(receiver, msg);
    }
}
