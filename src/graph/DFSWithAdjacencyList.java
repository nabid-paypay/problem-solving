package graph;

import javax.crypto.spec.PSource;
import java.lang.reflect.Array;
import java.util.*;


public class DFSWithAdjacencyList {

    public void dfs(Node node) {
        System.out.print(node.data + " ");
        List<Node> neighbours = node.getNeighbours();
        node.visited = true;
        for (int i = 0; i < neighbours.size(); i++) {
            Node n = neighbours.get(i);
            if (n != null && !n.visited) {
                dfs(n);
            }
        }
    }

    public void dfsUsingStack(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.add(node);
        while (!stack.isEmpty()) {
            Node element = stack.pop();
            if (!element.visited) {
                System.out.print(element.data + " ");
                element.visited = true;
            }

            List<Node> neighbours = element.getNeighbours();
            for (int i = 0; i < neighbours.size(); i++) {
                Node n = neighbours.get(i);
                if (n != null && !n.visited) {
                    stack.add(n);
                }
            }
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] sub : prerequisites) {
            adj.putIfAbsent(sub[0], new ArrayList<>());
            adj.get(sub[0]).add(sub[1]);
        }
        boolean[] visited = new boolean[numCourses];
        boolean[] cycleCheck = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if(!visited[i] && _dfs(adj, visited, i, cycleCheck)) return false;
        }
        return true;
    }

    private boolean _dfs(Map<Integer, List<Integer>> adj, boolean[] visited, int src, boolean[] cycleCheck) {
        if(cycleCheck[src]) {
            return true;
        }
        visited[src] = true;
        cycleCheck[src] = true;
        if (adj.containsKey(src)) {
            for (int node : adj.get(src)) {
                if(_dfs(adj, visited, node, cycleCheck)) return true;
            }
        }

        cycleCheck[src] = false;
        return false;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] sub : prerequisites) {
            adj.putIfAbsent(sub[1], new ArrayList<>());
            adj.get(sub[1]).add(sub[0]);
        }
        boolean[] visited = new boolean[numCourses];
        boolean[] cycleCheck = new boolean[numCourses];
        LinkedList<Integer> topSort = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if(!visited[i] && dfsTopSort(adj, visited, i, cycleCheck, topSort)) return new int[]{};
        }
        //int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
        //.filter(Objects::nonNull) also works
       // int[] arr = list.stream().filter(i -> i != null).mapToInt(i -> i).toArray();
        return topSort.stream().mapToInt(i-> i).toArray();
    }

    private boolean dfsTopSort(Map<Integer, List<Integer>> adj, boolean[] visited, int src, boolean[] cycleCheck,
                               LinkedList<Integer> topSort) {
        if(cycleCheck[src]) {
            return true;
        }
        if(visited[src]) return false;
        visited[src] = true;
        cycleCheck[src] = true;
        if (adj.containsKey(src)) {
            for (int node : adj.get(src)) {
                if(dfsTopSort(adj, visited, node, cycleCheck, topSort)) return true;
            }
        }

        cycleCheck[src] = false;
        topSort.addFirst(src);
        return false;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Map<String, String> wordsMap = new HashMap<>();
        for (String word : words){
            wordsMap.put(word, word);
        }
        int rows = board.length, cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        List<String> result = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String str = String.valueOf(board[i][j]);
                if(!visited[i][j]) dfsFindWords(board, wordsMap, result, "", i, j, visited);
            }
        }
        return result;
    }

    private void dfsFindWords(char[][] board, Map<String, String> wordsMap, List<String> result,
                              String genStr, int i , int j, boolean[][] visited) {
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || visited[i][j])  return;
        genStr = genStr+ board[i][j];
        if(wordsMap.containsKey(genStr)) result.add(genStr);
        visited[i][j] = true;
        dfsFindWords(board, wordsMap, result, genStr, i+1, j, visited);
        dfsFindWords(board, wordsMap, result, genStr, i-1, j, visited);
        dfsFindWords(board, wordsMap, result, genStr, i, j+1, visited);
        dfsFindWords(board, wordsMap, result, genStr, i, j-1, visited);

    }

    public int countComponents(int n, int[][] edges) {
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int[] edge: edges){
            map.putIfAbsent(edge[0], new HashSet<>());
            map.putIfAbsent(edge[1], new HashSet<>());

            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i <n ; i++) {
            if(!map.containsKey(i)) count++;
            else if(!visited[i]){
                simpleDFS(visited, map, i);
                count++;
            }
        }
        return count;
    }

    private void simpleDFS(boolean[] visited, Map<Integer, Set<Integer>> map, int node) {
        if(visited[node]) return;
        visited[node] = true;

        for (int elem : map.get(node)){
            if(!visited[elem]){
                simpleDFS(visited, map, elem);
            }
        }
    }

    public int numDistinctIslands(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        Set<Set<String>> result = new HashSet<>();

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(grid[i][j] == 0 || visited[i][j]) continue;
                Set<String> path = dfs(visited, grid, i, j, new HashSet<>(), i, j);
                result.add(path);
            }
        }
        return result.size();
    }
    int[][] dirs = {{-1, 0}, {1, 0}, {0, 1},{0, -1}};
    private Set<String> dfs(boolean[][] visited, int[][] grid, int row, int col, Set<String> path, int startRow, int startCol){
        if(visited[row][col]) return path;
        visited[row][col] = true;
        String temp = (row - startRow) + "," + (col - startCol);
        path.add(temp);
        for(int[] dir : dirs){
            int nx = dir[0] + row;
            int ny = dir[1] + col;

            if(nx<grid.length && nx>=0 && ny>=0 && ny<grid[0].length && !visited[nx][ny] && grid[nx][ny]==1){
                dfs(visited, grid, nx, ny, path, startRow, startCol);
            }
        }
        return path;
    }

    public static void main(String[] args) {
        DFSWithAdjacencyList obj = new DFSWithAdjacencyList();

       obj.findWords(new char[][]{
               {'o','a','a','n'},
               {'e','t','a','e'},
               {'i','h','k','r'},
               {'i','f','l','v'}}, new String[]{"oath","pea","eat","rain"});
    }
}
