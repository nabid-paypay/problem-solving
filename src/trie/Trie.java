package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

public class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    public TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.containsKey(ch)) {
                node = node.get(ch);
            } else return null;
        }

        return node;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    public boolean searchWithDot(String word) {
        return searchWithDotHelper(word, root);
    }

    public boolean searchWithDotHelper(String word, TrieNode node) {
        //TrieNode node = root;
        for (int i = 0; i < word.length() && node != null; i++) {
            char ch = word.charAt(i);
            if (ch == '.') {
                TrieNode temp = node;
                for (int j = 0; j < 26; j++) {
                    char c = (char) (97 + j);
                    node = temp.get(c);
                    if (searchWithDotHelper(word.substring(i + 1, word.length()), node)) {
                        return true;
                    }
                }
            } else {
                if (node.containsKey(ch)) {
                    node = node.get(ch);
                } else return false;
            }
        }

        return node != null && node.isEnd();
    }

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        Map<String, String> wordsMap = new HashMap<>();
        for (String word : words) {
            wordsMap.put(word, word);
            trie.insert(word);
        }
        int rows = board.length, cols = board[0].length;
        Set<String> result = new HashSet<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String str = String.valueOf(board[i][j]);
                //if(!visited[i][j])
                boolean[][] visited = new boolean[rows][cols];
                dfsFindWords(board, wordsMap, result, "", i, j, visited, trie);

            }
        }
        return new ArrayList<>(result);
    }

    private void dfsFindWords(char[][] board, Map<String, String> wordsMap, Set<String> result,
                              String genStr, int i, int j, boolean[][] visited, Trie trie) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) return;
        genStr = genStr + board[i][j];
        if (!trie.startsWith(genStr)) {
            return;
        }
        //if(wordsMap.containsKey(genStr))
        if (trie.search(genStr))
            result.add(genStr);
        visited[i][j] = true;
        dfsFindWords(board, wordsMap, result, genStr, i + 1, j, visited, trie);
        dfsFindWords(board, wordsMap, result, genStr, i - 1, j, visited, trie);
        dfsFindWords(board, wordsMap, result, genStr, i, j + 1, visited, trie);
        dfsFindWords(board, wordsMap, result, genStr, i, j - 1, visited, trie);
        visited[i][j] = false;
    }

    public boolean isPalindrome(String word) {
        int i = 0, j = word.length() - 1;
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) return false;
        }
        return true;
    }


    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i != j) {
                    if (isPalindrome(words[i] + words[j])) result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    public String longestWord(String[] words) {
        TrieNode2 node = new TrieNode2();
        TrieNode2 root = node;
        for (String word : words) {
            node = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (node.next[ch - 'a'] == null) {
                    node.next[ch - 'a'] = new TrieNode2();
                }
                node = node.next[ch - 'a'];
            }
            node.isEnd = true;
            node.word = word;
        }
        String ans = "";
        TrieNode2 node2 = root;

        Stack<TrieNode2> stack = new Stack<>();
        stack.push(node2);

        while (!stack.isEmpty()) {
            TrieNode2 cur = stack.pop();
            if (cur.isEnd || cur == root) {
                if (cur != root) {
                    if (cur.word.length() > ans.length() || cur.word.length() == ans.length() &&
                            cur.word.compareTo(ans) < 0) {
                        ans = cur.word;
                    }
                }

                for (int i = 97; i <= 122; i++) {
                    char ch = (char) i;
                    if (cur.next[ch - 'a'] != null && cur.next[ch - 'a'].isEnd) stack.push(cur.next[ch - 'a']);
                }
            }
        }

        return ans;
    }


    public class TrieNode2 {
        TrieNode2[] next = new TrieNode2[26];
        boolean isEnd = false;
        String word;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        TrieNode2 node = new TrieNode2();
        TrieNode2 root = node;

        for (String rootWord : dictionary) {
            node = root;
            for (int i = 0; i < rootWord.length(); i++) {
                char ch = rootWord.charAt(i);
                if (node.next[ch - 'a'] == null) {
                    node.next[ch - 'a'] = new TrieNode2();
                }
                node = node.next[ch - 'a'];
            }
            node.isEnd = true;
            node.word = rootWord;
        }

        String[] words = sentence.split(" ");
        StringJoiner sj = new StringJoiner(" ");

        for (String word : words) {
            TrieNode2 curr = root;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (curr.next[ch-'a']==null) break;
                curr = curr.next[ch-'a'];
                if(curr.isEnd) break;

            }

            if (curr != null && curr.isEnd) sj.add(curr.word);
            else sj.add(word);
        }

        return sj.toString();
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words){
            counts.put(word, counts.getOrDefault(word, 0) +1);
        }

        //PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> counts.get(b) - counts.get(a));

        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int res = counts.get(o2) - counts.get(o1);
                if(res == 0){
                    res = o1.compareTo(o2);
                }
                return res;
            }
        });

        pq.addAll(counts.keySet());

        List<String> result = new ArrayList<>();
        for (int i = 0; i <k ; i++) {
            result.add(pq.remove());
        }
       // Collections.sort(result);
        return result;
    }

    public List<String> topKFrequent2(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<String>(
                (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                        w2.compareTo(w1) : count.get(w1) - count.get(w2) );

        for (String word: count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }

        List<String> ans = new ArrayList();
        while (!heap.isEmpty()) ans.add(heap.poll());
        Collections.reverse(ans);
        return ans;
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
    /*    trie.findWords(new char[][]{
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}}, new String[]{"oath","pea","eat","rain"});*/

      /*  trie.findWords(new char[][]{
                {'a'},
                {'a'},
                }, new String[]{"aaa"});

        trie.findWords(new char[][]{
                {'a','b','c'},
                {'a','e','d'},
                {'a','f','g'}
                }, new String[]{"eaabcdgfa"});
        System.out.println(trie.isPalindrome(""));*/

        List<String> lala = trie.topKFrequent2(new String[]{"i", "love", "leetcode", "i", "love", "coding"},
                2);
        lala.forEach(System.out::println);


    }
}
