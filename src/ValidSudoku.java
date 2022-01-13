import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        if(!rowCheck(board)) return false;
        if(!colCheck(board)) return false;
        if(!boxCheck(board)) return false;
        return true;
    }

    public boolean rowCheck(char[][] board){
        Set<Character> rowSet = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (rowSet.contains(board[i][j])) return false;
                if (board[i][j] != '.') rowSet.add(board[i][j]);
            }
            rowSet.clear();
        }
        return true;
    }

    public boolean colCheck(char[][] board){
        Set<Character> colSet = new HashSet<>();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if (colSet.contains(board[i][j])) return false;
                if (board[i][j] != '.') colSet.add(board[i][j]);
            }
            colSet.clear();
        }
        return true;
    }

    public boolean boxCheck(char[][] board){
        int i = 0, j = 0;
        while(i < 9 && j < 9) {
            while(i < 9) {
                if(!isValid(board, i, j)) return false;
                i += 3;
            }
            i = 0;
            j += 3;
        }
        return true;
    }

    public boolean isValid(char[][] board, int x, int y){

        Set<Character> boxSet = new HashSet<>();
        for (int i = x; i < x+3; i++) {
            for (int j = y; j < y+3; j++) {
                if (boxSet.contains(board[i][j])) return false;
                if (board[i][j] != '.') boxSet.add(board[i][j]);
            }
        }
        boxSet.clear();
        return true;
    }




}
