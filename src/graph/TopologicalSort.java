package graph;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.*;

public class TopologicalSort {

    public int[] KahnAlgoForTopological(int numCourses, int[][] prerequisites) {
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

    public int longestIncreasingPath(int[][] matrix) {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] indegree = new int[row][col];
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                for (int[] dir : dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx >= 0 && nx < row && ny >= 0 && ny < col) {
                        if (matrix[x][y] > matrix[nx][ny]) indegree[x][y]++;
                    }
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (indegree[i][j] == 0) queue.offer(new int[]{i, j});
            }
        }
        int length = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                int x = point[0];
                int y = point[1];
                for (int[] dir : dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx >= 0 && nx < row && ny >= 0 && ny < col) {
                        if (matrix[x][y] < matrix[nx][ny] && --indegree[nx][ny] == 0) {
                            queue.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
            length++;
        }
        return length;
    }

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < group.length; i++) {
            groups.putIfAbsent(group[i], new ArrayList<>());
            groups.get(group[i]).add(i);
        }

        Map<Integer, List<Integer>> beforeItemsGraph = new HashMap<>();
        for (int i = 0; i < beforeItems.size(); i++) {
            beforeItemsGraph.putIfAbsent(i, new ArrayList<>());
            for (int j = 0; j < beforeItems.get(i).size(); j++) {
                int item = beforeItems.get(i).get(j);
                beforeItemsGraph.putIfAbsent(item, new ArrayList<>());
                beforeItemsGraph.get(item).add(i);
            }
        }

        int[] indegree = new int[n];

        for (List<Integer> list : beforeItemsGraph.values()) {
            for (int i = 0; i < list.size(); i++) {
                indegree[list.get(i)]++;
            }
        }

        //Queue<Integer> queue = new ArrayDeque<>();
        LinkedList<Integer> ll = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) ll.add(i);
        }

        List<Integer> topoSort = new ArrayList<>();
        int count = 0;
        while (!ll.isEmpty()) {
            int node = ll.removeFirst();
            if (beforeItemsGraph.containsKey(node)) {
                for (int edge : beforeItemsGraph.get(node)) {
                    indegree[edge]--;
                    if (indegree[edge] == 0) ll.addFirst(edge);
                }
            }
            topoSort.add(node);
            count++;
        }
        if (count != n) return new int[]{};



        return topoSort.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] sortItems2(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        // Item dependency graph creation.
        Map<Integer,Set<Integer>> itemGraph = new HashMap<>();
        Map<Integer,Integer> itemInDegree = new HashMap<>();

        for (int i=0;i<n;i++) {
            itemGraph.putIfAbsent(i,new HashSet<>());
        }

        // Group dependency graph creation
        Map<Integer,Set<Integer>> groupGraph = new HashMap<>();
        Map<Integer,Integer> groupInDegree = new HashMap<>();

        for (int g : group) {
            groupGraph.putIfAbsent(g,new HashSet<>());
        }

        for (int i=0;i<beforeItems.size();i++) {
            List<Integer> list = beforeItems.get(i);
            if(list.size()!=0) {
                for (int val : list) {
                    itemGraph.get(val).add(i);
                    itemInDegree.put(i,itemInDegree.getOrDefault(i,0)+1);
                    // If an item(i1) is dependent on another(i2) then its group(g1) should also be dependent on (g2)
                    int g1 = group[val];
                    int g2 = group[i];
                    if (g1 != g2 && groupGraph.get(g1).add(g2)) {
                        groupInDegree.put(g2,groupInDegree.getOrDefault(g2,0)+1);
                    }
                }
            }
        }

        List<Integer> itemOrdering = topologicalSort(itemGraph, itemInDegree, n);
        List<Integer> groupOrdering = topologicalSort(groupGraph, groupInDegree, groupGraph.size());

        // In case we find a cycle.
        if(itemOrdering.size()==0 || groupOrdering.size()==0) {
            return new int[0];
        }

        Map<Integer,List<Integer>> map = new HashMap<>();

        // Put items in respective buckets.
        for (int item : itemOrdering) {
            int g = group[item];
            map.putIfAbsent(g,new ArrayList<>());
            map.get(g).add(item);
        }

        int[] result = new int[n];
        int i=0;

        // Get result, by looping over group ordering.
        for (int g : groupOrdering) {
            List<Integer> list = map.get(g);
            for (int item : list) {
                result[i] = item;
                i++;
            }
        }

        return result;

    }

    // Kahn’s algorithm
    private List<Integer> topologicalSort(Map<Integer,Set<Integer>> graph,
                                          Map<Integer,Integer> inDegree,int count) {

        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int key : graph.keySet()) {
            if(inDegree.getOrDefault(key,0)==0) {
                queue.add(key);
            }
        }

        while (!queue.isEmpty()) {
            int pop = queue.poll();
            count--;
            result.add(pop);
            for (int next : graph.get(pop)) {
                int val = inDegree.get(next);
                inDegree.put(next,val-1);
                if(inDegree.get(next) ==0) {
                    queue.add(next);
                }
            }
        }
        return count==0 ? result : new ArrayList<>();

    }

    public int[] sortItemsPain(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        Map<Integer, Set<Integer>> itemGraph = new HashMap<>();
        Map<Integer,Integer> itemInDegree = new HashMap<>();

        for (int i=0;i<n;i++) {
            itemGraph.putIfAbsent(i,new HashSet<>());
        }

        Map<Integer, Set<Integer>> groupGraph = new HashMap<>();
        Map<Integer,Integer> groupIndegree = new HashMap<>();

        for (int g : group) {
            groupGraph.putIfAbsent(g,new HashSet<>());
        }


        for (int i = 0; i < beforeItems.size(); i++) {
            List<Integer> list = beforeItems.get(i);
            if(list.size() == 0) continue;
            for (int val : list){
                itemGraph.get(val).add(i);
                itemInDegree.put(i,itemInDegree.getOrDefault(i,0)+1);

                int g1 = group[val];
                int g2 = group[i];

                if(g1!=g2 && groupGraph.get(g1).add(g2)){
                    groupIndegree.put(g2,groupIndegree.getOrDefault(i,0)+1);
                }
            }
        }

        List<Integer> itemOrdering = topologicalSort(itemGraph, itemInDegree, n);
        List<Integer> groupOrdering = topologicalSort(groupGraph, groupIndegree, groupGraph.size());

        // In case we find a cycle.
        if(itemOrdering.size()==0 || groupOrdering.size()==0) {
            return new int[0];
        }

        Map<Integer, List<Integer>> mapList = new HashMap<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int item : itemOrdering){
            int g = group[item];
            mapList.putIfAbsent(g, new ArrayList<>());
            map.putIfAbsent(g, new HashSet<>());
            map.get(g).add(item);
            mapList.get(g).add(item);
        }

        int[] result = new int[n];
        int i=0;

        // Get result, by looping over group ordering.
        for (int g : groupOrdering) {
            //List<Integer> list = map.get(g);
            Set<Integer> list = map.get(g);
            for (int item : list) {
                result[i] = item;
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TopologicalSort obj = new TopologicalSort();
        /*List<List<Integer>> list = Arrays.asList( new ArrayList<>(), Arrays.asList(6),
                Arrays.asList(5),
                Arrays.asList(6),
                Arrays.asList(3,6),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        int[] blah = obj.sortItemsPain(8, 2, new int[]{-1, -1, 1, 0, 0, 1, 0, -1}, list);

        Arrays.stream(blah).forEach(elem-> System.out.print(elem + ","));*/
        //6,3,4,5,2,0,7,1,
        //3,4,6,2,5,0,1,7,

       /* List<List<Integer>> list2 = Arrays.asList( new ArrayList<>(), Arrays.asList(6),
                Arrays.asList(5),
                Arrays.asList(6),
                Arrays.asList(3),
                new ArrayList<>(),Arrays.asList(4), new ArrayList<>());
        int[] blah2 = obj.sortItems2(8, 2, new int[]{-1, -1, 1, 0, 0, 1, 0, -1}, list2);

        Arrays.stream(blah2).forEach(elem-> System.out.print(elem + ","));*/

        List<List<Integer>> list = Arrays.asList( Arrays.asList(3),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                Arrays.asList(1, 3, 2));
        int[] blah = obj.sortItemsPain(5, 3, new int[]{0, 0, 2, 1, 0}, list);

        //Arrays.stream(blah).forEach(elem-> System.out.print(elem + ","));


        int[] groups = new int[]{0, 0, 2, 1, 0};
        List<Integer> ff = Arrays.stream(groups).boxed().collect(Collectors.toList());

       // System.out.println(Integer.MAX_VALUE);
        //int[][] test = new int[][]{{5, -2147483647}, {10, 2147483647}};
        int[][] test = new int[][]{{5, -2147483647}, {10, 2147483647}};
        //System.out.println(-2147483645-2147483647);
       // Arrays.sort(test, Comparator.comparingInt(elem -> elem[1]));
        Arrays.sort(test, (a, b) -> a[1] - b[1]);
       // System.out.println(Arrays.deepToString(test));

        System.out.println(obj.longestIncreasingPath(new int[][]{
                {9,9,4},
                {6,6,8},
                {2,1,1}
        }));


    }
}
