import javax.swing.text.html.HTMLDocument;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Collectors;

public class Contest {

//[[1,2,0,1],[1,3,3,1],[0,2,5,1]]
//        [2,3]
//        [2,3]
//        2

    public int minimumCost(int[] cost) {
        //[6,5,7,9,2,2]
        //2 2 5 6 7 9
        Arrays.sort(cost);
        int res = 0;
        int i = cost.length-1;
        for( ; i>0; i-=3){
            res += (cost[i] + cost[i-1]);
        }

        return res + (i == 0 ? cost[0] : 0);
    }

    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int m = grid.length;
        int n = grid[0].length;

        int lower = pricing[0];
        int upper = pricing[1];

        Queue<Node> q = new ArrayDeque<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt((Node a) -> a.dist)
                .thenComparingInt(a -> a.price)
                .thenComparingInt(a -> a.r)
                .thenComparingInt(a -> a.c));

        Node temp = new Node(start[0], start[1], 0, grid[start[0]][start[1]]);
        q.add(temp);
        if(grid[start[0]][start[1]] >=lower && grid[start[0]][start[1]] <= upper){
            pq.add(temp);
        }

        boolean[][] vs = new boolean[m][n];
        boolean[][] pqAdded = new boolean[m][n];
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        int level = 1;
        while (!q.isEmpty()){
            int sz = q.size();
            for (int i=0; i<sz; i++){
                Node curr = q.poll();
                assert curr != null;
                if(vs[curr.r][curr.c]){
                    continue;
                }

                vs[curr.r][curr.c] = true;

                for(int[] dir : dirs){
                    int ni = curr.r + dir[0];
                    int nj = curr.c + dir[1];

                    if(ni >= 0 && ni < m && nj >=0 && nj < n && !vs[ni][nj] && grid[ni][nj]!=0){
                        Node t = new Node(ni, nj, level, grid[ni][nj]);
                        q.add(t);
                        if(grid[ni][nj] >= lower && grid[ni][nj] <= upper && !pqAdded[ni][nj]){
                            pq.add(t);
                            pqAdded[ni][nj] = true;
                        }

                    }
                }
            }

            level++;

        }

        for(int i = 0; i<k && !pq.isEmpty(); i++){
            Node curr = pq.poll();
            res.add(List.of(curr.r, curr.c));
        }
        return res;
    }

    static class Node{
        int r;
        int c;
        int dist;
        int price;

        public Node(int r, int c, int dist, int price){
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.price = price;
        }
    }

    public int numberOfArrays(int[] differences, int lower, int upper) {
        int min = Integer.MAX_VALUE;
        for(int d : differences){
            int a = count(d, lower, upper);
            if(a == 0){
                return 0;
            }
            min = Math.min(min, a);
        }

        return min;
    }

    private int count(int num, int lower, int upper){
        if(num > (upper - lower)){
            return 0;
        }
        int count = 0;
        int mid = (lower + upper)/2;
        while (mid - lower <= num){
            if(mid - lower == num){
                break;
            }
            lower++;
        }

        while (mid < upper){
            if(mid - upper == num){
                count++;
                lower++;
                mid++;
            }
            else if( (mid - lower) > num){
                lower++;
                upper--;
            }

        }
        List<String> list = new ArrayList<>();
        list.forEach(this::update);
        return count;
    }

    private void update(String lead){

    }
    public int minDeletions(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : s.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int val : map.values()){
            min = Math.min(min, val);
            max = Math.max(max, val);
        }

        int[] freq = new int[max + 1];
        for (int val : map.values()){
            freq[val]++;
        }

        //2 -> 4
        int res = 0;
        for (int i=0; i<=max; i++){ //i=2
            if (freq[i] > 1){
                int f = freq[i]; //4
                res += ((freq[i] - 2) * i);
                res += reduce(freq, freq[i]-1);
            }
        }

        return res;
    }

    private int reduce(int[] freq, int idx){
        int f = idx + 1;
        for(int i = idx; i>=0; i--){
            if(freq[i] == 0){
                freq[i]++;
                return f - i;
            }
        }

        return f;
    }

    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> q = new ArrayDeque<>();
        for(int i=0; i<m; i++){
            if(grid[i][0] == 1){
                q.add(new int[]{i, 0});
            }
            if(grid[i][n-1] == 1){
                q.add(new int[]{i, n-1});
            }
        }

        for(int j=0; j<m; j++){
            if(grid[0][j] == 1){
                q.add(new int[]{0, j});
            }
            if(grid[m-1][j] == 1){
                q.add(new int[]{m-1, j});
            }
        }

        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (!q.isEmpty()){
            int[] curr = q.poll();
            int i = curr[0];
            int j = curr[1];
            grid[i][j] = -1;
            for(int[] dir : dirs){
                int ni = i + dir[0];
                int nj = j + dir[1];

                if(ni >=0 && ni < m && nj >=0 && nj < n && grid[ni][nj] == 1){
                    q.add(new int[]{ni, nj});
                }
            }
        }

        int res = 0;
        for (int[] ints : grid) {
            for (int j = 0; j < n; j++) {
                if (ints[j] == 1) {
                    res++;
                }
            }
        }

        return res;
    }

    public int lengthOfLongestSubstringList(String s, int k) {
        int n = s.length(), ans = 0;
        //Set<String> set = new HashSet<>();
        Set<String> res = new HashSet<>();
        Set<Character> set = new HashSet<>(); // current index of character

        int l = 0;
        int r = 0;
        while (r < n){
            char ch = s.charAt(r);
            set.add(ch);
            if(r - l + 1 == k){
                if (set.size() == k){
                    res.add(s.substring(l, r+1));
                }
                set.remove(s.charAt(l++));
            }
            r++;
        }

        for (String ss : res){
            System.out.println(ss);
        }
        return res.size();
    }


        public int numJewelsInStones(String jewels, String stones) {
        boolean check;
        //System.out.println(check);
        Set<Character> set = new HashSet<>();
        for (char c : jewels.toCharArray()) {
            set.add(c);
        }

        int res = 0;
        for (char c : stones.toCharArray()) {
            if(set.contains(c)){
                res++;
            }
        }
        return res;
    }




    public static void main(String[] args) {
        Contest contest = new Contest();
        contest.lengthOfLongestSubstringList("abacab", 3);
        Runnable runnable = () -> {
            System.out.println("thread run");
        };


        String s = "6/aa";
        System.out.println(s.indexOf("/"));
        System.out.println(s.substring(0, 4));
        String[] ss = s.split(s, -1);
        System.out.println((char)277);
        for (String sss : ss){
            System.out.println(sss);
        }

        System.out.println(EnumTest.valueOf("DID"));
    }

//good commit/ the state you want to set in github

    //something good again

}
