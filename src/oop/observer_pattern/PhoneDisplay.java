package oop.observer_pattern;

public class PhoneDisplay implements Observer{
    private WeatherStation weatherStation;
    public PhoneDisplay(WeatherStation weatherStation) {
        this.weatherStation = weatherStation;
    }

    @Override
    public void update() {
        System.out.println("Phone: " + weatherStation.getTemp());
    }
}
