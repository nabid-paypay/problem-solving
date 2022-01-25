import java.util.*;

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

    class Node{
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

        return count;
    }



    public static void main(String[] args) {
        Contest contest = new Contest();
        contest.highestRankedKItems(new int[][]{{1,2,0,1},{1,3,0,1},{0,2,5,1}}, new int[]{2, 5}, new int[]{0, 0}, 3);
        //iterator.next()
    }




}
