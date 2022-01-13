package oop.observer_pattern;

public interface Observable {
    void add(Observer observer);
    void delete(Observer observer);
    void _notify();
}
