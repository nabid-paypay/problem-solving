import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrickWall {
    public int leastBricks(List<List<Integer>> wall) {
        if(wall.isEmpty()) return 0;
        Map<Integer, Integer> map = new HashMap<>();

        int max = 0;
        for (List<Integer> row : wall){
            int pos = 0;
            for (int i = 0; i < row.size() -1 ; i++) {
                pos += row.get(i);
                map.put(pos, map.getOrDefault(pos, 0) + 1);
                max = Math.max(max, map.get(pos));
            }
        }

        return wall.size() - max;

    }

    public void sampleTest(){System.out.println("OK");
    }
}
