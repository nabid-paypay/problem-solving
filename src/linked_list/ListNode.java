package linked_list;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}

   /* @Override
    public String toString() {
        *//*return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';*//*
        return "";
    }*/

    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
