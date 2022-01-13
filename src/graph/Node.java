package graph;

import java.util.*;

public class Node implements Comparator<Node> {
    int data;
    boolean visited;
    List<Node> neighbours;
    int color = -1;
    int cost;

    Node(int data) {
        this.data = data;
        this.neighbours = new ArrayList<>();
    }
    Node(){

    }

    public Node(int node, int cost) {
        this.data = node;
        this.cost = cost;
    }

    public void addNeighbours(Node neighbourNode) {
        this.neighbours.add(neighbourNode);
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours;
    }

    @Override  // ** don't forget this annotation
    public int hashCode() { // *** note capitalization of the "C"
        return this.data;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Node && ((Node) obj).data == this.data);
    }

    @Override
    public int compare(Node node1, Node node2) {
        return Integer.compare(node1.cost, node2.cost);
    }

}
