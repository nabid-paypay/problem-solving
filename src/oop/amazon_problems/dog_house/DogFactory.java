package oop.amazon_problems.dog_house;

public class DogFactory {
    public static Dog getDog(int age){
        if(age>=1 && age<=5){
            return new SmallDog(age);
        }
        return null;
    }
}
