
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

public class TestBlah {
    Map<Character, Character> map = Map.of(')', '(',
            '}', '{',
            ']', '[');

    int[] nums;

    // m log m + n log m where m = length of forward and n = length of return
    public static List<List<Integer>> optimize(int maxTravelDist,
                                               int[][] forwardRoutes,
                                               int[][] returnRoutes) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for (int[] forwardRoute : forwardRoutes) {
            treeMap.put(forwardRoute[1], forwardRoute[0]);
        }

        List<List<Integer>> pairs = new LinkedList<>();

        int max = 0;
        for (int[] returnRoute : returnRoutes) {
            int key = returnRoute[0];
            int dist = returnRoute[1];

            if (maxTravelDist - dist <= 0) continue;

            int diff = maxTravelDist - dist;
            Integer closestDist = treeMap.floorKey(diff);
            if (closestDist == null) continue;

            if (closestDist + dist >= max) {
                if (closestDist + dist > max) {
                    max = closestDist + dist;
                    pairs.clear(); //Clean up
                }

                List<Integer> pair = new ArrayList<>();
                pair.add(treeMap.get(closestDist));
                pair.add(key);
                pairs.add(pair);
            }
        }

        return pairs;
    }



    public List<List<String>> groupStrings(String[] strings) {
        return new ArrayList<>(Arrays.stream(strings)
                .collect(Collectors.groupingBy(this::normalize))
                .values());
    }

    private List<Integer> normalize(String s) {
        return s.chars()
                .mapToObj(
                        c -> (c - s.charAt(0) + 26) % 26
                )
                .collect(toList());
    }

    public static boolean isConfusingNumber(long n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);

        long src = n;
        long res = 0;
        while (n > 0) {
            res = res * 10 + map.get((int) n % 10);
            n /= 10;
        }
        return res != src;
    }


    private static int getOccurence(int l, int r, int x) {
        List<Integer> list = Arrays.asList(0,1,4,100); //x arr e je je pos e ache tar sorted list.

        // returns :the index of the search key, if it is contained in the list; otherwise, (-(insertion point) - 1). The insertion point is defined as the point at which the key would be inserted into the list: the index of the first element greater than the key, or list.size() if all elements in the list are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.

        int i = Collections.binarySearch(list, l); //found hoile first index dei elem er.
        int j = Collections.binarySearch(list, r);

        if(i<0) i = ~i; //Binary Ones Complement Operator is unary and has the effect of 'flipping' bits.
        if(j<0) j = ~j -1; //https://www.tutorialspoint.com/cplusplus/cpp_operators.htm

        if(i == list.size()) return 0; //ekta elem o exist kore na

        return j-i+1;
    }

    private static int getMinSwap(String s){
        char[] chars = new char[s.length()+2];
        for (int i = 1,k=0; i < chars.length-1; i++,k++) {
            chars[i] = s.charAt(k);
        }
        chars[0] = '#';
        chars[chars.length-1] = '#';
        int swap=0;
        for (int i = 1; i < chars.length-1; i++) {
            char ch = chars[i];
            if(ch!=chars[i-1] && ch!=chars[i+1]){
                continue;
            }
            for (int j = chars.length-2; j >=1; j--) {
                if( chars[j] == ch){
                    continue;
                }
                if(chars[j-1]!=ch  && ch!=chars[j+1]){
                    swap++;
                    chars[i] = chars[j];
                    chars[j] = ch;
                    break;
                }
            }
        }

        return swap;
    }

    public int[] runningSum(int[] nums) {
        return IntStream.range(0, nums.length)
                .map(i -> i == 0 ? nums[i] : (nums[i] += nums[i-1]))
                .toArray();
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->
                a[0] == b[0] ? a[1] - b[1] : a[0]- b[0]);

        for(int i=0; i<servers.length; i++){
            pq.add(new int[]{servers[i], i}); //weight pos
        }

        int len = tasks.length;
        int idx = 0;
        int time = 0;
        int[] ans = new int[len];
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        while(idx<len){
            //Integer next = map.ceilingKey(time);
            time = Math.max(time, idx);
            if(map.containsKey(time)){
                List<int[]> free = map.get(time);
                pq.addAll(free);
            }
            if(pq.isEmpty()){
                Integer t = map.ceilingKey(time);
                time = t;
                pq.addAll(map.get(time));
            }

            //if(!pq.isEmpty()){
                int[] s = pq.poll();
                ans[idx] = s[1];
                int freeIn = time + tasks[idx];
                map.putIfAbsent(freeIn, new ArrayList<>());
                map.get(freeIn).add(s);
                idx++;
            //}

            //time++;


        }

        return ans;
    }

    public int[] sortedSquares(int[] nums) {
        return Arrays.stream(nums).map(i->i*i).sorted().toArray();
    }

    public int lcs(String s1, String s2, int idx1, int idx2, int count){
        if(idx1 >= s1.length()){
            return count;
        }
        if(idx2 >= s2.length()){
            return count;
        }
        int a = count;
        if(s1.charAt(idx1) == s2.charAt(idx2)){
            a = lcs(s1,s2, idx1+1, idx2+1, count+1);
        }
        int b = lcs(s1,s2, idx1+1, idx2, 0);
        int c = lcs(s1,s2, idx1, idx2+1, 0);

        return Math.max(a, Math.max(b,c));
    }

    List<String> ans2 = new ArrayList<>();
    public String[] expand(String s) {
        helper(s, 0, "");
        return ans2.toArray(String[]::new);
    }

    private String helper(String s, int idx, String prev){
        if(idx >= s.length()){
            return "";
        }

        for(int i=idx; i<s.length(); i++){
            if( s.charAt(i) == '}'){

            }
            else if(s.charAt(i) == ','){

            }
            else if(s.charAt(i) == '{'){
                int start = i;
                int close = s.indexOf("}", start);
                String sub = s.substring(start+1, close);
                String[] chars = sub.split(",");
                for(String ch : chars){
                    String res = helper(s, close, ch);
                    if(!res.equals("") && !prev.equals("")){
                        ans2.add(prev+res);
                    }
                }
                i = close;
                break;
            }
            else {
                prev+=s.charAt(i);
            }

        }

        return prev;
    }

    int ans3;
    Set<Integer> rset = new HashSet<>();
    Set<Integer> cset = new HashSet<>();
    Set<String> cut = new HashSet<>();
    int rows,cols;
    public int ways(String[] pizza, int k) {
        rows = pizza.length;
        cols = pizza[0].length();
        char[][] p = new char[rows][cols];
        int m=0;
        for(String pi : pizza){
            p[m++] = pi.toCharArray();
        }

        for(int i=0; i<p.length; i++){
            for(int j=0; j<p[0].length; j++){
                if(p[i][j] == 'A'){
                    rset.add(i);
                    cset.add(j);
                }
            }
        }
        helper(p, k, 0, 0);

        return ans3;
    }
    // A . .
   //  A A .
   //  . . .
    private void helper(char[][] p , int k, int i, int j){
        if(k == 0 || i>=rows || j>=cols){
            return;
        }

        if(k==1){
            if(hasApple(i, j, p)){
                ans3++;
            }
        }

        if(rset.contains(i)){
            helper(p, k-1, i+1, j);
        }
        if(cset.contains(j)){
            helper(p, k-1, i, j+1);
        }

    }

    private boolean hasApple(int x, int y, char[][] p) {
        for (int i = x; i < rows; i++) {
            for (int j = y; j < cols; j++) {
                if(p[i][j] == 'A'){
                    return true;
                }
            }
        }
        return false;
    }
    public List<List<Integer>> findRLEArray(int[][] en1, int[][] en2) {
        LinkedList<List<Integer>> product = new LinkedList<>();
        int idx1 = 0;
        int idx2 = 0;
        int len1 = en1.length;
        int len2 = en2.length;
        //List<Integer> lastProductEncoding = new ArrayList<>();
        int lastProductEncoding = 0;
        while(idx1 < len1 && idx2 < len2){
            int[] p1 = en1[idx1];
            int[] p2 = en2[idx2];

            int prodVal = p1[0] * p2[0];
            int freq = Math.min(p1[1], p2[1]);

            p1[1] -= freq;
            p2[1] -= freq;

            if(p1[1] == 0) idx1++;
            if(p2[1] == 0) idx2++;

            if (!product.isEmpty()){
                lastProductEncoding = product.getLast().get(0);
            }

            if (product.isEmpty() || lastProductEncoding != prodVal) {
                product.add(Arrays.asList(prodVal, freq));
            }
            else {
                //lastProductEncoding.set(1, lastProductEncoding.get(1) + freq);
            }

        }

        return product;
    }

    class Trie{
        Map<String, Trie> next = new HashMap<>();
        boolean isEnd;
    }
    public List<String> removeSubfolders(String[] folder) {
        Trie root = new Trie();

        for(String f : folder){
            Trie curr = root;
            String[] arr = f.split("/");
            for(String s : arr){
                if(s.equals("")){
                    continue;
                }
                curr.next.putIfAbsent(s, new Trie());
                curr = curr.next.get(s);
            }
            curr.isEnd = true;
        }
        
        List<String> ans = new ArrayList<>();
        extracted(root, ans, new StringBuilder());
        return ans;
    }

    private void extracted(Trie node, List<String> ans, StringBuilder curr) {
        Map<String, Trie> map = node.next;

        for (Map.Entry<String, Trie> entry : map.entrySet()){

            if (entry.getValue().isEnd){
                int len = curr.length();
                curr.append("/");
                curr.append(entry.getKey());
                ans.add(curr.toString());
                curr.setLength(len);
            }
            else {
                curr.append("/");
                curr.append(entry.getKey());
                extracted(entry.getValue(), ans, curr);
            }
            curr = new StringBuilder();
        }
    }

    public int getMoneyAmount(int n) {
        return helper(1, n);
    }

    private int helper(int lower, int higher){
        if(higher - lower == 0){
            return 0;
        }

        if(higher - lower == 1){
            return lower;
        }

        int res = Integer.MAX_VALUE;
        for(int i=lower; i<=higher; i++){
            int aMin=Integer.MAX_VALUE, bMin=Integer.MAX_VALUE;
            for(int j=1; j<i; j++){
                aMin = Math.min(aMin, helper(1, j));
            }
            for(int k=i+1; k<=higher; k++){
                bMin = Math.min(bMin, helper(k, higher));
            }

            int cost = i;
            res = Math.min(res, Math.max(aMin,bMin) + cost);
        }

        return res;
    }

    public boolean validUtf8(int[] data) {
        int len = data.length;
        String[] sdata = new String[len];
        for(int i=0; i<len; i++){
            String s = Integer.toBinaryString(data[i]);
            if(s.length() != 8){
                int need = 8 - s.length();
                String t = "0".repeat(need);
                s = t + s;
            }
            sdata[i] = s;
        }

        for(int i=0; i<len; i++){
            String s = sdata[i];
            if(s.startsWith("0")){
                continue;
            }
            if(s.startsWith("10")){
                return false;
            }

            int idx = 0;
            while(idx<s.length() && s.charAt(idx) == '1'){
                idx++;
            }
            if(idx >= 5){
                return false;
            }
            int limit = idx + i;
            if(limit > len){
                return false;
            }

            for(int j=i+1; j<limit; j++){
                String ss = sdata[j];
                if(ss.length() <=1){
                    return false;
                }
                if( !(ss.charAt(0) == '1' && ss.charAt(1) == '0') ){
                    return false;
                }
            }
            i = limit-1;
        }

        return true;
    }

    private String processDivision(String s){
        String[] arr = s.split("/");
        for(int i=0; i<arr.length; i++){

        }
        return "";
    }

    public ArrayList<Integer> order(List<Integer> heights, List<Integer> inFront) {
        int len = inFront.size();
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < inFront.size(); i++) {
            if(inFront.get(i) == 0) queue.add(i);
        }

        ArrayList<Integer> topoSort = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        visited.addAll(queue);
        while (!queue.isEmpty()) {
            int pos = queue.poll();
            for (int i = 0; i < len; i++) {
                if (visited.contains(i)) {
                    continue;
                }
                if (heights.get(i) < heights.get(pos)) {
                    int t = inFront.get(i);
                    inFront.set(i, t - 1);
                }

                if (inFront.get(i) == 0 && !visited.contains(i)) {
                    queue.addFirst(i);
                    visited.add(i);
                }
            }

            topoSort.add(heights.get(pos));
        }
        return topoSort;
    }

    public static int getVersionNumber(String version) {
        try {
            String appVersion1 = version.split("-")[0];
            String[] appVersion = appVersion1.split("\\.");
            return (Integer.parseInt(appVersion[0]) * 100) + (Integer.parseInt(appVersion[1]) * 10) + (Integer.parseInt(appVersion[2]));
        } catch (Exception ex) {
            //log.error("Error Parsing Version Number {} - {}", version, ex.getMessage());
            System.out.println("error : " + ex.getMessage());
        }
        return 430;
    }

    class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    };

    public int[] maxSubsequence(int[] nums, int k) {
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int[] res = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        int j=0;
        for(int i=arr.length-1; i>=0 &&  j<k; i--, j++){
            map.put(arr[i], 0);
        }

        j = 0;
        for(int i=0; i<nums.length && j<k; i++){
            int n = nums[i];
            if(map.containsKey(n)){
                res[j++] = n;
            }
        }

        return res;
    }

    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        Deque<Integer> decStack = new ArrayDeque<>();
        Deque<Integer> incStack = new ArrayDeque<>();

        for(int n : security){
            if(decStack.isEmpty()){
                decStack.push(n);
            }
            else if(decStack.peek() >= n){
                decStack.push(n);
            }
        }

        for(int i = security.length-1; i>=0; i--){
            int n = security[i];
            if(incStack.isEmpty()){
                incStack.push(n);
            }
            else if(incStack.peek() <= n){
                incStack.push(n);
            }
        }

        return null;
    }
    public static void main(String[] args) {
        TestBlah testBlah = new TestBlah();

        List<List<Interval>> schedule = new ArrayList<>();
        List<Interval> temp = schedule.stream()
                .flatMap(List::stream)
                .sorted()
                .limit(20)
                .collect(toList());
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        list.add(-1);
        //list.remove(-1);

        testBlah.maxSubsequence(new int[]{2,1,3,3}, 2);

        TreeMap<Integer, List<String>> treeMap = new TreeMap<>(Comparator.reverseOrder());
        Map.Entry<Integer, List<String>> en = null;
        int curr = 0;
        int j = 0;
        for(Map.Entry<Integer, List<String>> entry : treeMap.entrySet()){
            j += entry.getValue().size();
            if(j >= curr){
                en = entry;
                break;
            }
        }

       // List<String> l = en.getValue();

    }


}



class Node{
    int w;
    int h;

    public Node(int w, int h) {
        this.w = w;
        this.h = h;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return w == node.w && h == node.h;
    }

    @Override
    public int hashCode() {
        return Objects.hash(w, h);
    }
    static boolean running = true;

}

