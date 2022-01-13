class MyCalendarTwo2 {
    private static class SegmentTreeNode{
        int l, r; //left border right border
        int k, lazy;
        SegmentTreeNode left = null;
        SegmentTreeNode right = null;

        public SegmentTreeNode(int l, int r, int k){
            this.l = l;
            this.k = k;
            this.r = r;
            this.lazy = 0; //notice
        }
        @Override
        public String toString() {
            return "SegmentTreeNode{" +
                    "l=" + l +
                    ", r=" + r +
                    ", k=" + k +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private int query(SegmentTreeNode node, int lq, int rq){
        if(lq > rq || node == null || rq < node.l || lq > node.r){
            return 0;
        }

        if(lq <= node.l && rq >= node.r){
            return node.k;
        }

        normalize(node);

        return Math.max(query(node.left, lq, rq), query(node.right, lq, rq));
    }

    private void update(SegmentTreeNode node, int lq, int rq, int val){
        if(lq > rq || node == null || rq < node.l || lq > node.r){
            return;
        }

        if(lq <= node.l && rq >= node.r){
            node.k += val;
            node.lazy += val;
            return;
        }

        normalize(node);

        update(node.left, lq, rq, val);
        update(node.right, lq, rq, val);

        node.k = Math.max(node.left.k, node.right.k);
    }

    private void normalize(SegmentTreeNode node){
        if(node.l < node.r){ //left border < right border
            if(node.left == null && node.right == null){
                int mid = node.l + (node.r - node.l)/2;
                node.left = new SegmentTreeNode(node.l, mid, node.k);
                node.right = new SegmentTreeNode(mid+1, node.r, node.k);
            }
            else if(node.lazy > 0){
                node.left.k += node.lazy;
                node.left.lazy += node.lazy;

                node.right.k += node.lazy;
                node.right.lazy += node.lazy;
            }
        }

        node.lazy = 0;
    }

    SegmentTreeNode root;
    public MyCalendarTwo2() {
        root = new SegmentTreeNode(0, 1000, 0);
    }

    public boolean book(int start, int end) {
        int k = query(root, start, end - 1);
        if (k >= 2) return false;

        update(root, start, end - 1, 1);
        System.out.println(root.toString());
        return true;
    }

    public static void main(String[] args) {
        MyCalendarTwo2 myCalendarTwo2 = new MyCalendarTwo2();
        System.out.println(myCalendarTwo2.book(10, 20)); // return True, The event can be booked.
        System.out.println(myCalendarTwo2.book(50, 60)); // return True, The event can be booked.
        System.out.println(myCalendarTwo2.book(10, 40)); // return True, The event can be double booked.
        System.out.println(myCalendarTwo2.book(5, 15));  // return False, The event ca not be booked, because it would result in a triple booking.
        System.out.println(myCalendarTwo2.book(5, 10)); // return True, The event can be booked, as it does not use time 10 which is already double booked.
        System.out.println(myCalendarTwo2.book(25, 55)); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
    }

}
