package sorting;


import linked_list.ListNode;

import java.util.*;
import java.util.stream.*;

public class SortingManipulation {
    public void streamTest(){
        Map<Integer, String> map = new HashMap<>();
        map.put(10, "apple");
        map.put(20, "orange");
        map.put(30, "banana");
        map.put(40, "watermelon");
        map.put(50, "dragonfruit");

        // split a map into 2 List
        List<Integer> resultSortedKey = new ArrayList<>();
        List<String> resultValues = map.entrySet().stream()
                //sort a Map by key and stored in resultSortedKey
                .sorted(Map.Entry.<Integer, String>comparingByKey().reversed())
                .peek(e -> resultSortedKey.add(e.getKey()))
                .map(x -> x.getValue())
                // filter banana and return it to resultValues
                .filter(x -> !"banana".equalsIgnoreCase(x))
                .collect(Collectors.toList());

        resultSortedKey.forEach(System.out::println);
        resultValues.forEach(System.out::println);

    }
   /* public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, Set<String>> map = new HashMap<>();
        Map<Integer, Integer> map1 = new HashMap<>();

        for(int i=0; i<website.length; i++){
            map.putIfAbsent(website[i], new HashSet<>());
            map.get(website[i]).add(username[i]);
        }

        Set<Map.Entry<String,Set<String>>> s = map.entrySet();

        List<String> resultValues = map.entrySet().stream()
                .sorted(Map.Entry.<String, Set<String>> comparingByValue(new Comparator<Set<String>>() {
                    @Override
                    public int compare(Set<String> o1, Set<String> o2) {
                        int comp =  Integer.compare(o2.size(), o1.size());
                        return comp;
                    }
                }))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return resultValues;
    }*/

    class Visit {
        String username;
        int timestamp;
        String website;
        public Visit(String username, int timestamp, String website) {
            this.username = username;
            this.timestamp = timestamp;
            this.website = website;
        }

        public String toString() {
            return username+" "+timestamp+" "+website;
        }

    }

    Set<List<String>> generate3(List<String> history) {
        Set<List<String>> output = new HashSet<>();
        for(int i = 0; i < history.size(); i++) {
            for(int j = i + 1; j < history.size(); j++) {
                for(int k = j + 1; k < history.size(); k++) {
                    List<String> l = new ArrayList<>();
                    l.add(history.get(i));
                    l.add(history.get(j));
                    l.add(history.get(k));
                    output.add(l);
                }
            }
        }
        return output;
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Visit[] visits = new Visit[username.length];
        for (int i = 0; i < username.length; i++) {
            visits[i] = new Visit(username[i], timestamp[i], website[i]);
        }

        Arrays.sort(visits, Comparator.comparingInt(a -> a.timestamp));

        Map<String, List<String>> userHistory = new HashMap<>();

        for (Visit v : visits) {
            userHistory.putIfAbsent(v.username, new ArrayList<>());
            userHistory.get(v.username).add(v.website);
        }

        Map<List<String>, Integer> candidateOutput = new HashMap<>();

        for (List<String> history: userHistory.values()){
            Set<List<String>> h3comb = generate3(history);

            for (List<String> h : h3comb){
                candidateOutput.put(h, candidateOutput.getOrDefault(h, 0) + 1);
            }
        }

        List<String> output = new ArrayList<>();

        int max = 0;
        for (Map.Entry<List<String>, Integer> candidate :candidateOutput.entrySet()){
            if(candidate.getValue() > max){
                max = candidate.getValue();
                output = candidate.getKey();
            }
            else if(max == candidate.getValue()){
                if(candidate.getKey().toString().compareTo(output.toString())<0){
                    output = candidate.getKey();
                }
            }
        }
        return output;
    }

    public int[][] highFive(int[][] items) {

        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        for (int[] item : items){
            map.putIfAbsent(item[0], new PriorityQueue<>((a, b)-> Integer.compare(b,a)));
            map.get(item[0]).add(item[1]);
        }

        int[][] result = new int[map.keySet().size()][2];
        int k=0;
        for (int stdId: map.keySet()){
            int sum = 0;
            for (int i = 0; i <5 ; i++) {
                sum+= map.get(stdId).poll();
            }
            int avg = sum/5;
            int[] idAvg = new int[]{stdId, avg};
            result[k++] = idAvg;
        }

        Arrays.sort(result, Comparator.comparingInt(elem -> elem[0]));

        return result;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
    }

    public ListNode sortList(ListNode head) {
        if(head == null|| head.next == null) return head;

        ListNode mid = getMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        return merge(left, right);
    }


    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;

        while(left!=null && right !=null){
            if(left.val < right.val){
                tail.next = left;
                left = left.next;
            }
            else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }

        tail.next = (left!=null) ? left : right;

        return dummyHead.next;
    }

    private ListNode getMid(ListNode head) {
        ListNode midPrev = null;

        while(head!=null && head.next!=null){
            midPrev = (midPrev == null) ? head : midPrev.next;
            head = head.next.next;
        }

        ListNode mid = midPrev.next;
        midPrev.next = null; //see rundown.
        return mid;
    }


    private ListNode getMiddle(ListNode head) {
        if(head == null) return null;
        ListNode slow = head, fast = head;

        while (fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }
        int interval = 1;
        while(interval<lists.length){
            for (int i = 0; i + interval< lists.length; i=i+interval*2) {
                lists[i]=merge(lists[i],lists[i+interval]);
            }
            interval*=2;
        }

        return lists[0];
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

    public int connectSticks(int[] sticks) {
        if(sticks.length == 1) return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int stick : sticks){
            pq.add(stick);
        }

        int totalCost = 0;
        while (pq.size()>1){
            int stick1 = pq.poll();
            int stick2 = pq.poll();
            int cost = stick1 + stick2;

            totalCost+= cost;
            pq.add(stick1 + stick2);
        }
        return totalCost;
    }

    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> comparator = (o1, o2) -> {
            String[] o1Array = o1.split(" ");
            String identifier1 = o1Array[0];
            String[] o2Array = o2.split(" ");
            String identifier2 = o2Array[0];

            String content1 = Arrays.stream(o1Array).filter(str -> !str.equals(identifier1)).collect(Collectors.joining(" "));
            String content2 = Arrays.stream(o2Array).filter(str -> !str.equals(identifier2)).collect(Collectors.joining(" "));

            char o1FirstChar = content1.charAt(0);
            char o2FirstChar = content2.charAt(0);

            if(Character.isLetter(o1FirstChar) && Character.isLetter(o2FirstChar)){
                int comp =  content1.compareTo(content2);
                if(comp!=0) return comp;
                else {
                    return identifier1.compareTo(identifier2);
                }
            }
            else if(Character.isLetter(o1FirstChar) && Character.isDigit(o2FirstChar)){
                // the letter-log comes before digit-logs
                return -1;
            }
            else if(Character.isDigit(o1FirstChar) && Character.isLetter(o2FirstChar)){
                return 1;
            }
            else return 0;
        };

        Arrays.sort(logs, comparator);

        return logs;
    }

    public int compare(String o1, String o2) {
        String[] o1Array = o1.split(" ");
        String identifier1 = o1Array[0];
        String[] o2Array = o2.split(" ");
        String identifier2 = o2Array[0];

        String content1 = Arrays.stream(o1Array).filter(str -> !str.equals(identifier1)).collect(Collectors.joining(" "));
        String content2 = Arrays.stream(o2Array).filter(str -> !str.equals(identifier2)).collect(Collectors.joining(" "));
        return 0;
    }

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->-1*a[1]));
        pq.addAll(Arrays.asList(boxTypes));

        int boxSize = 0;
        int unit = 0;
        while (!pq.isEmpty()){
            int[] box = pq.poll();
            if(boxSize + box[0] <= truckSize){
                unit+= (box[0] * box[1]);
                boxSize+= box[0];
            }
            else {
                int temp = box[0];
                while (boxSize + temp != truckSize) temp--;
                unit+= (temp*box[1]);
                boxSize+= temp;
            }

            if(boxSize == truckSize) break;
        }
        return unit;

    }


    public static void main(String[] args) {
        SortingManipulation obj = new SortingManipulation();
        obj.maximumUnits(new int[][]{{5,10},{2,5},{4,7},{3,9}}, 10);
        obj.maximumUnits(new int[][]{{1,3},{2,2},{3,1}}, 4);
    }
}
