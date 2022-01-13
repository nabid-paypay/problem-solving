import java.util.*;

public class Contest {
    Map<Integer, List<Integer>> map = new HashMap<>();
    public int maximumDetonation(int[][] bombs) {

        for(int i=0; i<bombs.length; i++){
            int[] b1 = bombs[i];
            for(int j=i+1; j<bombs.length; j++){
                int[] b2 = bombs[j];
                if(isInside(b1[0], b1[1], b1[2], b2[0], b2[1])){
                    map.putIfAbsent(i, new ArrayList<>());
                    map.putIfAbsent(j, new ArrayList<>());

                    map.get(i).add(j);
                }
            }
        }

        int max = 0;
        for(int i=0; i<bombs.length; i++){
            int k = dfs(i, new boolean[bombs.length]);
            max = Math.max(max, k);
        }

        return max;
    }

    private int dfs(int node, boolean[] vs){
        if(vs[node]){
            return 0;
        }

        vs[node] = true;
        int res = 1;
        for(int nei : map.getOrDefault(node, new ArrayList<>())){
            //res++;
            res += dfs(nei, vs);
        }

        return res;
    }


    static boolean isInside(int circle_x, int circle_y,
                            int rad, int x, int y)
    {
        // Compare radius of circle with
        // distance of its center from
        // given point
        if ((x - circle_x) * (x - circle_x) +
                (y - circle_y) * (y - circle_y) <= rad * rad)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        Contest contest = new Contest();
        contest.maximumDetonation(new int[][]{{2,1,3},{6,1,4}});
        List<String> res = new ArrayList<>();
        PriorityQueue<String> pq = new PriorityQueue<>();
        res.addAll(pq);
        TreeMap<Integer, List<String>> treeMap = new TreeMap<>(Comparator.reverseOrder());

        Iterator<Map.Entry<Integer, List<String>>> iterator = treeMap.entrySet().iterator();
         TreeSet<Integer> locations = new TreeSet<>();
        //iterator.next()
    }
}
