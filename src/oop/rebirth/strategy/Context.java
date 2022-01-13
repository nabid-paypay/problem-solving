package oop.rebirth.strategy;

public class Context {
    private final Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public String executeStrategy(int[][] graph, int node){
        return this.strategy.findShortestPath(graph, node);
    }

    public static void main(String[] args) {
        Context dijkstra = new Context(new Dijkstra());
        System.out.println(dijkstra.executeStrategy(new int[][]{}, 5));

        Context floydWarshall = new Context(new FloydWarshall());
        System.out.println(floydWarshall.executeStrategy(new int[][]{}, 5));
    }
}
