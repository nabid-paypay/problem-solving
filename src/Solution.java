import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Solution {
    public int[] amountPainted(int[][] paint) {
        TreeSet<Integer> tset = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();

        int[] res = new int[paint.length];
        for(int[] p : paint){
            int start = p[0];
            int end = p[1];
            if(tset.isEmpty()){
                tset.add(p[0]);
                map.put(start, end);
            }
            else{
                Integer s = tset.floor(start);
                Integer e = tset.ceiling(end);
            }
        }

        return res;
    }

    public List<String> findAndReplacePattern(String[] words, String p) {


        List<String> res = new ArrayList<>();
        for (String w : words){
            Map<Character, Character> map = new HashMap<>();
            boolean same = true;
            for(int i=0; i<w.length(); i++){
                if(map.containsKey(w.charAt(i))){
                    map.put(w.charAt(i), p.charAt(i));
                }
                else{
                    if(map.get(w.charAt(i)) != p.charAt(i)){
                        same = false;
                        break;
                    }
                }
            }
            if (same) res.add(w);
        }

        return res;
    }



    private int modify(int[] mapping, int num) {
        String s = String.valueOf(num);
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int t = chars[i] - '0';
            int m = mapping[t];
            chars[i] = (char) m;
        }

        return Integer.parseInt(new String(chars));
    }

    public int mostFrequent(int[] nums, int key) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length-1; i++){
            if(nums[i] == key){
                map.put(nums[i+1], map.getOrDefault(nums[i+1], 0) + 1);
            }
        }

        int max = 0;
        int res = -1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() > max){
                res = entry.getKey();
            }
        }

        return res;
    }

    //1 2 4 5
    public int numRescueBoats(int[] p, int limit) {
        Arrays.sort(p);
        int j = p.length - 1;
        int i = 0;
        int res = 0;
        int sum = 0;
        while (i < j){
            sum += (p[i] + p[j]);
            if(sum == limit){
                res++;
                i++;
                j--;
                sum = 0;
            }
            else if(sum > limit){
                sum -= p[j];
                j--;
                res++;
            }
            else {
                i++;
            }
        }

        return sum == 0 ? res : res+1;
    }

    public int[] findRightInterval(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i=0; i< intervals.length; i++){
            map.put(intervals[i][0], i);
        }

        int[] res = new int[intervals.length];
        for(int i=0; i< intervals.length; i++){
            if(map.higherKey(intervals[i][1]) == null){
                res[i] = -1;
            }
            else{
                res[i] = map.get(map.ceilingKey(intervals[i][0]));
            }
        }

        return res;
    }

    public List<Integer> longestCommonSubsequence(int[][] arrays) {
        int cnt[] = new int[101];
        int[] a = Arrays.stream(arrays).flatMapToInt(Arrays::stream).toArray();
        System.out.println(a);
        return IntStream.range(0, 101).filter(i -> cnt[i] == arrays.length).boxed().toList();

    }

    class STNode{
        int start,end;
        int sum;
        STNode left , right;
        public STNode(int l, int r){
            this.start = l;
            this.end = r;
        }
    }

    STNode root;
    int res = 0;
    int maxRange;
    int threshold;
    private int maxWithinThreshold(int[] arr, int maxRange, int threshold){
        this.maxRange = maxRange;
        this.threshold = threshold;
        root = buildST(arr, 0, arr.length-1);
        return res;
    }

    private STNode buildST(int[] nums, int start, int end){
        if(start > end){
            return null;
        }

        STNode node = new STNode(start, end);
        if(start == end){
            node.sum = nums[start];
            if(node.sum <= threshold){
                res = Math.max(res, node.sum);
            }
            return node;
        }

        int mid =  (node.end + node.start)/2;
        node.left = buildST(nums, start, mid);
        node.right = buildST(nums, mid+1, end);
        node.sum = node.left.sum + node.right.sum;

        if(end - start + 1 <= maxRange && node.sum * (end -start+1) <= threshold){
            res = Math.max(res, node.sum * (end -start+1));
        }
        return node;
    }

    private int querySegTree(STNode node, int start, int end){
        if(node.end == end && node.start == start){
            return node.sum;
        }
        else {
            int mid = node.start + (node.end - node.start)/2;
            if(end <= mid){
                return querySegTree(node.left, start, end);
            }
            else if(start >= mid + 1){
                return querySegTree(node.right, start, end);
            }
            else {
                return querySegTree(node.left, start, mid) + querySegTree(node.right, mid+1, end);
            }
        }
    }

    public int sumRange(int[] arr, int left, int right) {
        return querySegTree(root, left, right);
    }

    public int minSetSize(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : arr){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        pq.addAll(map.keySet());

        int req = arr.length/2;
        int r = 0;
        int c = 0;
        while (!pq.isEmpty()){
            c++;
            r -= map.get(pq.poll());
            if(r <= req){
                return c;
            }
        }

        return arr.length;
    }

    class State{
        int nodeValues;
        int time;
        int node;

        public State(int nodeValues, int time, int node) {
            this.nodeValues = nodeValues;
            this.time = time;
            this.node = node;
        }

        @Override
        public String toString() {
            return "State{" +
                    "nodeValues=" + nodeValues +
                    ", time=" + time +
                    ", node=" + node +
                    '}';
        }
    }


    class Node{
        int i;
        int j;
        int val;

        public Node(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }
    }

    public int swimInWater(int[][] grid) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] vs = new boolean[m][n];
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a->a.val));
        pq.add(new Node(0, 0, grid[0][0]));

        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (!pq.isEmpty()){
            Node curr = pq.poll();
            min = Math.min(min, curr.val);
            max = Math.max(max, curr.val);

            if(curr.i == m-1 && curr.j == n-1){
                return max - min;
            }

            for (int[] dir : dirs){
                int ni = curr.i + dir[0];
                int nj = curr.j + dir[1];

                if(ni >= 0 && ni < m && nj >= 0 && nj < n && !vs[ni][nj]){
                    vs[curr.i][curr.j] = true;
                    pq.add(new Node(ni, nj, grid[ni][nj]));
                }
            }
        }

        return max - min;
    }
    public int sumOfUnique(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        for(int n : nums){
            if(set.contains(n)){
                set.remove(n);
                sum -= n;
            }
            else{
                set.add(n);
                sum += n;
            }
        }

        return sum;
    }

    public int halveArray(int[] nums) {
        int sum = 0;
        PriorityQueue<Double> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a));
        for(int n : nums){
            sum += n;
            pq.add(n*1.0);
        }
        double half = sum/2;
        System.out.println(pq.poll());
        return 0;
    }

    public boolean divideArray(int[] nums) {
        int[] cnt = new int[501];
        for (int n : nums)
            ++cnt[n];

        return IntStream.of(cnt).allMatch(n -> n %2 == 0);
    }

    TreeSet<Integer> treeSet = new TreeSet<>();
    public int[][] getIntervals() {
        List<int[]> res = new ArrayList<>();
        List<Integer> list = treeSet.stream().toList();
        int idx = 0;
        while(idx < list.size()){
            int start = list.get(idx++);
            int end = start+1;
            while (idx < list.size() && list.get(idx) == end){
                idx++;
                end++;
            }
            res.add(new int[]{start, end-1});
        }

        return res.toArray(new int[][]{});
    }

    class Node1{
        int val;
        char c;

        public Node1(int val, char c) {
            this.val = val;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Node1{" +
                    "val=" + val +
                    ", c=" + c +
                    '}';
        }
    }
    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Node1> pq = new PriorityQueue<>((a1, b1) -> b1.val - a1.val);
        pq.add(new Node1(a, 'a'));
        pq.add(new Node1(b, 'b'));
        pq.add(new Node1(c, 'c'));
        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()){
            Node1 curr1 = pq.poll();
            Node1 curr2 = pq.isEmpty() ? null : pq.poll();

            if(curr1.val <= 0){
                continue;
            }

            int len = sb.length();
            if(sb.length() == 0){
                extracted(sb, curr1, curr1, pq);
            }
            else if(sb.charAt(len-1) != curr1.c){
                extracted(sb, curr1, curr1, pq);
            }
            else if(sb.charAt(len-1) == curr1.c && sb.charAt(len-2) != curr1.c){
                sb.append(curr1.c);
                pq.add(new Node1(curr1.val - 1, curr1.c));
            }
            else if(sb.charAt(len-1) == curr1.c && sb.charAt(len-2) == curr1.c){

            }

            if(curr2 != null && curr2.val > 0){
                extracted(sb, curr2, curr1, pq);
            }
        }

        return sb.toString();
    }

    private void extracted(StringBuilder sb, Node1 curr1, Node1 curr11, PriorityQueue<Node1> pq) {
        sb.append(curr1.c);
        if(curr1.val - 1 > 0){
            sb.append(curr1.c);
        }
        pq.add(new Node1(curr1.val - 2, curr1.c));
        setRight(sb, curr11.c);
    }

    private void setRight(StringBuilder sb, char c) {
        int len = sb.length();
        if(len > 2 && sb.charAt(len-1) == c && sb.charAt(len-2) == c && sb.charAt(len-3) == c){
            sb.setLength(len-1);
        }
    }

    public int removeOnes(int[][] grid) {
        Map<Integer, Integer> rows = new HashMap<>();
        Map<Integer, Integer> cols = new HashMap<>();
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 1){
                    rows.put(i, rows.getOrDefault(i, 0) + 1);
                    cols.put(j, cols.getOrDefault(i, 0) + 1);
                }
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> rows.get(b[0]) + cols.get(b[1]) -
                rows.get(a[0]) - cols.get(a[1]));

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 1){
                    pq.add(new int[]{i, j});
                }
            }
        }

        int res = 0;
        while (!pq.isEmpty()){
            int[] curr = pq.poll();
            if(!flip(grid, curr)){
                return res;
            }
            res++;
        }

        return res;
    }

    private boolean flip(int[][] grid, int[] curr) {
        int x = curr[0];
        int y = curr[1];

        boolean changed = false;
        for(int j=0; j<grid[0].length; j++){
            if(grid[x][j] == 1){
                grid[x][j] = 0;
                changed = true;
            }
        }

        for(int i=0; i<grid.length; i++){
            if(grid[i][y] == 1){
                grid[i][y] = 0;
                changed = true;
            }
        }

        return changed;
    }

    private double[] ff(){
        List<Double> output = new ArrayList<>();
        return output.stream().mapToDouble(i->i).toArray();
    }

    static class Restaurant{
        int id;
        int rating;
        int veganFriendly;
        int price;
        int distance;

        public Restaurant(int[] r) {
            this.id = r[0];
            this.rating = r[1];
            this.veganFriendly = r[2];
            this.price = r[3];
            this.distance = r[4];
        }
    }

    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        Comparator<Restaurant> comparator = (a, b) -> a.rating == b.rating ? b.id - a.id : b.rating - a.rating;
        return Arrays.stream(restaurants)
                .map(Restaurant :: new)
                .toList()
                .stream()
                .filter(r -> veganFilter(veganFriendly, r) && priceFilter(maxPrice, r) && distanceFilter(maxDistance, r))
                .sorted(comparator)
                .map(r -> r.id)
                .toList();

    }

    private boolean veganFilter(int veganFriendly, Restaurant restaurant){
        if(veganFriendly == 0){
            return true;
        }
        return restaurant.veganFriendly == 1;
    }

    private boolean priceFilter(int maxPrice, Restaurant restaurant){
        return restaurant.price <= maxPrice;
    }

    private boolean distanceFilter(int maxDistance,  Restaurant restaurant){
        return restaurant.distance <= maxDistance;
    }

    public int minBitFlips(int start, int goal) {
        String s1 = Integer.toBinaryString(start);
        String s2 = Integer.toBinaryString(goal);

        if(s1.length() == s2.length()){
            return findMin(s1, s2);
        }
        else if(s1.length() > s2.length()){
            int d = s1.length() - s2.length();
            String s = "0".repeat(d);
            s2 = s + s2;
            return findMin(s1, s2);
        }
        else { //s2 > s1
            int d = s2.length() - s1.length();
            String s = "0".repeat(d);
            s1 = s + s1;
            return findMin(s1, s2);
        }
    }

    private int findMin(String s1, String s2) {
        int count = 0;
        for(int i=0; i<s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                count++;
            }
        }
        return count;
    }

    public int triangularSum(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int num : nums){
            list.add(num);
        }

        while (true){
            if(list.size() == 1){
                return list.get(0);
            }

            List<Integer> temp = new ArrayList<>();
            for(int i=0; i<list.size()-1; i++){
                int sum = (list.get(i) + list.get(i+1)) % 10;
                temp.add(sum);
            }

            list = temp;
        }
    }

    public long numberOfWays(String s) {
        StringBuilder sb = new StringBuilder();
        Long[] dp = new Long[s.length()+1];
        long ans =  helper(s, sb, 0, dp);
        return ans;
    }

    private long helper(String s, StringBuilder sb, int idx, Long[] dp){
        if(dp[idx] != null){
            return dp[idx];
        }

        if(sb.length() == 3){
            String t = sb.toString();
            if(t.equals("101") || t.equals("010")){
                return 1;
            }
            else{
                return 0;
            }
        }

        long res = 0;
        for(int i=idx; i<s.length(); i++){
            int len = sb.length();
            if(sb.length() == 0 || sb.charAt(len-1) != s.charAt(i)){
                sb.append(s.charAt(i));
                res += helper(s, sb, i+1, dp);
                sb.setLength(len);
            }
        }

        dp[idx] = res;
        return dp[idx];
    }

    public List<List<Integer>> findWinners(int[][] matches) {
        Set<Integer> winner = new HashSet<>();
        Map<Integer, Integer> looser = new HashMap<>();

        for(int[] m : matches){
            winner.add(m[0]);
            looser.put(m[1], looser.getOrDefault(m[1], 0) + 1);
        }

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for(int p : winner){
            if(!looser.containsKey(p)){
                temp.add(p);
            }
        }
        Collections.sort(temp);
        res.add(temp);

        List<Integer> temp2 = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : looser.entrySet()){
            if(entry.getValue() == 1){
                temp2.add(entry.getKey());
            }
        }

        Collections.sort(temp2);
        res.add(temp2);
        return res;
    }

    public int smallestCommonElement(int[][] mat) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] m : mat){
            for (int e : m){
                map.put(e, map.getOrDefault(e, 0) + 1);
            }
        }

        for(int j=0; j<mat[0].length; j++){
            if(map.get(mat[0][j]) == mat.length){
                return mat[0][j];
            }
        }

        return -1;
    }

    class Node2{
        Integer one;
        Integer two;
        Integer three;
        public Node2(Integer one, Integer two, Integer three){
            this.one = one;
            this.two = two;
            this.three = three;
        }
    }

    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int n = colors.length;
        Node2[] left = new Node2[n];
        for(int i=0; i<n; i++){
            int c = colors[i];
            Node2 temp = getNode(c);
            if (i != 0) {
                Node2 prev = left[i - 1];
                setNode(temp, prev);

                if (c == 1) {
                    temp.one = 0;
                } else if (c == 2) {
                    temp.two = 0;
                } else {
                    temp.three = 0;
                }
            }
            left[i] = temp;
        }

        Node2[] right = new Node2[n];
        for(int i=n-1; i>=0; i--){
            int c = colors[i];
            Node2 temp = getNode(c);

            if (i != n - 1) {
                Node2 next = right[i + 1];
                setNode(temp, next);

                if (c == 1) {
                    temp.one = 0;
                } else if (c == 2) {
                    temp.two = 0;
                } else {
                    temp.three = 0;
                }
            }
            right[i] = temp;
        }

        List<Integer> res = new ArrayList<>();
        for (int[] q : queries){
            int idx = q[0];
            int val = q[1];
            Node2 l = left[idx];
            Node2 r = right[idx];

            if(val == 1){
                add(l.one, r.one, res);
            }
            else if(val == 2){
                add(l.two, r.two, res);
            }
            else{
                add(l.three, r.three, res);
            }
        }

        return res;
    }

    private Node2 getNode(int c){
        Node2 temp;
        if(c == 1){
            temp = new Node2(0, null, null);
        }
        else if(c == 2){
            temp = new Node2(null, 0, null);
        }
        else {
            temp = new Node2(null, null, 0);
        }
        return temp;
    }

    private void setNode(Node2 temp, Node2 prev){
        temp.one = prev.one == null ? null : prev.one + 1;
        temp.two = prev.two == null ? null : prev.two + 1;
        temp.three = prev.three == null ? null : prev.three + 1;
    }

    private void add(Integer a, Integer b, List<Integer> res){
        if(a == null && b == null){
            res.add(-1);
        }
        else if(a == null && b != null){
            res.add(b);
        }
        else if(a != null && b == null){
            res.add(a);
        }
        else{
            res.add(Math.min(a, b));
        }
    }

    public int largestInteger(int num) {
        List<Integer> digits = getDigits(num);
        PriorityQueue<Integer> evens = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        PriorityQueue<Integer> odds = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        for(int d : digits){
            if((d & 1) == 0){
                evens.add(d);
            }
            else odds.add(d);
        }

        int p = digits.size()-1;
        int res = 0;
        for(int d : digits){
            int t;
            if((d & 1) == 0){
                t = evens.poll() * (int)Math.pow(10, p);
            }
            else {
                t = odds.poll() * (int)Math.pow(10, p);
            }
            p--;
            res += t;
        }

        return res;
    }

    private List<Integer> getDigits(int num) {
        List<Integer> digits = new ArrayList<>();
        while (num!=0){
            digits.add(num%10);
            num/=10;
        }
        Collections.reverse(digits);
        return digits;
    }

    class Node3{
        int val;
        int count;

        public Node3(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }
    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        for(int n : nums){
            pq.add(n);
        }

        while (k > 0){
            int poll = pq.poll();
            poll++;
            pq.add(poll);
            k--;
        }

        int MOD = (int) 1e9 + 7;
        int res = 1;
        while (!pq.isEmpty()){
            res += (pq.poll() * res) % MOD;
        }
        return res;
    }


    private void foo(){
        int[][] mat = {{1,2,3,4,5,6,},{1,12,13,14,15,16},{1,22,23,24,25,26}};
        int rows = mat.length;
        int cols = mat[0].length;
        int lowest=Integer.MAX_VALUE;
        int highest=Integer.MIN_VALUE;
        List<int[]> highestPos = new ArrayList<>();
        List<int[]> lowestPos = new ArrayList<>();

        System.out.println("informed values: ");
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                highest = Math.max(mat[i][j], highest);
                lowest = Math.min(mat[i][j], lowest);
                System.out.print(mat[i][j]+"\t");
            }
            System.out.println("");
        }

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(mat[i][j] == highest){
                    highestPos.add(new int[]{i, j});
                }
                else if(mat[i][j] == lowest){
                    lowestPos.add(new int[]{i, j});
                }
            }
        }

        System.out.println("highest is : " + mat[highestPos.get(0)[0]][highestPos.get(0)[1]]);
        for(int[] h : highestPos){
            System.out.println("located in : " + "row : " + (h[0] + 1) + " col : " + (h[1] + 1));
        }

        System.out.println("\n");
        System.out.println("lowest is : " + mat[lowestPos.get(0)[0]][lowestPos.get(0)[1]]);
        for(int[] l : lowestPos){
            System.out.println("located in : " + "row : " + (l[0] + 1) + " col : " + (l[1] + 1));
        }
    }

    private int[] getRandomArray(int size){
        int[] arr = new int[size];
        for(int i=0; i<arr.length; i++){
            //if you don't any bound
            int rand = ThreadLocalRandom.current().nextInt();
            //if you need any bound. for example following will return int value in between 10 and 100
            //int rand = ThreadLocalRandom.current().nextInt(10, 100);
            arr[i] = rand;
        }
        return arr;
    }

    public String findContestMatch(int n) {
        int a = (int) (Math.log(n) / Math.log(2));

        return "";
    }

    public List<Integer> fibo(int k) {
        List<Integer> fib = new ArrayList<>();
        fib.add(1);
        fib.add(1);
        fib.add(1);
        for(int i=3;  ;i++){
            int l = fib.get(i-1) + fib.get(i-2);
            if(l<=k){
                fib.add(l);
            }
            else break;
        }

        return fib;
    }

    public int findMinFibonacciNumbers(int k) {
        List<Integer> fib = fibo(k);
        return helper(fib, 0, k, 0);
    }

    private int helper(List<Integer> fib, int idx, int k, int sum) {
        if(idx >= fib.size() || sum > k){
            return Integer.MAX_VALUE;
        }
        if(sum == k){
            return 1;
        }

        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        a = helper(fib, idx, k, sum + fib.get(idx));
        if(idx + 1 < fib.size()){
            b = helper(fib, idx+1, k, sum + fib.get(idx + 1));
        }

        return Math.min(a, b);
    }

    class Node4{
        int cumSum;
        int idx;
        public Node4(int cumSum, int idx) {
            this.cumSum = cumSum;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "Node4{" +
                    "cumSum=" + cumSum +
                    ", idx=" + idx +
                    '}';
        }
    }

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int currSum = 0;

        Deque<Node4> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            currSum += nums[i];
            while (!deque.isEmpty() && deque.peekLast().cumSum < currSum){
                deque.removeLast();
            }

            while (!deque.isEmpty() && deque.peekLast().cumSum - deque.peekFirst().cumSum >= k){
                min = Math.min(min, deque.peekFirst().idx - deque.peekLast().idx + 1);
                deque.removeFirst();
            }

            deque.addLast(new Node4(currSum, i));
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int t : tasks){
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        int res = 0;
        for(int val : map.values()){
            if(val == 1){
                return -1;
            }
            else if(val == 2 || val == 3){
                res++;
            }
            else{
                int d = val / 3;
                res += d;

                int t = d * 3;
                if(t < val){
                    res++;
                }
            }
        }

        return res;
    }

    public String digitSum(String s, int k) {
        if(s.length() == k){
            return s;
        }

        StringBuilder sb = new StringBuilder();
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            t.append(s.charAt(i));
            if(t.length() == k){
                sb.append(getSum(t.toString()));
                t = new StringBuilder();
            }
        }

        return sb.length() <= k ? sb.toString() : digitSum(sb.toString(), k);
    }

    private String getSum(String s) {
        int sum = 0;
        for (char ch : s.toCharArray()){
            sum += Character.digit(ch, 10);
        }

        return String.valueOf(sum);
    }

    class Node5{
        char ch;
        int parent;
        List<Node> children = new ArrayList<>();

        public Node5(char ch, int parent) {
            this.ch = ch;
            this.parent = parent;
        }
    }
    public int longestPath(int[] parent, String s) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0; i<parent.length; i++){
            int p = parent[i];
            if(p == -1){
                continue;
            }
            map.putIfAbsent(p, new ArrayList<>());
            map.get(p).add(i);
        }

        return helper(0, map, s, -1);
    }

    private int helper(int curr, Map<Integer, List<Integer>> map, String s, int gp){
        int max = 0;
        for(int c : map.getOrDefault(curr, new ArrayList<>())){
            if(gp == -1 && s.charAt(curr) != s.charAt(c)
                    || s.charAt(curr) != s.charAt(c) && s.charAt(c) != s.charAt(gp)){
                max = Math.max(max, 1 + helper(c, map, s, curr));
            }
            else{
                max = Math.max(max, helper(c, map, s, curr));
            }
        }

        return max;
    }

    private void foo2(List<Integer> list){
        int max1 = -1;
        int max2 = -1;
        for(int i : list){
            if (i > max1) {
                max2 = max1;
                max1 = i;
            }
            else if (i > max2) {
                max2 = i;
            }
        }

        System.out.println("max1 :" + max1);
        System.out.println("max2 :" + max2);
    }

    public int maximumSum(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int n : nums){
            int dSum = getDigitSum(n);
            map.computeIfAbsent(dSum, key -> new ArrayList<>()).add(dSum);
        }

        int max = 0;
        for (List<Integer> list : map.values()){
            if(list.size() > 1){
                list.sort(Collections.reverseOrder());
                max = Math.max(max, list.get(0) + list.get(1));
            }
        }

        return max == 0 ? -1 : max;
    }

    private int getDigitSum(int n) {
        int sum = 0;
        while (n!=0){
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    static class Pair{
        String s;
        int idx;

        public Pair(String s, int idx) {
            this.s = s;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "s='" + s + '\'' +
                    ", idx=" + idx +
                    '}';
        }
    }
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int k = queries[i][0];
            int trim = queries[i][1];

            List<Pair> list = new ArrayList<>();
            for (int j=0; j<nums.length; j++){
                String s = nums[j];
                int len = s.length();
                String ss = s.substring(len-trim, len);
                list.add(new Pair(ss, j));
            }

            list.sort((a, b) -> {
                int c = a.s.compareTo(b.s);
                if(c == 0){
                    return a.idx - b.idx;
                }
                return c;
            });
            res[i] = list.get(k-1).idx;
        }

        return res;
    }

    public int minOperations(int[] nums, int[] numsDivide) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        boolean hasOdd = false;
        for (int n : nums) {
            treeMap.put(n, treeMap.getOrDefault(n, 0) + 1);
            if(n % 2 == 1){
                hasOdd = true;
            }
        }

        int res = 0;
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();

            if(hasOdd && key % 2 == 0){
                res += val;
                continue;
            }

            boolean notDivide = false;
            for (int n : numsDivide){
                if(n % key != 0){
                    notDivide = true;
                    break;
                }
            }

            if (notDivide){
                res += val;
            }
            else return res;
        }

        return -1;
    }

    interface test{
        String firstName();
        String secondName();
        String thirdName();

        default String fullName1() {
            String fName = firstName();
            if (fName != null) {
                String mName = secondName();
                if (mName != null) {
                    String lName = thirdName();
                    if (lName != null) {
                        return fName + " " + mName + " " + lName;
                    }
                }
            }
            return null;
        }

        default Optional<String> fullNameFancy(){
            return Optional.ofNullable(firstName())
                    .flatMap(fname -> Optional.ofNullable(secondName())
                    .flatMap(sname -> Optional.ofNullable(thirdName())
                    .flatMap(tname -> Optional.of(fname + ", " + sname + ", " + tname))));
        }
    }

     class TestImpl implements test{

        @Override
        public String firstName() {
            return "first name";
        }

        @Override
        public String secondName() {
            return "second name";
        }

        @Override
        public String thirdName() {
            return "third name";
        }
    }

    public String bestHand(int[] ranks, char[] suits) {
        Map<Character, Integer> suit = new HashMap<>();
        Map<Integer, Integer> rank = new HashMap<>();
        for (int i=0; i<ranks.length; i++){
            suit.put(suits[i], suit.getOrDefault(suits[i], 0) + 1);
            rank.put(ranks[i], rank.getOrDefault(ranks[i], 0) + 1);

            if(suit.get(suits[i]) == 5){
                return "Flush";
            }
        }

        boolean pair = false;
        for (int val : rank.values()){
            if(val == 3 || val == 4){
                return "Three of a Kind";
            }
            else if(val == 2){
                pair = true;
            }
        }


        return pair ? "Pair" : "High Card";
    }

    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<String, Integer> map = new HashMap<>();
        for (int[] ints : grid) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(ints[j]);
            }
            String s = sb.toString();
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        int res = 0;
        for (int[] ints : grid) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(ints[i]);
            }
            String s = sb.toString();
            if (map.containsKey(s)) {
                res += map.get(s);
            }
        }

        return res;
    }

    public String shortestCompletingWord(String licensePlate, String[] words) {
        Map<Character, Integer> ori = new HashMap<>();
        populate(licensePlate, ori);

        String res = "";
        for(String s : words){
            Map<Character, Integer> map = new HashMap<>();
            populate(s, map);

            if(contains(ori, map)){
                if(res.isEmpty() || s.length() < res.length()){
                    res = s;
                }
            }
        }

        return res;
    }

    private void populate(String str, Map<Character, Integer> ori){
        for(char ch : str.toCharArray()){
            if(Character.isDigit(ch) || ch == ' '){
                continue;
            }

            ch = Character.toLowerCase(ch);
            ori.put(ch, ori.getOrDefault(ch, 0) + 1);
        }
    }

    private boolean contains(Map<Character, Integer> ori, Map<Character, Integer> map){
        for(Map.Entry<Character, Integer> entry : ori.entrySet()){
            Character k = entry.getKey();
            Integer v = entry.getValue();

            if(!map.containsKey(k)){
                return false;
            }
            else if(map.get(k) < v){
                return false;
            }
        }

        return true;
    }

    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] a : items1){
            map.put(a[0], map.getOrDefault(a[0], 0) + a[1]);
        }

        for (int[] a : items2){
            map.put(a[0], map.getOrDefault(a[0], 0) + a[1]);
        }

        List<List<Integer>> res = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            res.add(List.of(entry.getKey(), entry.getValue()));
        }

        return res;
    }

    public long countBadPairs(int[] nums) {
        long res = 0;
        int n = nums.length;

        int misplaced = 0;
        for(int i = 0; i< n; i++){
            int num = nums[i];
            for (int j = num +1 ; j < Math.min(n, num + n-i); j++){
                if(nums[j] != j){
                    misplaced++;
                }
            }
            res += misplaced;
        }

        return res;

    }

    public long taskSchedulerII(int[] tasks, int space) {
        Map<Integer, Long> map = new HashMap<>();
        long res = 0;
        for(int i=0; i<tasks.length; i++){
            int t = tasks[i];
            if(!map.containsKey(t)){
                res++;
                map.put(t, res);
            }
            else if(res - map.get(t) >= space){
                res++;
                map.put(t, res);
            }
            else{
//                while (res - map.get(t) != space){
//                    res++;
//                }
                res = map.get(t) + space;
                map.put(t, ++res);
            }
        }

        return res;
    }

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] e : edges){
            map.computeIfAbsent(e[0], k-> new ArrayList<>()).add(e[1]);
        }

        Set<Integer> set = new HashSet<>();
        Arrays.stream(restricted).forEach(set::add);

        return dfs(0, map, set);
    }

    private int dfs(int curr, Map<Integer, List<Integer>> map, Set<Integer> set){
        int res = 1;
        for (int nei : map.getOrDefault(curr, new ArrayList<>())){
            if(!set.contains(nei)){
                res += (dfs(nei, map, set)+1);
            }
        }

        return res;
    }

    public int solve(List<Integer> A, int B) {
        int n = A.size();
        int res = 0;
        int sum = 0;
        int i = 0;
        int j = 0;
        while (j < n ){

            if(sum + A.get(j) < B){
                sum += A.get(j);
                res += (j - i + 1);
                j++;
            }
            else{
                sum -= A.get(i++);
            }
        }

        return res;
    }

    public String shiftingLetters(String s, int[][] shifts) {
        int[] arr = getModifiedArray(s.length(), shifts);
        return "";
    }

    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];

        for(int i=0; i<updates.length; i++){
            int sidx = updates[i][0];
            int eidx = updates[i][1];
            int inc = updates[i][2];

            res[sidx] += inc;
            if(eidx + 1 < length){
                res[eidx + 1] -= inc;
            }
        }

        for(int i =1; i<length; i++){
            res[i] += res[i - 1];
        }

        return res;
    }

    public boolean findSubarrays(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length-1; i++){
            map.put(nums[i]+nums[i+1], i);
        }

        for(int i=0; i<nums.length-1; i++){
            int sum = nums[i] + nums[i+1];
            if(map.containsKey(sum) && map.get(sum)!=i){
                return true;
            }
        }

        return false;

    }

    public boolean isStrictlyPalindromic(int n) {
        for (int i = 2; i <= n-2; i++) {
            String s = Integer.toString(n, i);
            if(!palin(s)){
                return false;
            }
        }

        return true;
    }

    private boolean palin(String s){
        StringBuilder sb = new StringBuilder(s);
        return s.equals(sb.reverse().toString());
    }

    int lmax = 0;
    public int maximumRows(int[][] mat, int cols) {
        int m = mat.length;
        int n = mat[0].length;
        combBacktrack(new ArrayList<>(), 0, n, cols, mat);

        return lmax;
    }

    private void combBacktrack(List<Integer> tempList, int start, int n, int k, int[][] mat) {
        if(tempList.size() == k){
            findMax(mat, new HashSet<>(tempList));
        }

        for (int i = start; i <n ; i++) {
            tempList.add(i);
            combBacktrack(tempList, i+1, n, k, mat);
            tempList.remove(tempList.size()-1);
        }
    }

    private void findMax(int[][] mat, Set<Integer> set) {
        int c = 0;
        for (int i = 0; i < mat.length; i++) {
            boolean spoiled = false;
            for (int j = 0; j < mat[0].length; j++) {
                if(mat[i][j] == 1 && !set.contains(j)){
                    spoiled = true;
                }
            }
            if (!spoiled){
                c++;
            }
        }

        lmax = Math.max(lmax, c);

    }

    public int maximumRobots(int[] ct, int[] rc, long budget) {
        int n = ct.length;
        int i=0;
        int j=0;
        TreeSet<Integer> treeSet = new TreeSet<>();
        int max = 0;
        long sum = 0;
        int res = 0;

        while (j < n){
            sum += rc[j];
            treeSet.add(ct[j]);
            max = treeSet.last();
            int k = j - i + 1;
            long ts = max + k * sum;

            if(ts <= budget){
                res = Math.max(res, k);
            }
            else {
                treeSet.remove(ct[i]);
                sum -= rc[i];
                i++;
            }

            j++;
        }

        return res;
    }


    public int numberOfWays(int startPos, int endPos, int k) {
        if(k > (endPos - startPos)){
            return 0;
        }
        ArrayDeque<List<Integer>> q = new ArrayDeque<>();
        Set<String> set = new HashSet<>();
        q.add(new ArrayList<>(startPos));
        int level = 1;
        int res = 0;

        while(!q.isEmpty()){
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                List<Integer> list = q.poll();
                if (level > k){
                    continue;
                }
                else if(level == k){
                    int last = list.get(list.size()-1);
                    if(last == endPos){
                        StringBuilder sb = new StringBuilder();
                        for (int n : list){
                            sb.append(n);
                        }

                        String s = sb.toString();
                        if(!set.contains(s)){
                            set.add(s);
                            res++;
                        }
                    }
                }
                else{
                    int last = list.get(list.size()-1);
                    List<Integer> a = new ArrayList<>(list);
                    a.add(last+1);
                    q.add(a);

                    if(last > 0){
                        List<Integer> b = new ArrayList<>(list);
                        a.add(last-1);
                        q.add(a);
                    }
                }
            }
            level++;
        }

        return res;
    }

    public String largestPalindromic(String num) {
        Map<Character, Integer> map = new HashMap<>();
        num.chars().mapToObj(e -> (char) e).forEach(ch -> map.put(ch, map.getOrDefault(ch, 0) + 1));

        StringBuilder sb = new StringBuilder();
        int max = -1;
        for(int i=9; i>=0; i--){
            if(sb.length() == 0 && i == 0){
                continue;
            }

            char k = (char) ('0' + i);
            if(map.containsKey(k)){
                int v = map.get(k);
                if( (v & 1) == 0){
                    String s = i + "";
                    s = s.repeat(v/2);
                    sb.append(s);
                }
                else{
                    v--;
                    max = Math.max(max, i);

                    if(v > 0){
                        String s = i + "";
                        s = s.repeat(v/2);
                        sb.append(s);
                    }
                }

            }
        }

        String s1 = sb.toString();
        String rev = sb.reverse().toString();
        if(max != -1){
            s1 = s1 + max;
        }

        String res =  s1 + rev;

        return res.length() == 0 ? "0" : res;
    }

    public int[] sumPrefixScores(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : words){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                sb.append(s.charAt(i));
                String t = sb.toString();
                map.put(t, map.getOrDefault(t, 0) +1);
            }
        }

        int[] res = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            StringBuilder sb = new StringBuilder();
            int c = 0;
            for (int j = 0; j < s.length(); j++) {
                sb.append(s.charAt(j));
                String t = sb.toString();
                c += map.getOrDefault(t, 0);
            }

            res[i] = c;
        }

        return res;
    }

    <T> void foo(T... args){
        for (T t : args){
            System.out.println(t);
        }
    }

    void test(){

    }


    static class blah{

    }

    public int numDupDigitsAtMostN(int n) {
        int res = 0;
        for(int i=1; i<=n; i++){
            if(dup(i)){
                res++;
            }
        }

        return res;
    }

    private boolean dup(int i) {
        Set<Integer> set = new HashSet<>();
        while (i!=0){
            int d = i % 10;
            if(set.contains(i)){
                return true;
            }
            set.add(d);
            i /= 10;
        }

        return false;
    }

    public static void main(String[] args) throws MalformedURLException {
        Solution solution = new Solution();
        solution.foo(new blah(), new blah());

    }
}
//one
//two