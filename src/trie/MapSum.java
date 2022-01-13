package trie;
import java.util.*;

public class MapSum {
    public class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean isEnd = false;
        String word;
        int value = 0;
    }

    public MapSum() {

    }
    private TrieNode root = new TrieNode();
    private Map<String, Integer> map = new HashMap<>();

    public void insert(String key, int val) {
        TrieNode node = root;
        boolean exist = false;
        int prevVal = 0;
        if(map.containsKey(key)){
            exist = true;
            prevVal = map.get(key);
        }
        for (int i = 0; i < key.length(); i++) {

            char ch = key.charAt(i);
            if (node.next[ch - 'a'] == null) {
                node.next[ch - 'a'] = new TrieNode();
            }

            node = node.next[ch - 'a'];
            if(!exist) node.value += val;
            else {
                node.value = (node.value - prevVal + val);
            }
        }

        node.isEnd = true;
        node.word = key;
        map.put(key, val);
    }

    public int sum(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if(node.next[ch-'a']!=null){
                node = node.next[ch-'a'];
            }
            else return 0;
        }
        return node.value;
    }

    public boolean isWord(String word){
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(node.next[ch-'a']!=null){
                node = node.next[ch-'a'];
            }
            else return false;
        }
        return node.isEnd;
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();

        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));         // return 3 (apple = 3)
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("apple", 7);
        System.out.println(mapSum.sum("apple"));
        //System.out.println(mapSum.isWord("ff"));
    }

}
