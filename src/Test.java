import java.util.*;
public class Test {
    private String blah;

    public String getBlah() {
        return blah;
    }

    public void setBlah(String blah) {
        this.blah = blah;
    }

    public static void main(String[] args) {
      Test test = new Test();
      test.setBlah(null);
      System.out.println(test.getBlah());
    }
}
