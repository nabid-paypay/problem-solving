class MyCalendarTwo {
    private static class SegmentTreeNode{
        int l, r;
        int k;
        SegmentTreeNode left = null;
        SegmentTreeNode right = null;

        public SegmentTreeNode(int l, int r, int k){
            this.l = l;
            this.k = k;
            this.r = r;
        }
        @Override
        public String toString() {
            return "SegmentTreeNode{" +
                    "l=" + l +
                    ", r=" + r +
                    ", k=" + k +
                    ", left=" + (left!=null && left.k > 0 ? left : "") +
                    ", right=" + (right!=null && right.k > 0 ? right : "") +
                    '}';
        }
    }

    private int query(SegmentTreeNode node, int lq, int rq){
        if(lq > rq  || node == null || rq < node.l || lq > node.r){
            return 0;
        }

        if( (lq <= node.l && rq >= node.r)){ //|| node.left == null
            return node.k;
        }

        int mid = node.l + (node.r - node.l) / 2;
        if(rq <= mid){
            return query(node.left, lq, rq);
        }
        else if(lq >= mid){
            return query(node.right, lq, rq);
        }
        else {
            return Math.max(query(node.left, lq, rq), query(node.right, lq, rq));
        }
    }

    private int update(SegmentTreeNode node, int lq, int rq, int val){
        if(lq > rq || rq < node.l || lq > node.r){
            return 0;
        }

        if(lq <= node.l && rq >= node.r){
            node.k += val;
            node.left = null;
            node.right = null;
            return node.k;
        }

        int mid = node.l + (node.r - node.l)/2;
        if(node.left == null){
            node.left = new SegmentTreeNode(node.l, mid, node.k);
            node.right = new SegmentTreeNode(mid+1, node.r, node.k);
        }

        int left = update(node.left, lq, rq, val);
        int right = update(node.right, lq, rq, val);

        return node.k = Math.max(left, right);
    }


    SegmentTreeNode root;
    public MyCalendarTwo() {
        root = new SegmentTreeNode(0, 1_000_000_000, 0);
    }

    public boolean book(int start, int end) {
        int k = query(root, start, end - 1);
        if (k >= 2) return false;

        update(root, start, end - 1, 1);
        System.out.println(root.toString());
        return true;
    }

    public static void main(String[] args) {
        MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
        System.out.println(myCalendarTwo.book(10, 20)); // return True, The event can be booked.
        System.out.println(myCalendarTwo.book(50, 60)); // return True, The event can be booked.
        System.out.println(myCalendarTwo.book(10, 40)); // return True, The event can be double booked.
        System.out.println(myCalendarTwo.book(5, 15));  // return False, The event ca not be booked, because it would result in a triple booking.
        System.out.println(myCalendarTwo.book(5, 10)); // return True, The event can be booked, as it does not use time 10 which is already double booked.
        System.out.println(myCalendarTwo.book(25, 55)); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
    }

}
