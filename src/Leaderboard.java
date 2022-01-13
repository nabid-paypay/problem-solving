import java.util.*;
import java.util.stream.Collectors;

class Leaderboard {
    Map<Integer, Integer> map = new HashMap<>();
    TreeMap<Integer, Integer> treeMap = new TreeMap<>(Collections.reverseOrder()); //score er against e koi jon.
    public Leaderboard() {

    }

    public void addScore(int playerId, int score) {
        if(!map.containsKey(playerId)){
            map.put(playerId, score);
            treeMap.put(score, treeMap.getOrDefault(score, 0) + 1);
        }
        else{
            int prevScore =  map.get(playerId);
            int playerCount = treeMap.get(prevScore);
            if(playerCount == 1){
                treeMap.remove(prevScore);
            }
            else {
                treeMap.put(prevScore, playerCount -1);
            }
            int totalScore = prevScore + score;
            map.put(playerId, totalScore);
            treeMap.put(totalScore, treeMap.getOrDefault(totalScore, 0) + 1);
        }
    }

    public int top(int K) {
        int sum = 0;
        int count = 0;
        for(Map.Entry<Integer, Integer> entry: this.treeMap.entrySet()){
            int score = entry.getKey();
            int players = entry.getValue();
            for(int i=0; i<players; i++){
                sum += score;
                count++;
                if(count == K){
                    break;
                }
            }
            if(count == K){
                break;
            }
        }

        return sum;
    }

    public void reset(int playerId) {
        int score = map.get(playerId);
        map.remove(playerId);
        treeMap.put(score, treeMap.get(score)-1);
        if(treeMap.get(score) == 0){
            treeMap.remove(score);
        }
    }

    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard ();
        leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
        leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
        leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
        leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
        leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
        System.out.println(leaderboard.top(1));       // returns 73;
        leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
        leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
        leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
        System.out.println(leaderboard.top(3));            // returns 141 = 51 + 51 + 39;

        //TreeMap<Integer, Integer> treeMap = new TreeMap<>(Arrays.asList(nums));
        //System.out.println(treeMap.tailMap(4).size());
        int[] nums = new int[]{1, 2};
        TreeSet<Integer> treeSet = new TreeSet<>();
        NavigableSet<Integer> navigableSet = treeSet.tailSet(4, true);
    }
}
