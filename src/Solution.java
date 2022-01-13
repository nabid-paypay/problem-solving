import java.util.*;

class Solution {
    TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    TreeMap<Integer, Integer> timeline = new TreeMap<>();

    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a, b)-> a[0] - b[0]);
        for(int[] e : events){
            System.out.println(book2(e[0], e[1]));
        }


        return 0;
    }

    public int book2(int start, int end) {
        timeline.put(start, timeline.getOrDefault(start, 0) + 1);
        timeline.put(end, timeline.getOrDefault(end, 0) - 1);

        int max =0, room =0;
        for(int count : timeline.values()){
            max = Math.max(max, room+=count);
        }

        return max;
    }


    public boolean book(int start, int end) {
        if(treeMap.size() == 0){
            treeMap.put(start, end);
            return true;
        }
        if(inter(treeMap.ceilingKey(start), start, end) || inter(treeMap.floorKey(start), start, end)){
            return false;
        }
        treeMap.put(start, end);
        return true;
    }

    private boolean inter(Integer prev, int start, int end){
        if(prev == null){
            return false;
        }
        Integer next = treeMap.get(prev);

        int lo = Math.max(start, prev);
        int hi = Math.min(end, next);

        if(lo<=hi){ //intersects
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int a = solution.maxTwoEvents(new int[][]{{66,97,90},{98,98,68},{38,49,63},{91,100,42},
                {92,100,22},{1,77,50},{64,72,97}});
        //int a = solution.maxTwoEvents(new int[][]{{1,3,2},{4,5,2},{2,4,3}});
        System.out.println(a);
    }

}