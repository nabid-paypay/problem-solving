package oop.rebirth.adapter.anime;

public class AndroidAdapter implements Warrior{
    Android android;
    public AndroidAdapter(Android android) {
        this.android = android;
    }

    @Override
    public String attack() {
        return android.punch() + " " + android.kick();
    }

    public static void main(String[] args) {
        AndroidAdapter c17 = new AndroidAdapter(new Android());
        System.out.println(c17.attack());
        Saiyan saiyan = new Saiyan();
        System.out.println(saiyan.attack());
    }
}
