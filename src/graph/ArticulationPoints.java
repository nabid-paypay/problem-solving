package graph;
import java.util.*;

public class ArticulationPoints {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int[] low = new int[n];
        int[] disc = new int[n];
        List<List<Integer>> result = new ArrayList<>();
        Arrays.fill(disc, -1);

        for (List<Integer> conn : connections){
            int from = conn.get(0);
            int to = conn.get(1);
            graph.putIfAbsent(from, new HashSet<>());
            graph.putIfAbsent(to, new HashSet<>());

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        for (int i = 0; i <n ; i++) {
            if(disc[i]==-1){
                dfs(i, graph, result, low, disc, i);
            }
        }

        return result;
    }
    int timer = 0;
    public static class test{
        int val;

        public test(int val) {
            this.val = val;
        }
    }
    private void dfs(int u, Map<Integer, Set<Integer>> graph, List<List<Integer>> result,
                     int[] low, int[] disc, int parent) {


        Map<Integer, test> test = new HashMap<>();
        disc[u] = low[u] = ++timer;
        if(graph.containsKey(u)){
            for (int v : graph.get(u)){
                if(v == parent) continue; // if parent vertex, ignore

                if(disc[v] == -1){ // if not discovered
                    dfs(v, graph, result, low, disc, u);
                    low[u] = Math.min(low[u], low[v]);
                    if(low[v] > disc[u]){
                        // u - v is critical, there is no path for v to reach back to u or previous vertices of u
                        result.add(Arrays.asList(u, v));
                    }
                }
                else { // if v discovered and is not parent of u, update low[u], cannot use low[v] because u is not subtree of v
                    low[u] = Math.min(low[u], disc[v]);
                }
            }
        }
    }

    public static void main(String[] args) {
       /* LinkedList<Integer> ll = new LinkedList<>();
        ll.addLast(12);
        ll.addLast(13);
        ll.addLast(14);
        System.out.println(ll.peekLast());*/

        Deque<Integer> dq = new ArrayDeque<Integer>();
        dq.add(12);
        dq.add(13);
        System.out.println(dq.poll());
    }

}
