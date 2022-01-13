package oop.observer_pattern.chat_room;

public abstract class Observer {
    abstract void showMsg(Observer observer);
    String name;

    public String getName(){
        return name;
    }
}
