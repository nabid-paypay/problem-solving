import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println();
    }
}