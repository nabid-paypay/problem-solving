package oop.observer_pattern.chat_room;

public class User extends Observer{
    ChatRoom chatRoom;

    public User(ChatRoom chatRoom, String name) {
        this.chatRoom = chatRoom;
        this.name = name;
    }

    @Override
    public void showMsg(Observer observer) {
       String s = chatRoom.getMsg(observer);
       System.out.println(s);
    }

    public String getName(){
        return this.name;
    }
}
