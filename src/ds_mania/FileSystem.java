package ds_mania;
import java.util.*;

class FileSystem {
    TrieNode root;
    public class TrieNode{
        Map<String, TrieNode> next = new HashMap<>();
        Map<String, String> data = new HashMap<>();
    }

    public FileSystem() {
        root = new TrieNode();
        root.next.put("/", new TrieNode());
    }

    public List<String> ls(String path) {
        TrieNode curr = root;
        //TrieNode temp = curr.next.get("/");
        //Set<String> set = temp.next.keySet();
        if(path.equals("/")) return new ArrayList<>( curr.next.get("/").next.keySet());
        for (String str: path.split("/")) {
            if(str.equals("")){
                curr = curr.next.get("/");
            }
            else {
                curr = curr.next.get(str);
            }
        }

        return new ArrayList<>(curr.next.keySet());
    }

    public void mkdir(String path) {
        TrieNode curr = root;

        for (String str: path.split("/")) {
            if(str.equals("")){
                curr = curr.next.get("/");
            }
            else {
                curr.next.putIfAbsent(str, new TrieNode());
                curr = curr.next.get(str);
            }
        }
    }

    public void addContentToFile(String filePath, String content) {
        TrieNode curr = root;
        String[] arr = filePath.split("/");
        int len = arr.length;
        for (int i=0; i<len-1; i++) {
            if(arr[i].equals("")){
                curr = curr.next.get("/");
            }
            else {
                curr = curr.next.get(arr[i]);
            }
        }
        curr.data.put(arr[len-1], content);
    }

    public String readContentFromFile(String filePath) {
        TrieNode curr = root;
        String[] arr = filePath.split("/");
        int len = arr.length;
        for (int i=0; i<len-1; i++) {
            if(arr[i].equals("")){
                curr = curr.next.get("/");
            }
            else {
                curr = curr.next.get(arr[i]);
            }
        }
        return curr.data.get(arr[len-1]);
    }


/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
public String one(int num) {
    switch(num) {
        case 1: return "One";
        case 2: return "Two";
        case 3: return "Three";
        case 4: return "Four";
        case 5: return "Five";
        case 6: return "Six";
        case 7: return "Seven";
        case 8: return "Eight";
        case 9: return "Nine";
    }
    return "";
}

    public String twoLessThan20(int num) {
        switch(num) {
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19: return "Nineteen";
        }
        return "";
    }

    public String ten(int num) {
        switch(num) {
            case 2: return "Twenty";
            case 3: return "Thirty";
            case 4: return "Forty";
            case 5: return "Fifty";
            case 6: return "Sixty";
            case 7: return "Seventy";
            case 8: return "Eighty";
            case 9: return "Ninety";
        }
        return "";
    }
public static void main(String[] args) {
    FileSystem fs = new FileSystem();
    fs.ls("/");
    fs.mkdir("/a/b/c");
    fs.addContentToFile("/a/b/c/d", "hello world");
    fs.addContentToFile("/a/b/c/d", "second time");
    System.out.println(fs.readContentFromFile(fs.readContentFromFile("/a/b/c/d")));

}
}
