package linked_list;

import javax.print.attribute.standard.QueuedJobCount;
import java.awt.List;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LinkedListManipulation {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(-1);
        ListNode finalList = l3;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l3.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                l3.next = new ListNode(l2.val);
                l2 = l2.next;
            }

            l3 = l3.next;
        }

        if (l1 != null) l3.next = l1;
        if (l2 != null) l3.next = l2;

        return finalList.next;
    }

    public void print(ListNode list) {
        while (list != null) {
            System.out.print(list.val + " ");
            list = list.next;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
       /* ListNode current = head;
        while (current!=null){
            int val = current.val;
            ListNode temp = current.next;
            while (temp!=null && (val == temp.val)){
               temp = temp.next;
            }
            current.next = temp;
            current = current.next;
        }*/
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else current = current.next;
        }
        return head;
    }

    public ListNode deleteDuplicatesCompletely(ListNode head) {
        ListNode current = head;
        ListNode prev = new ListNode(0);
        ListNode dummy = prev;
        prev.next = head;

        while (current != null) {
            while (current.next != null && prev.next.val == current.next.val) {
                current = current.next;
            }

            if (prev.next == current) {
                prev = prev.next;
            } else {
                prev.next = current.next;
                current = current.next;
            }

        }
        return head;
    }

    public void test(ListNode head) {
        ListNode dummy = head;
        while (head!=null){
           // head.next = head.next.next;
            head = head.next;
        }
        head.next = head.next.next;
        System.out.println("dummy: " + dummy.toString());
        System.out.println("head: " + head);
    }

    //Given 1->2->3->4, you should return the list as 2->1->4->3.
    public ListNode swapPairs(ListNode head) {

        ListNode dummy = new ListNode(0);
        ListNode dummy2 = dummy;
        while (head != null && head.next != null) {
            ListNode current = head;
            ListNode temp = head.next;
            ListNode link = head.next.next;

            current.next = link;
            dummy.next = temp;
            temp.next = current;

            head = head.next;
            dummy = dummy.next.next;
        }
        return dummy2.next;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        int size = findSize(head);
        int i = 0;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null) {
            slow = slow.next;
            fast = fast.next.next;
            i++;

            if (slow == fast) {
                if (i == size) return slow.next;
                else return slow;
            }

        }
        return null;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = findSize(head);
        if (size == n) return head.next;
        ListNode dummy = head;
        for (int i = 0; i < size - n - 1; i++) {
            head = head.next;
        }
        head.next = head.next.next;
        return dummy;
    }

    public int findSize(ListNode head) {
        int i = 0;
        while (head != null) {
            head = head.next;
            i++;
        }
        return i;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        Stack<ListNode> stack = new Stack<>();
        ListNode dummy2 = new ListNode(0);
        dummy2.next = head;
        ListNode dummy3 = dummy2;
        int i = 1;
        while (head != null) {
            if (i == m) {
                ListNode dummy = head;
                while (i != n + 1) {
                    ListNode temp = new ListNode(dummy.val);
                    stack.push(temp);
                    dummy = dummy.next;
                    i++;
                }
            } else {
                head = head.next;
                dummy2 = dummy2.next;
                i++;
            }

            if (stack.size() > 0) {
                ListNode node = new ListNode(0);
                ListNode node2 = node;
                while (!stack.isEmpty()) {
                    ListNode temp = stack.pop();
                    temp.next = null;
                    node.next = temp;
                    node = node.next;
                    head = head.next;
                }

                while (head != null) {
                    node.next = head;
                    node = node.next;
                    head = head.next;
                }

                dummy2.next = node2.next;
            }
        }
        return dummy3.next;
    }

    ListNode reverseRec(ListNode head) {
        if (head.next == null) return head;
        ListNode last = reverseRec(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    ListNode reverseIterativelyWithStack(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        ListNode dummy = new ListNode(-1);
        ListNode dummy2 = dummy;
        while (!stack.isEmpty()) {
            dummy.next = new ListNode(stack.pop());
            dummy = dummy.next;
        }
        return dummy2.next;
    }

    public ListNode reverseIteratively(ListNode head) {
        ListNode current = head;
        ListNode prev = null;

        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }

        return prev;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        ListNode firstHalf = findMiddle(head);
        ListNode secondHalf = reverseIteratively(firstHalf.next);

        boolean result = true;
        ListNode p1 = head;
        ListNode p2 = secondHalf;

        while (result && p2!=null){
            if(p1.val!=p2.val) result = false;
            p1 = p1.next;
            p2 = p2.next;
        }

        firstHalf.next = reverseIteratively(secondHalf);

        return result;

    }

    private ListNode findMiddle(ListNode head) {
       /* ListNode fast = head;
        ListNode slow = head;

        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;*/

        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode fake = new ListNode(0);
        fake.next = head;
        Map<Integer, ListNode> m = new HashMap<>();
        int prefixsum = 0;
        ListNode cur = fake;
        while (cur != null) {
            //System.out.println(cur.val);
            prefixsum += cur.val;
            if (m.containsKey(prefixsum)) {
                m.get(prefixsum).next = cur.next;
                m.clear();
                prefixsum = 0;
                cur = fake;
            } else {
                m.put(prefixsum, cur);
                cur = cur.next;
            }
        }
        return fake.next;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public ListNode oddEvenList(ListNode head) {
        ListNode dummy = head;
        ListNode temp = new ListNode(0);
        ListNode dummyTemp = temp;
        while (head != null) {
            temp.next = head.next;
            if (head.next != null)
                head.next = head.next.next;
            if (head.next == null) {
                head.next = dummyTemp.next;
                break;
            } else head = head.next;
            temp = temp.next;
        }
        return dummy;
    }

    public int[] nextLargerNodes(ListNode head) {

        Queue<Integer> queue = new LinkedList();
        int[] array = new int[findSize(head)];
        int i = 0;
        int peek = Integer.MAX_VALUE;
        while (head != null) {
            int elem = head.val;
            if (elem > peek) {
                while (!queue.isEmpty()) {
                    int xx = queue.poll();
                    if (xx < elem)
                        array[i++] = elem;
                    else array[i++] = 0;
                }
            }
            queue.add(elem);
            peek = elem;
            head = head.next;
        }
        return array;
    }

    public int[] nextLargerNodes2(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        for (ListNode node = head; node != null; node = node.next)
            list.add(node.val);
        int[] res = new int[list.size()];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < list.size(); ++i){
            while (!stack.isEmpty() && list.get(stack.peek()) < list.get(i))
                res[stack.pop()] = list.get(i);
            //only smaller element can be pushed over top element of stack
            stack.push(i);
        }
        return res;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode temp = head;
        ListNode greater = new ListNode(-1);
        ListNode greaterDummy = greater;
        ListNode lower = new ListNode(-1);
        ListNode lowerDummy = lower;
        while (temp!=null){
            if(temp.val >=x ) {
                greater.next = temp;
                greater = greater.next;
            }
            else {
                lower.next = temp;
                lower = lower.next;
            }
            temp = temp.next;
        }
        greater.next = null;
        lower.next = greaterDummy.next;

        return lowerDummy.next;
    }

    public ListNode formInput(int[] array){
        ListNode head = new ListNode();
        ListNode dummy = head;
        for (int n: array){
            ListNode node = new ListNode(n);
            head.next = node;
            head = head.next;
        }
        return dummy.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode();
        ListNode dummy2 = dummy;

        for (ListNode list : lists){
            if(dummy.next == null) {
                dummy.next = list;
                continue;
            }
            ListNode dummy3 = dummy.next;
            while (list!=null){
                ListNode node = new ListNode(list.val);
                if(list.val <= dummy3.val){
                    node.next = dummy3;
                    dummy3 = node;
                }
                else{
                    ListNode temp = dummy3.next;
                    dummy3.next = node;
                    node.next = temp;
                }
                list = list.next;
            }
        }
        return dummy2.next;
    }

    public void test2(ListNode head){
        ListNode dummy1 = new ListNode();
        dummy1.next = head;
        ListNode dummy2 = head;
        ListNode dummy3 = new ListNode();
        dummy3.next = head;

        while (dummy1!=null){
            dummy1.val = 5;
            dummy1 = dummy1.next;
        }
        System.out.println("");
    }
    /*Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
        Output: 7 -> 8 -> 0 -> 7*/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sizel1 = getSize(l1);
        int sizel2 = getSize(l2);

        int num1 = constructNum(l1, --sizel1);
        int num2 = constructNum(l2, --sizel2);

        int res = num1 + num2;
        String resStr = String.valueOf(res);

        ListNode dummy = new ListNode();
        ListNode dummy2 = dummy.next;

        for (char ch : resStr.toCharArray()){
            dummy2.next = new ListNode(Character.getNumericValue(ch));
            dummy2 = dummy2.next;
        }

        return dummy.next;
    }

    private int constructNum(ListNode node, int size) {
        int num = 0;

        while (node!=null){
            num+= (node.val * Math.pow(10, size--));
        }
        return num;
    }


    private int getSize(ListNode node){
        int i=0;
        while(node!=null){
            node = node.next;
            i++;
        }
        return i++;
    }

    public static void main(String[] args) {
        LinkedListManipulation obj = new LinkedListManipulation();

        ListNode input1 = obj.formInput(new int[]{2,4,5});
        ListNode input2 = obj.formInput(new int[]{1,3,4});
       // obj.mergeKLists(new ListNode[]{input1, input2});
        obj.test2(input1);


    }
}

