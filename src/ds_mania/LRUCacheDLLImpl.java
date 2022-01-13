package ds_mania;
import java.util.*;

public class LRUCacheDLLImpl {
    class DLLNode{
        int key;
        int value;
        DLLNode prev;
        DLLNode next;
    }

    private void addNode(DLLNode node){
        //Always add the new node right after head.
        node.prev = head;
        node.next = head.next;

        node.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLLNode node){
        DLLNode prev = node.prev;
        DLLNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(DLLNode node){
        removeNode(node);
        addNode(node); //addNode always adds node to the head
    }

    private DLLNode popTail(){
        DLLNode deletedTail = tail.prev;
        removeNode(deletedTail);

        return deletedTail;
    }



    private int capacity;
    private int size;
    private Map<Integer, DLLNode> cache = new HashMap<>();
    DLLNode head, tail;

    public LRUCacheDLLImpl(int capacity) {
        this.capacity = capacity;
        size = 0;
        head = new DLLNode();
        tail = new DLLNode();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLLNode node = cache.get(key);
        if(node == null) return -1;

        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLLNode node = cache.get(key);
        if(node == null){
            DLLNode newNode = new DLLNode();
            newNode.key = key;
            newNode.value = value;

            cache.put(key, newNode);
            addNode(newNode);
            ++size;

            if(size > capacity){
                DLLNode tail = popTail();
                cache.remove(tail.key);
                size--;
            }
        }
        else {
            node.value = value;
            moveToHead(node);
        }
    }

    public static void main(String[] args) {
        LRUCacheDLLImpl lRUCache = new LRUCacheDLLImpl(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
    }
}
