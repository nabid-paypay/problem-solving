package string_manipulation;

import java.util.*;

public class StringManipulation {
    public void reverseString(char[] s) {
        int mid = s.length / 2;
        int last = s.length - 1;
        for (int i = 0; i < mid; i++) {
            char temp = s[i];
            s[i] = s[last];
            s[last] = temp;
            last--;
        }
        //System.out.println(s);
    }

    public String reverseWords(String s) {
        s = s.trim();
        String[] array = s.split(" +");


        int mid = array.length / 2;
        int last = array.length - 1;
        for (int i = 0; i < mid; i++) {
            String temp = array[i];
            array[i] = array[last];
            array[last] = temp;
            last--;
        }

        System.out.println(Arrays.toString(array));

        String result = "";
        for (int i = 0; i < array.length; i++) {
            if (!array[i].equals("")) result = result + array[i] + " ";
        }

        return result.substring(0, result.length() - 1);
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        if(n==0) return 0;
        if(n==1) return 1;
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(chars[j])) {
                i = Math.max(map.get(chars[j]), i);

            }
            ans = Math.max(ans, j - i +1);
            map.put(chars[j], j+1);
        }
        return ans;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;

    }

    public String lcpHorizontalScanning(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            int k = 0;
            String res = "";
            while (k < str.length() && k < prefix.length() && str.charAt(k) == prefix.charAt(k)) {
                res += prefix.charAt(k++);
            }
            if (res.isEmpty()) return "";
            prefix = res;
        }
        return prefix;
    }

    public String minWindow(String s, String t) {
        if(t.length() == 0 || s.length() ==0) return "";
        Map<Character, Integer> tMap = new HashMap<>();
        for (char ch : t.toCharArray()){
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
        }
        int requiredSize = tMap.size();
        int formed = 0;

        int l=0, r = 0;
        Map<Character, Integer> windowCount = new HashMap<>();
        int[] ans = new int[]{-1, 0, 0};

        while (l<=r && r<s.length()){
            char ch = s.charAt(r);
            windowCount.put(ch, windowCount.getOrDefault(ch, 0) + 1);

            if(tMap.containsKey(ch) && windowCount.get(ch).equals(tMap.get(ch))){
                formed++;
            }

            while (l<=r && formed == requiredSize){
                if(ans[0] == -1 || r - l + 1 < ans[0]){
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }
                ch = s.charAt(l);
                windowCount.put(ch, windowCount.get(ch)-1);

                if(tMap.containsKey(ch) && windowCount.get(ch)<tMap.get(ch)){
                    formed--;
                }
                l++;
            }
            r++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }



    public String minWindow2(String s, String t) {

        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        // Dictionary which keeps a count of all the unique characters in t.
        Map<Character, Integer> dictT = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }

        // Number of unique characters in t, which need to be present in the desired window.
        int required = dictT.size();

        // Left and Right pointer
        int l = 0, r = 0;

        // formed is used to keep track of how many unique characters in t
        // are present in the current window in its desired frequency.
        // e.g. if t is "AABC" then the window must have two A's, one B and one C.
        // Thus formed would be = 3 when all these conditions are met.
        int formed = 0;

        // Dictionary which keeps a count of all the unique characters in the current window.
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();

        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};

        while (r < s.length()) {
            // Add one character from the right to the window
            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);

            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count by 1.
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = s.charAt(l);
                // Save the smallest window until now.
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }

                // The character at the position pointed by the
                // `Left` pointer is no longer a part of the window.
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }

                // Move the left pointer ahead, this would help to look for a new window.
                l++;
            }

            // Keep expanding the window once we are done contracting.
            r++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

    public int maxLengthBetweenEqualCharacters(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int result = -1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(map.containsKey(ch)){
                int diff = i - map.get(ch)-1;
                result = Math.max(result, diff);
            }
            else {
                map.put(ch, i);
            }
        }
        return result;
    }

    public int lengthOfLongestSubstringList(String s, int k) {
        int n = s.length(), ans = 0;
        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        for (int j = 0, lastSeen = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                lastSeen = Math.max(map.get(s.charAt(j)), lastSeen);
            }
           // ans = Math.max(ans, j - lastSeen + 1);
            if(j - lastSeen + 1 == k && !set.contains(s.substring(lastSeen, j+1))) {
                set.add(s.substring(lastSeen, j+1));
                list.add(s.substring(lastSeen, j+1));
                lastSeen++;
            }
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }


    public static void main(String[] args) {
        StringManipulation obj = new StringManipulation();
        obj.longestCommonPrefix(new String[]{"flow","flower","flight"});


    }
}
