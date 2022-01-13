package trie;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class LC_336_Palindrome_Pairs {
    public static class Trie{
        Trie[] next;
        int index;
        List<Integer> palins;

        public Trie() {
            this.next = new Trie[26];
            this.index = -1;
            palins = new ArrayList<>();
        }
    }
    public static boolean isPalindrome(String word, int i, int j){
        while (i<j){
            if(word.charAt(i++)!=word.charAt(j--)) return false;
        }
        return true;
    }

    public void add(Trie root, String word, int pos){
        for (int i = word.length()-1; i >=0 ; i--) {
            char ch = word.charAt(i);
            if(root.next[ch - 'a'] == null)
                root.next[ch - 'a'] = new Trie();

            if(isPalindrome(word, 0, i))
                root.palins.add(pos);

            root = root.next[ch - 'a'];
        }
        root.index = pos;
        root.palins.add(pos);
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            add(trie, words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            search(trie, words[i], i, ans);
        }

        return ans;
    }

    public  void search(Trie root, String word, int i, List<List<Integer>> ans) {
        String[] stringArray = {"Hey", "Hi", "Hello"};

        List<String> list = Arrays.asList(stringArray);
        int len = word.length();
        for (int j = 0; j < len && root != null; j++) {
            if (root.index >= 0 && i != root.index && isPalindrome(word, j, len - 1)) {
                ans.add(Arrays.asList(i, root.index));
            }
            char ch = word.charAt(j);
            root = root.next[ch - 'a'];
        }
        if (root != null && root.palins.size() > 0) { // assume 'xyxabc' is in trie, now try 'cba'
            for (int j : root.palins) {
                if (j != i) {
                    ans.add(Arrays.asList(new Integer[] {i, j}));
                }
            }
        }
    }

    public static void main(String[] args) {
        LC_336_Palindrome_Pairs trie = new LC_336_Palindrome_Pairs();
       // List<List<Integer>> result1 = trie.palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"});
        //
       // List<List<Integer>> result1 = trie.palindromePairs(new String[]{"xyxcba", "abc", "xcba"});
        List<List<Integer>> result1 = trie.palindromePairs(new String[]{"a", "aaa"});
        System.out.println(result1.toString());
    }
}
