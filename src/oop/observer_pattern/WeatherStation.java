package oop.observer_pattern;
import java.util.*;

public class WeatherStation implements Observable{
    List<Observer> observers = new ArrayList<>();
    @Override
    public void add(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void delete(Observer observer) {
        observers.remove(observer);
        //Arrays.copyo
    }

    @Override
    public void _notify() {
        observers.forEach(observer -> observer.update() );
    }

    public String getTemp(){
        return "100 degree";
    }
}
