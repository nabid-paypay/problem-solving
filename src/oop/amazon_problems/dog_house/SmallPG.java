package oop.amazon_problems.dog_house;

import java.util.ArrayList;
import java.util.List;

public class SmallPG extends PlayGround{
    List<Dog> dogs = new ArrayList<>();

    public SmallPG(int size) {
        this.size = size;
    }

    @Override
    public boolean addDog(Dog dog) {
        if( dogs.size() >= this.size){
            return false;
        }
        dogs.add(dog);
        return true;
    }

    @Override
    public int currentCapacity() {
        return this.size - dogs.size();
    }

    @Override
    public int getCapacity() {
        return this.size;
    }
}
