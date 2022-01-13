package ds_mania;
import java.util.*;
public class LFUCacheDLL {
    class Node{
        int key,value,count;
        Node prev,next;
        Node(int key, int val) {
            this.key = key;
            this.value = val;
            count = 1;
        }
    }

    class DLList{
        Node head, tail;
        int size;
        DLList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        private void addNode(Node node){
            //Always add the new node right after head.
            node.prev = head;
            node.next = head.next;

            node.next.prev = node;
            head.next = node;
            size++;
        }

        private void removeNode(Node node){
            Node prev = node.prev;
            Node next = node.next;

            prev.next = next;
            next.prev = prev;
            size--;
        }

        private void moveToHead(Node node){
            removeNode(node);
            addNode(node); //addNode always adds node to the head
        }

        private Node popTail(){
            if (size > 0) {
                Node node = tail.prev;
                removeNode(node);
                return node;
            }
            else return null;
        }

    }

    int capacity, size, min;
    Map<Integer, Node> nodeMap;
    Map<Integer, DLList> countMap;

    public LFUCacheDLL(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>();
        countMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = nodeMap.get(key);
        if (node == null) return -1;
        update(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            node.value = value;
            update(node);
        }
        else {
            Node node = new Node(key, value);
            nodeMap.put(key, node);
            if (size == capacity) {
                DLList lastList = countMap.get(min);
                nodeMap.remove(lastList.popTail().key);
                size--;
            }
            size++;
            min = 1;
            DLList newList = countMap.getOrDefault(node.count, new DLList());
            newList.addNode(node);
            countMap.put(node.count, newList);
        }
    }

    private void update(Node node) {
        DLList oldList = countMap.get(node.count);
        oldList.removeNode(node);
        if (node.count == min && oldList.size == 0) min++;
        node.count++;
        DLList newList = countMap.getOrDefault(node.count, new DLList());
        newList.addNode(node);
        countMap.put(node.count, newList);
    }

}
