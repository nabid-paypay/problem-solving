package oop.observer_pattern.chat_room;


public interface Observable {
    void add(Observer observer);
    void delete(Observer observer);
    void _notify();
}
