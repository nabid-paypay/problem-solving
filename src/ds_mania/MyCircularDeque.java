package ds_mania;

class MyCircularDeque {
    /** Initialize your data structure here. Set the size of the deque to be k. */
    class DoubleLL{
        int val;
        DoubleLL prev;
        DoubleLL next;

        public DoubleLL(int value){
            val = value;
        }

        @Override
        public String toString() {
            return "DoubleLL{" +
                    "val=" + val +
                    ", prev=" + prev +
                    ", next=" + next +
                    '}';
        }
    }

    int k;
    int size;
    DoubleLL head;
    DoubleLL tail;
    public MyCircularDeque(int k) {
        this.k = k;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(size == k){
            return false;
        }
        if(size == 0){
            head = new DoubleLL(value);
            tail = head;
            //tail = new DoubleLL(value);

            //head.next = tail;
            //tail.prev = head;
        }
        else {
            DoubleLL newHead = new DoubleLL(value);
            newHead.next = head;
            head.prev = newHead;

            head = newHead;
        }

        size++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(size == k){
            return false;
        }
        if(size == 0){
            head = new DoubleLL(value);
            tail = new DoubleLL(value);

            head.next = tail;
            tail.prev = head;
        }
        else {
            DoubleLL newTail = new DoubleLL(value);
            tail.next = newTail;
            newTail.prev = tail;

            tail = newTail;
        }

        size++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(size == 0){
            return false;
        }
        head = head.next;
        size--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        DoubleLL temp = null;
        if(size == 0){
            return false;
        }
        else if(size == 1){
            head = null;
            tail = null;
        }
        else {
            tail = tail.prev;
            tail.next = null;
        }

        size--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        return size == 0 ? -1 : head.val;
    }

    /** Get the last item from the deque. */
    public int getRear() {
        return size == 0 ? -1 : tail.val;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == k;
    }

    public static void main(String[] args) {
        MyCircularDeque dq = new MyCircularDeque(52);
        dq.insertFront(80);
        dq.insertFront(27);
        dq.deleteLast();
        dq.insertFront(60);
        dq.insertFront(81);
        System.out.println(dq.getRear());

    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
