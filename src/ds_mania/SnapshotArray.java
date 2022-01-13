package ds_mania;
import java.util.*;

class SnapshotArray {
    List<TreeMap<Integer, Integer>> snapshot;
    int currSnap = 0;
    public SnapshotArray(int length) {
        snapshot = new ArrayList<>();
        for(int i=0; i<length; i++){
            snapshot.add(i, new TreeMap<>());
            snapshot.get(i).put(0, 0); //nice
        }
    }

    public void set(int index, int val) {
        snapshot.get(index).put(currSnap, val); //snapshot er index
    }

    public int snap() {
        return currSnap++;
    }

    public int get(int index, int snap_id) {
        return snapshot.get(index).floorEntry(snap_id).getValue();
    }

    public static void main(String[] args) {
        SnapshotArray snapshotArray = new SnapshotArray(3);
        snapshotArray.set(1, 18);
        snapshotArray.set(1, 4);
        snapshotArray.snap();
        snapshotArray.get(0, 0);
        snapshotArray.set(0, 20);
        snapshotArray.snap();
        snapshotArray.set(0, 2);
        snapshotArray.set(1, 1);
        snapshotArray.get(1, 1);
        snapshotArray.get(1, 0);
    }
}

//["SnapshotArray","set","set","0snap","get","set","1snap","set","set","get","get"]
//[[3],           [1,18],[1,4],  [],  [0,0],[0,20], [],  [0,2], [1,1],[1,1],[1,0]]
