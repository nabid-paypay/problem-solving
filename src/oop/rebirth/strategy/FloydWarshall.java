package oop.rebirth.strategy;

public class FloydWarshall implements Strategy{

    @Override
    public String findShortestPath(int[][] graph, int node) {
        return "shortest path for node: " + node + " is Floyd-Warshall";
    }
}
