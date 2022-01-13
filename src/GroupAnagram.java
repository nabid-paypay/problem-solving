import java.util.*;

public class GroupAnagram {

    public static List<List<String>> groupAnagram(String[] strings){
        if(strings.length == 0) return new ArrayList();
        Map<String, List> ans =  new HashMap<String, List>();

        for (String str: strings) {
            char[] ca = str.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            //System.out.println("key: " + key);
            if(!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(str);
        }
        return new ArrayList(ans.values());
    }

    public static void main(String[] args) {
        String[] strings = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagram(strings));
    }
}
