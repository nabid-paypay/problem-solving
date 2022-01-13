package oop.observer_pattern;

public class LcdDisplay implements Observer{
    private WeatherStation weatherStation;
    public LcdDisplay(WeatherStation weatherStation) {
        this.weatherStation = weatherStation;
    }

    @Override
    public void update() {
        System.out.println("LCD: " + weatherStation.getTemp());
    }
}
