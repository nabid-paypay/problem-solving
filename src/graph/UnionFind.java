package graph;

import java.util.*;

public class UnionFind {


    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;
        int component = n - 1;
        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        for (int[] connection : connections) {

            int p1 = findParent(connection[0], parent);
            int p2 = findParent(connection[1], parent);

            if (p1 != p2) {
                component--;
                if (size[p1] < size[p2]) {
                    parent[p1] = p2;
                    size[p2] += size[p1];
                } else {
                    parent[p2] = p1;
                    size[p1] += size[p2];
                }
            }
        }
        return component;
    }

    public int findParent(int i, int[] parent) {
        if (parent[i] == i) return i;
        return parent[i] = findParent(parent[i], parent);
    }

    public int findCircleNum(int[][] M) {
        int row = M.length;
        int[] parent = new int[row];
        int[] size = new int[row];
        for (int i = 0; i < row; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        int circleCount = row;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if (i != j) {
                    if (M[i][j] == 1) {
                        int p1 = findParent(i, parent);
                        int p2 = findParent(j, parent);
                        if (p1 == p2) continue;

                        if (size[p1] < size[p2]) {
                            parent[p1] = p2;
                            size[p2] += size[p1];
                        } else {
                            parent[p2] = p1;
                            size[p1] += size[p2];
                        }

                        circleCount--;
                    }
                }
            }
        }
        System.out.println(circleCount);
        return circleCount;
    }

    public int numIslands(char[][] grid) {
        int[][] dirs = {{0, 1}, {-1, 0}};
        int row = grid.length;
        int col = grid[0].length;
        int[] parent = new int[row*col];
        int[] size = new int[row*col];
        for (int i = 0; i < row*col; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        int islandCount = 0;
        int unified = 0;
        int carry = col;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    islandCount++;
                    for (int[] dir :dirs){
                        int nx = dir[0] + i;
                        int ny = dir[1] + j;
                        if(nx>=0 && nx<row && ny>=0 && ny<col && grid[nx][ny] == '1'){
                            int a = i*carry + j;
                            int b = nx*carry + ny;
                            int p1 = findParent(a, parent);
                            int p2 = findParent(b, parent);
                            if (p1 == p2) continue;

                            if (size[p1] < size[p2]) {
                                parent[p1] = p2;
                                size[p2] += size[p1];
                            } else {
                                parent[p2] = p1;
                                size[p1] += size[p2];
                            }
                            //islandCount--;
                            unified++;
                        }
                    }
                }
            }
        }
        System.out.println(islandCount-unified);
        return islandCount-unified;
    }

    public int minimumCost(int N, int[][] conn) {
        if(conn.length < N-1) return -1;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a-> a[2]));

        for(int[] con : conn){
            pq.addAll(Collections.singleton(con));
        }

        return -1;

    }


    public static void main(String[] args) {
        UnionFind obj = new UnionFind();
        obj.numIslands(new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}});

        obj.numIslands(new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        });

        obj.numIslands(new char[][]{
                {'1','0','1','1','1'},
                {'1','0','1','0','1'},
                {'1','1','1','0','1'}});
    }
}
