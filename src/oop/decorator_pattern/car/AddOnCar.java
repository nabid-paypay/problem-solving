package oop.decorator_pattern.car;

public class AddOnCar extends Car{
    String color;
    Car car; //important
    boolean isRoof;
    boolean fogLight;
    public AddOnCar(Car car, String color, boolean isRoof, boolean fogLight) {
        this.color = color;
        this.car = car;
        this.isRoof = isRoof;
        this.fogLight = fogLight;
    }

    @Override
    String desc() {
        return car.desc() + " " + color + (isRoof? " roof" : " no roof") +
                (fogLight? " fog light" : " no fog light");
    }

}
