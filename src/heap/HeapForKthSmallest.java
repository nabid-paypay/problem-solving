package heap;
import java.util.*;
public class HeapForKthSmallest {
    int row;
    int col;
    int val;

    public HeapForKthSmallest(int row, int col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getVal() {
        return val;
    }

}
