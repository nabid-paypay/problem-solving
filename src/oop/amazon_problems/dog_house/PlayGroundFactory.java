package oop.amazon_problems.dog_house;

public class PlayGroundFactory {
    public static PlayGround getPlayGround(String pg){
        switch (pg){
            case "kessel" : return new Kessel(20);
            case "small" : return new SmallPG(30);
            default: return null;
        }
    }
}
