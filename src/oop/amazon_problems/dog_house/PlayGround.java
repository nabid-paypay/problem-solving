package oop.amazon_problems.dog_house;

public abstract class PlayGround {
    protected int size;
    public abstract boolean addDog(Dog dog);
    public abstract int currentCapacity();
    public abstract int getCapacity();
}
