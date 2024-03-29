package trie;

public class TrieNode {
    private TrieNode[] links;
    private boolean isEnd;

    public TrieNode() {
        this.links = new TrieNode[26];
    }

    public boolean containsKey(char ch){
        return links[ch - 'a'] != null;
    }

    public TrieNode get(char ch){
        return links[ch - 'a'];
    }

    public void put(char ch, TrieNode node){
        links[ch - 'a'] = node;
    }

    public void setEnd(){
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
