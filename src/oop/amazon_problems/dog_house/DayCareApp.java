package oop.amazon_problems.dog_house;

public class DayCareApp {
    public static void main(String[] args) {
        DogAllotmentService service = new DogAllotmentService();
        service.addDog(new SmallDog(4));

    }
}
