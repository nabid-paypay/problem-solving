package oop.rebirth.strategy;

public class Dijkstra implements Strategy{

    @Override
    public String findShortestPath(int[][] graph, int node) {
        return "shortest path for node: " + node + " is dijkstra";
    }
}
