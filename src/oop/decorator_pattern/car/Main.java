package oop.decorator_pattern.car;

public class Main {
    public static void main(String[] args) {
        Car engineCar = new EngineCar();
        Car electricCar = new ElectricCar();

        System.out.println("engine:" + engineCar.desc());
        System.out.println("electric:" + electricCar.desc());

        Car greenEngineCar = new AddOnCar(engineCar, "green", true, true);
        System.out.println(greenEngineCar.desc());

        Car blueElectricCar = new AddOnCar(engineCar, "blue", false, true);
        System.out.println(blueElectricCar.desc());
    }
}
