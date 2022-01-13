package oop.amazon_problems.dog_house;

public class DogAllotmentService {
    public boolean addDog(Dog dog){
        PlayGround kessel = PlayGroundFactory.getPlayGround("kessel");
        PlayGround smallPg = PlayGroundFactory.getPlayGround("small");

        if(dog.age >=1 && dog.age<=5){
            return smallPg.addDog(dog);
        }
        return false;
    }
}
