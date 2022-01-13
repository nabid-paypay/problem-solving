package oop.observer_pattern.chat_room;

public class Main {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();
        User userA = new User(chatRoom, "userA");
        User userB = new User(chatRoom, "userB");
        chatRoom.add(userA);
        chatRoom.add(userB);
        chatRoom.addMsg(userA, "hello");
        System.out.println();
        chatRoom.addMsg(userB, "hi");
    }
}
