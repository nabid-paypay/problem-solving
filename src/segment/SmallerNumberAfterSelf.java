package segment;
import java.util.*;

public class SmallerNumberAfterSelf {
    class SegTreeNode {
        int min, max; // range [min, max]
        int count;
        SegTreeNode left, right;

        public int mid() {
            return ((max - min) / 2 + min);
        }

        public SegTreeNode(int min, int max) {
            this.min = min;
            this.max = max;
            count = 0;
        }

        @Override
        public String toString() {
            return "SegTreeNode{" +
                    "min=" + min +
                    ", max=" + max +
                    ", count=" + count +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new LinkedList<Integer>();

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i : nums) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }

        SegTreeNode root = new SegTreeNode(min, max);

        for (int i = nums.length - 1; i >= 0; i--) {
            list.add(0, find(nums[i] - 1, root)); // minus 1, in case there will be a equal one
            add(nums[i], root);
        }
        System.out.println(root);
        return list;
    }

    private int find(int x, SegTreeNode root) {
        if (root == null) return 0;

        if (x >= root.max) {
            return root.count;
        } else {
            int mid = root.mid();
            if (x <= mid) {
                return find(x, root.left);
            } else {
                return find(x, root.left) + find(x, root.right);
            }
        }
    }

    private void add(int x, SegTreeNode root) {
        if (x < root.min || x > root.max) return;

        root.count++;
        if (root.max == root.min) return;

        int mid = root.mid();
        if (x <= mid) {
            if (root.left == null) {
                root.left = new SegTreeNode(root.min, mid);
            }
            add(x, root.left);
        } else {
            if (root.right == null) {
                root.right = new SegTreeNode(mid+1, root.max);
            }
            add(x, root.right);
        }
    }

    public static void main(String[] args) {
        SmallerNumberAfterSelf obj = new SmallerNumberAfterSelf();
        obj.countSmaller(new int[]{5,2,6,1}).forEach(System.out::println);
    }
}
