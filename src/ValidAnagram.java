import java.util.ArrayList;
import java.util.List;

public class ValidAnagram {

    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;

        List<Character> sList = new ArrayList<>();
        List<Character> tList = new ArrayList<>();
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            sList.add(sArray[i]);
            tList.add(tArray[i]);
        }


        for (char ch: sList) {
            if(!tList.contains(ch)) return false;
            tList.remove(Character.valueOf(ch));
        }
        return true;
    }

    public static void main(String[] args) {
        int[] counter = new int[26];
        System.out.println(counter[0]);
        System.out.println(isAnagram("aacc", "ccac"));
    }
}
