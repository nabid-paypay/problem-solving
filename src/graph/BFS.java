package graph;

import java.util.*;

public class BFS {
    public Node bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> map = new HashMap<>();
        queue.add(node);
        map.put(node, new Node(node.data));

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (Node nei : curr.neighbours) {
                if (!map.containsKey(nei)) {
                    map.put(nei, new Node(nei.data));
                    queue.add(nei);
                }
                map.get(curr).neighbours.add(map.get(nei));
            }
        }
        return map.get(node);
    }

    public int[] findOrderByKahnAlgo(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int edge[] : prerequisites) {
            adj.putIfAbsent(edge[1], new ArrayList<>());
            adj.get(edge[1]).add(edge[0]);
        }
        int[] indegree = new int[numCourses];

        for (List<Integer> list : adj.values()) {
            for (int i = 0; i < list.size(); i++) {
                indegree[list.get(i)]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        List<Integer> topoSort = new ArrayList<>();
        int count = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (adj.containsKey(node)) {
                for (int edge : adj.get(node)) {
                    indegree[edge]--;
                    if (indegree[edge] == 0) queue.add(edge);
                }
            }
            topoSort.add(node);
            count++;
        }
        if (count != numCourses) return new int[]{};

        return topoSort.stream().mapToInt(Integer::intValue).toArray();

    }

    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);
        Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            adj.putIfAbsent(edge[0], new HashMap<>());
            adj.putIfAbsent(edge[1], new HashMap<>());
            adj.get(edge[0]).put(edge[1], edge[1]);
            adj.get(edge[1]).put(edge[0], edge[0]);
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() == 1) leaves.add(i);
        }
        while (n > 2) {
            n -= leaves.size();
            List<Integer> myLeaves = new ArrayList<>();
            for (int node : leaves) {
                int j = (int) adj.get(node).values().toArray()[0];
                adj.get(j).remove(node);
                if (adj.get(j).size() == 1) myLeaves.add(j);
            }
            leaves = myLeaves;
        }
        return leaves;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);

        List<Set<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) adj.add(new HashSet<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; ++i)
            if (adj.get(i).size() == 1) leaves.add(i);

        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int i : leaves) {
                int j = adj.get(i).iterator().next();
                adj.get(j).remove(i);
                if (adj.get(j).size() == 1) newLeaves.add(j);
            }
            leaves = newLeaves;
        }
        return leaves;
    }


    public int orangesRotting(int[][] grid) {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int time = -1;
        int freshOrange = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 2){
                    queue.add(new int[]{grid[i][j], i, j});
                }
                else if (grid[i][j] == 1){
                   freshOrange++;
                }
            }
        }
        if(freshOrange == 0) return 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int k = 0; k <size ; k++) {
                int[] elem = queue.poll();
                int val = elem[0];
                int i = elem[1];
                int j = elem[2];
                if (val != 2) continue;

                for (int[] dir : dirs) {
                    int row = grid.length;
                    int col = grid[0].length;
                    int nx = dir[0] + i;
                    int ny = dir[1] + j;

                    if (nx >= 0 && nx < row && ny >= 0 && ny < col && grid[nx][ny] == 1) {
                        grid[nx][ny] = 2;
                        queue.add(new int[]{grid[nx][ny], nx, ny});
                        freshOrange--;
                    }
                }
            }

            time++;
        }

        return freshOrange == 0? time : -1;
    }
    public class Pair<K, V> {

        private final K element0;
        private final V element1;

        public  <K, V> Pair<K, V> createPair(K element0, V element1) {
            return new Pair<K, V>(element0, element1);
        }

        public Pair(K element0, V element1) {
            this.element0 = element0;
            this.element1 = element1;
        }

        public K getKey() {
            return element0;
        }

        public V getValue() {
            return element1;
        }

    }
    int l;
    Map<String, List<String>> combDict = new HashMap<>();
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        this.l = beginWord.length();
        wordList.forEach(
                word -> {
                    for(int i=0; i<l; i++){
                        String newWord = word.substring(0, i) + "*" + word.substring(i+1, l);
                        List<String> transformations = combDict.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        combDict.put(newWord, transformations);
                    }
                }
        );

        LinkedList<Pair<String, Integer>> qBegin = new LinkedList<>();
        qBegin.add(new Pair(beginWord, 1));
        Map<String, Integer> beginVisited = new HashMap<>();
        beginVisited.put(beginWord, 1);

        LinkedList<Pair<String, Integer>> qEnd = new LinkedList<>();
        qEnd.add(new Pair(endWord, 1));
        Map<String, Integer> endVisited = new HashMap<>();
        endVisited.put(endWord, 1);

        while (!qBegin.isEmpty() && !qEnd.isEmpty()){
            int ans = visitedNode(qBegin, beginVisited, endVisited);
            if(ans > -1) return ans;

            ans = visitedNode(qEnd, endVisited, beginVisited);
            if(ans > -1) return ans;
        }
        return 0;
    }

    private int visitedNode(LinkedList<Pair<String, Integer>> q, Map<String, Integer> visited, Map<String, Integer> otherVisited) {
        Pair<String, Integer> node = q.poll();
        String word = node.getKey();
        int level = node.getValue();

        for(int i=0; i<l; i++){
            String newWord = word.substring(0,i) + "*" + word.substring(i+1, l);

            for(String adjWord: combDict.getOrDefault(newWord, new ArrayList<>())){
                if(otherVisited.containsKey(adjWord)){
                    return level + otherVisited.get(adjWord);
                }

                if(!visited.containsKey(adjWord)){
                    q.add(new Pair(adjWord, level+1));
                    visited.put(adjWord, level+1);
                }
            }
        }
        return -1;
    }

    public static void main (String[]args){
            BFS obj = new BFS();
            int res1 = obj.orangesRotting(new int[][]{{2,1,1},
                    {1,1,0},
                    {0,1,1}});
            System.out.println(res1);

            int res2 = obj.orangesRotting(new int[][]{{2,1,1},
                    {0,1,1},
                    {1,0,1}});
            System.out.println(res2);

            int res3 = obj.orangesRotting(new int[][]{{0,2}});

            System.out.println(res3);
        }
    }
