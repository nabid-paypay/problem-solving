package ds_mania;

class SnakeGame {

    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */

    int[][] board;
    int[][] food;
    int[] pos = {0, 0};
    int score = 0;
    int foodPos = 0;
    public SnakeGame(int width, int height, int[][] food) {
        board = new int[height][width];
        this.food = food;
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String dir) {
        int i,j;
        if(dir.equals("R")){
            i = pos[0];
            j = pos[1]+1;
        }
        else if(dir.equals("L")){
            i = pos[0];
            j = pos[1]-1;
        }
        else if(dir.equals("U")){
            i = pos[0]-1;
            j = pos[1];
        }
        else {
            i = pos[0]+1;
            j = pos[1];
        }
        if(foodPos<food.length){
            int[] foo = this.food[foodPos];
            int x = foo[0];
            int y = foo[1];
            if(i == x && y == j) {
                score++;
                foodPos++;
                this.pos = new int[]{i,j};
                return score;
            }
        }

        this.pos = new int[]{i,j};

        if(i<0 || i>=board.length || j<0 || j>=board[0].length) {
            return -1;
        }
        return score;
    }

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame(3,2, new int[][]{{1,2}, {0,1}});
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("D"));
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("U"));
        System.out.println(snakeGame.move("L"));
        System.out.println(snakeGame.move("U"));
    }
}

