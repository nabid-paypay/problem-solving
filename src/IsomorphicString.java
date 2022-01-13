import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IsomorphicString {

    public static boolean isIsomorphic(String s, String t) {
        if(s.length()!=t.length()) return false;
        char[] sArray  = s.toCharArray();
        char[] tArray = t.toCharArray();

        Map<Character, Character> sMap = new HashMap<>();
        Set<Character> set  = new HashSet<>();
        for (int i = 0; i < s.length()  ; i++) {
            if(sMap.get(sArray[i])!=null){
                char c = sMap.get(sArray[i]);
                if(c!= tArray[i] ) return false;
            }
            if(!sMap.containsKey(s.charAt(i)) && set.contains(t.charAt(i)))
                return false;
            sMap.put(sArray[i], tArray[i]);
            set.add(t.charAt(i));

        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphic("ab", "aa")); //false
    }
}
