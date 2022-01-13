package graph;

import java.util.*;

public class DFSWithAdjacencyMatrix {
    static ArrayList<Node> nodes = new ArrayList<>();

    public ArrayList<Node> findNeighbours(int adjacency_matrix[][], Node x) {
        int nodeIndex = -1;

        ArrayList<Node> neighbours = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).equals(x)) {
                nodeIndex = i;
                break;
            }
        }

        if (nodeIndex != -1) {
            for (int j = 0; j < adjacency_matrix[nodeIndex].length; j++) {
                if (adjacency_matrix[nodeIndex][j] == 1) {
                    neighbours.add(nodes.get(j));
                }
            }
        }
        return neighbours;
    }

    public void dfsRec(int adjacency_matrix[][], Node node) {

        //  System.out.print(node.data + " ");
        ArrayList<Node> neighbours = findNeighbours(adjacency_matrix, node);
        node.visited = true;
        for (int i = 0; i < neighbours.size(); i++) {
            Node n = neighbours.get(i);
            if (n != null && !n.visited) {
                dfsRec(adjacency_matrix, n);
            }
        }
    }

    boolean isBipartite = true;

    public void dfsRec(int adjacency_matrix[][], Node node, int color) {
        //  System.out.print(node.data + " ");
        ArrayList<Node> neighbours = findNeighbours(adjacency_matrix, node);
        node.visited = true;
        node.color = color;
        for (int i = 0; i < neighbours.size(); i++) {
            Node n = neighbours.get(i);
            if (n.color == color) isBipartite = false;
            if (n != null && !n.visited) {
                if (n.color == color) isBipartite = false;
                dfsRec(adjacency_matrix, n, color ^ 1);
            }
        }
    }

    public void dfsUsingStack(int adjacency_matrix[][], Node node) {
        Stack<Node> stack = new Stack<>();
        stack.add(node);

        while (!stack.isEmpty()) {
            Node element = stack.pop();
            if (!element.visited) {
                System.out.print(element.data + " ");
                element.visited = true;
            }

            ArrayList<Node> neighbours = findNeighbours(adjacency_matrix, element);
            for (int i = 0; i < neighbours.size(); i++) {
                Node n = neighbours.get(i);
                if (n != null && !n.visited) {
                    stack.add(n);
                }
            }
        }
    }

    public static void clearVisitedFlags() {
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).visited = false;
            nodes.get(i).color = -1;
        }
    }

    public void print(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    List<List<Integer>> graph = new ArrayList<>();
    Map<Integer, Integer> color = new HashMap<>();
    ;

    public boolean possibleBipartition(int N, int[][] dislikes) {

        for (int i = 1; i <= N + 1; i++) {
            graph.add(new ArrayList());
        }

        for (int[] edge : dislikes) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        for (int node = 1; node <= N; ++node) {
            if (!color.containsKey(node) && !dfs(node, 0))
                return false;
        }
        return true;
    }

    public boolean dfs(int node, int c) {
        if (color.containsKey(node))
            return color.get(node) == c;
        color.put(node, c);

        for (int nei : graph.get(node)) {
            if (!dfs(nei, c ^ 1)) return false;
        }
        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {              //This graph might be a disconnected graph. So check each unvisited node.
            if (colors[i] == 0 && !validColor(graph, colors, 1, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean validColor(int[][] graph, int[] colors, int color, int node) {
        if (colors[node] != 0) {
            return colors[node] == color;
        }
        colors[node] = color;
        for (int next : graph[node]) {
            if (!validColor(graph, colors, -color, next)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfsForBipartiteCheck(int node, int color, Map<Integer, Integer> map,
                                         List<List<Integer>> graph) {
        if (map.containsKey(node))
            return map.get(node) == color;
        map.put(node, color);

        for (int nei : graph.get(node)) {
            if (!dfsForBipartiteCheck(nei, color ^ 1, map, graph))
                return false;
        }
        return true;
    }

    public boolean isBipartiteByBFS(int[][] graph) {
        int[] colors = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (colors[i] != 0) continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            colors[i] = 1;
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (int elem : graph[curr]) {
                    if (colors[elem] == 0) {
                        colors[elem] = -colors[curr];
                        queue.offer(elem);
                    } else if (colors[elem] != -colors[curr]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;

        int[][] cache = new int[row][col];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int len = dfsForIncreasingPath(cache, matrix, i, j);
                max = Math.max(max, len);
            }
        }
        return max;
    }

    private int dfsForIncreasingPath(int[][] cache, int[][] matrix, int x, int y) {
        if (cache[x][y] != 0) {
            System.out.println("found cache");
            return cache[x][y];
        }
        int row = matrix.length, col = matrix[0].length;
        int max = 1;
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < row && ny >= 0 && ny < col && matrix[x][y] > matrix[nx][ny]) {
                int len = 1 + dfsForIncreasingPath(cache, matrix, nx, ny);
                max = Math.max(max, len);
            }
        }
        cache[x][y] = max;
        return max;
    }

    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(grid[i][j] == '1' && !visited[i][j]) {
                    dfsNumIslands(grid, visited, i, j);
                    count++;
                }
            }
        }
        System.out.println(count);
        return count;
    }

    private void dfsNumIslands(char[][] grid, boolean[][] visited, int i, int j) {
        if(visited[i][j]) return;
        for (int[] dir : dirs){
            visited[i][j] = true;
            int row = grid.length;
            int col = grid[0].length;
            int nx = dir[0] + i;
            int ny = dir[1] + j;
            if(nx>=0 && nx<row && ny>=0 && ny<col && grid[nx][ny] == '1' && !visited[nx][ny]){
                dfsNumIslands(grid, visited, nx, ny);
            }
        }
    }

    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    int area = dfsMaxAreaOfIsland(grid, visited, i, j);
                    count = Math.max(count, area);
                }
            }
        }
       // System.out.println(count);
        return count;
    }
    private int dfsMaxAreaOfIsland(int[][] grid, boolean[][] visited, int i, int j) {
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || visited[i][j] || grid[i][j] == 0)  return 0;
        visited[i][j] = true;
        return 1 + dfsMaxAreaOfIsland(grid, visited, i+1, j) +
                   dfsMaxAreaOfIsland(grid, visited, i-1, j) +
                   dfsMaxAreaOfIsland(grid, visited, i, j+1) +
                   dfsMaxAreaOfIsland(grid, visited, i, j-1);
    }

    public static void main(String[] args) {
        DFSWithAdjacencyMatrix obj = new DFSWithAdjacencyMatrix();

        obj.maxAreaOfIsland(new int[][]{
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}});

        obj.maxAreaOfIsland(new int[][]{
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}});
        obj.maxAreaOfIsland(new int[][]{
                {1, 0, 1},
                {1, 1, 1},
                {0, 0, 1}
        });

        obj.maxAreaOfIsland(new int[][]{
                {1,1,0,1,1},
                {1,0,0,0,0},
                {0,0,0,0,1},
                {1,1,0,1,1}
        });

    }
}
