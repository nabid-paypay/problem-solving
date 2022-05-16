package misc;


import java.util.PriorityQueue;
import java.util.*;
import java.util.stream.Collectors;

class Solution1 {


    public int numberOfCleanRooms(int[][] room) {
        int m = room.length;
        int n = room[0].length;

        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int idx = 0;

        ArrayDeque<int[]> q = new ArrayDeque<>();
        int[][] vs = new int[m][n];
        q.add(new int[]{0, 0});

        while (!q.isEmpty()){
            int[] curr = q.poll();
            int i = curr[0];
            int j = curr[1];

            if(vs[i][j] == 4){
                break;
            }

            int ni = i;
            int nj = j;
            while (ni >= 0 && ni < m && nj >= 0 && nj < n && room[ni][nj] == 0){
                ni += dirs[idx][0];
                nj += dirs[idx][1];
            }

            idx++;
            if(idx == 4){
                idx = 0;
            }

            ni -= dirs[idx][0];
            nj -= dirs[idx][1];

            vs[ni][nj]++;

        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(vs[i][j] >= 1){
                    res++;
                }
            }
        }

        return res;
    }

    private boolean bfs(int[][] grid, int time){
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 1){
                    q.add(new int[]{i, j});
                }
            }
        }

        int level = 0;
        while (!q.isEmpty()){
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int[] curr = q.poll();

                if(curr[0] == m-1 && curr[1] == n-1){
                    return level <= time;
                }
                for (int[] dir : dirs){
                    int ni = dir[0] + curr[0];
                    int nj = dir[1] + curr[1];

                    if(ni >= 0 && ni < m && nj >= 0 && nj < n && grid[ni][nj] == 0){
                        grid[ni][nj] = 1;
                        q.add(new int[]{ni, nj});
                    }
                }
            }
            level++;
        }

        return false;
    }
    public int maximumMinutes(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int high = m * n;
        int low = 0;

        while (low < high){
            int mid = low + (high - low)/2;
            int[][] arr = deepCopy2D(grid);
            if(bfs(arr, mid)){
                low = mid + 1;
            }
            else{
                high = mid;
            }
        }

        return low;
    }

    public int[][] deepCopy2D(int[][] arr){
        return Arrays.stream(arr)               // deep copy
                .map(ar -> Arrays.copyOf(ar, ar.length))
                .toArray(int[][]::new);
    }

      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }


    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        Map<Integer, Integer> map = new HashMap<>();
        ListNode curr = head;
        while (curr!=null){
            map.put(curr.val, map.getOrDefault(curr.val, 0) + 1);
            curr = curr.next;
        }

        ListNode res = new ListNode(-1);
        ListNode ans = res;
        while (head != null){
            if(map.get(head.val) == 1){
                res.next = head;
                res = res.next;
                head = head.next;
            }
        }

        return ans.next;
    }

    class Node{
        StringBuilder path;
        int i;
        int j;

        public Node(StringBuilder path, int i, int j) {
            this.path = path;
            this.i = i;
            this.j = j;
        }
    }
    public boolean hasValidPath(char[][] grid) {
        return dfs(new StringBuilder(), 0 , 0, grid);
    }

    private boolean dfs(StringBuilder sb, int i, int j, char[][] arr){
        int m = arr.length;
        int n = arr[0].length;
        if(i == m-1 && i == n-1){
            return isValid(sb);
        }

        if(i >= m || j >= n){
            return false;
        }

        int len = sb.length();
        sb.append(arr[i][j]);


        if(dfs(sb, i+1, j, arr)){
            return true;
        }
        sb.setLength(len);

        return dfs(sb, i, j+1, arr);
    }

    private boolean isValid(StringBuilder sb){
        String s = sb.toString();
        int balance = 0;
        for(char ch : s.toCharArray()){
            if(ch == '('){
                balance++;
            }
            else{
                if(balance <= 0){
                    return false;
                }
                balance--;
            }
        }

        return balance == 0;
    }

    private boolean isValid(String s){
        int balance = 0;
        for(char ch : s.toCharArray()){
            if(ch == '('){
                balance++;
            }
            else{
                if(balance <= 0){
                    return false;
                }
                balance--;
            }
        }

        return balance == 0;
    }

    public int findTheLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if(vowels.contains(s.charAt(i))){
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            }
            if(evenVowels(map)){
                res = i + 1;
            }
        }

        return res;
    }

    private boolean evenVowels(Map<Character, Integer> map) {
        //return map.values().stream().reduce(0, Integer::sum) % 2 == 0;
        return map.values().stream().mapToInt(Integer::intValue).sum() % 2 == 0;
    }


    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.isValid("(()()(())((())))"));
        System.out.println(solution1.isValid("(()))))((("));
        int[] arr = {1, 2, 3};
        //long a = Arrays.stream(arr)
    }

}
