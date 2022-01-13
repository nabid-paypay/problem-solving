package oop.rebirth.adapter.plug;

public class SquarePegAdapter extends RoundPeg{
    private final SquarePeg squarePeg;

    public SquarePegAdapter(SquarePeg squarePeg) {
        this.squarePeg = squarePeg;
    }

    public int getRadius(){
        return (int) (squarePeg.getWidth() * Math.sqrt(2) / 2);
    }



    public static void main(String[] args) {
        RoundPeg roundPeg = new RoundPeg(8);
        RoundHole roundHole = new RoundHole(40);
        roundHole.fits(roundPeg);

        SquarePeg squarePeg = new SquarePeg(4);
        //roundHole.fits(squarePeg); incompatible

        SquarePegAdapter squarePegAdapter = new SquarePegAdapter(squarePeg);
        roundHole.fits(squarePegAdapter);

        String s = "1";
        String s1 = null;
        System.out.println(s.equals(s1));
    }

}
