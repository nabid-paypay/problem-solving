class BookMyShow {
    int[][] grid;
    int seatCanBeBooked = 0;
    int currRow = 0;
    int currCol = 0;
    int rows;
    int cols;
    public BookMyShow(int n, int m) {
        grid = new int[n][m];
        seatCanBeBooked = n * m;
        rows = n;
        cols = m;
    }

    public int[] gather(int k, int maxRow) {
        if(k > seatCanBeBooked){
            return new int[]{};
        }
        if(maxRow < currRow){
            return new int[]{};
        }


        int[] res = new int[]{currRow, currCol};
        while (k > 0){
            grid[currRow++][currCol++] = 1;
            if(currCol == cols){
                if(k > 1){
                    //reverse();
                    return new int[]{};
                }
                currRow++;
                currCol = 0;
            }
            k--;
        }

        return res;
    }

    public boolean scatter(int k, int maxRow) {
        return true;
    }
}