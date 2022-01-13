package heap;
//import array_manipulation.BinarySearch;

import java.util.*;

public class HeapManipulation {
    public int kthSmallest(int[][] matrix, int k) {
        int N = matrix.length;
        PriorityQueue<HeapForKthSmallest> minHeap = new PriorityQueue<>(Math.min(N, k),
                Comparator.comparingInt(a -> a.val));

        for (int i = 0; i <Math.min(N,k) ; i++) {
            minHeap.add(new HeapForKthSmallest(i, 0, matrix[i][0]));
        }
        HeapForKthSmallest elem = minHeap.peek();
        while (k-- > 0){
            elem = minHeap.poll();
            int row = elem.getRow();
            int col = elem.getCol();

            if(col < N-1){
                minHeap.add(new HeapForKthSmallest(row, col+1, matrix[row][col+1]));
            }
        }
        return elem.getVal();
    }
    public static class Point{
        int x;
        int y;
        double dist;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.dist = Math.sqrt(x*x + y*y);
        }
    }
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingDouble(a->-1*a.dist));

        for (int[] point : points){
            Point temp = new Point(point[0], point[1]);
            pq.add(temp);
            if(pq.size()>K) pq.poll();
        }
        int[][] res = new int[K][];
        int i=0;
        while (!pq.isEmpty()){
            Point p = pq.poll();
            res[i++] = new int[]{p.x, p.y};
        }
        return res;
    }

    public static void main(String[] args) {
        HeapManipulation obj = new HeapManipulation();
        System.out.println(Arrays.deepToString(obj.kClosest(new int[][]{
                {3, 3},
                {5, -1},
                {-2, 4}}, 2)));
    }
}
