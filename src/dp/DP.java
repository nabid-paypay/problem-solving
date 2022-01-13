package dp;

import trie.MapSum;

import java.util.*;

public class DP {
    public int maxSubArray(int[] A) {
        int max = A[0];
        int maxCurrent = A[0];

        for (int i = 1; i < A.length; i++) {
            maxCurrent = Math.max(maxCurrent + A[i], A[i]);
            max = Math.max(max, maxCurrent);
        }
        return max;
    }
    //tenet bb

    public String longestPalindrome(String s) {
        if (isPalindrome(s, 0, s.length() - 1)) return s;
        int len = s.length();
        int[][] cache = new int[len][len];

        for (int[] row : cache)
            Arrays.fill(row, -1);

        helper(s, 0, s.length() - 1, cache);
        int max = 0;
        int low = 0, high = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (max < cache[i][j]) {
                    max = cache[i][j];
                    low = i;
                    high = j;
                }
            }
        }
        return s.substring(low, high + 1);
    }


    private void helper(String s, int i, int j, int[][] cache) {
        if (j >= s.length()) return;

        if (cache[i][j] >= 0) return;

        else if (isPalindrome(s, i, j)) {
            cache[i][j] = j - i;
        } else {
            cache[i][j] = 0;
            helper(s, i + 1, j, cache);
            helper(s, i, j - 1, cache);
            //helper(s, i, i+1, cache);
        }
    }

    public boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) return false;
        }
        return true;
    }

    public String longestPalinBruteForce(String s) {
        int n = s.length();
        int start = 0, end = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1]);
                if (dp[i][j] == true && j - i > end - start) {
                    start = i;
                    end = j;
                }
            }
        }
        return n == 0 ? "" : s.substring(start, end + 1);
    }

    public int climbStairs(int n) {
        int[] cache = new int[n + 1];
        return climbStairsHelper(0, n, cache);
    }

    private int climbStairsHelper(int i, int n, int[] cache) {
        if (i > n) return 0;
        if (i == n) return 1;
        if (cache[i] > 0) return cache[i];
        cache[i] = climbStairsHelper(i + 1, n, cache) + climbStairsHelper(i + 2, n, cache);
        return cache[i];
    }

    public int climbStairs2(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    public int uniquePaths(int m, int n) {
        int[][] cache = new int[m][n];
        return uniquePathsHelper(m - 1, n - 1, m, n, cache);

    }

    private int uniquePathsHelper(int i, int j, int row, int col, int[][] cache) {
        if (i < 0 || j < 0) return 0;
        if (i == 0 || j == 0) return 1;

        if (cache[i][j] > 0) return cache[i][j];

        else cache[i][j] = uniquePathsHelper(i, j - 1, row, col, cache) +
                uniquePathsHelper(i - 1, j, row, col, cache);

        return cache[i][j];
    }

    public int uniquePathsDP(int m, int n) {
        int[][] cache = new int[m][n];
        for (int i = 0; i < m; i++) {
            cache[i][0] = 1;
        }

        for (int j = 0; j < n; j++) {
            cache[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cache[i][j] = cache[i][j - 1] + cache[i - 1][j];
            }
        }
        return cache[m - 1][n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1) return 0;
        boolean check = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 0) check = true;
            }
            if (!check) return 0;
            check = false;
        }

        for (int i = 0; i < obstacleGrid[0].length; i++) {
            for (int j = 0; j < obstacleGrid.length; j++) {
                if (obstacleGrid[j][i] == 0) check = true;
            }
            if (!check) return 0;
            check = false;
        }


        int[][] cache = new int[m][n];
        return uniquePathsWithObstaclesHelper(m - 1, n - 1, m, n, obstacleGrid, cache);
    }

    private int uniquePathsWithObstaclesHelper(int i, int j, int row, int col,
                                               int[][] obstacleGrid, int[][] cache) {
        if (obstacleGrid[i][j] == 1) return 0;
        if (i < 0 || j < 0) return 0;
        if (i == 0 || j == 0) return 1;

        if (cache[i][j] > 0) return cache[i][j];

        else cache[i][j] = uniquePathsWithObstaclesHelper(i, j - 1, row, col, obstacleGrid, cache) +
                uniquePathsWithObstaclesHelper(i - 1, j, row, col, obstacleGrid, cache);

        return cache[i][j];
    }

    public int uniquePathsWithObstaclesDP(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) return 0;

        int[][] cache = new int[row][col];
        cache[0][0] = 1;
        for (int i = 1; i < row; i++) {
            if (obstacleGrid[i][0] == 0) cache[i][0] = 1;
            else break;
        }

        for (int j = 1; j < col; j++) {
            if (obstacleGrid[0][j] == 0) cache[0][j] = 1;
            else break;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] != 1) {
                    cache[i][j] = cache[i][j - 1] + cache[i - 1][j];
                }
            }
        }
        return cache[row - 1][col - 1];
    }

    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] cache = new int[row][col];

        cache[0][0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            cache[i][0] = grid[i][0] + cache[i - 1][0];
        }

        for (int j = 1; j < col; j++) {
            cache[0][j] = grid[0][j] + cache[0][j - 1];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                cache[i][j] = Math.min(cache[i - 1][j], cache[i][j - 1]) + grid[i][j];
            }
        }
        return cache[row - 1][col - 1];
    }

    public int minPathSumSpaceOptimized(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        for (int j = 1; j < col; j++) {
            grid[0][j] = grid[0][j] + grid[0][j - 1];
        }

        for (int i = 1; i < row; i++) {
            grid[i][0] = grid[i][0] + grid[i - 1][0];
            for (int j = 1; j < col; j++) {
                grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }

        return grid[row - 1][col - 1];
    }

    public int numTrees(int n) {
        if (n <= 2) return n;
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            ans = ans * i;
        }
        return ans - 1;
    }

    public int maxProfit(int[] prices) {
        int len = prices.length;
        int profit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            profit = Math.max(profit, prices[i] - minPrice);
        }
        return profit;
    }

    public int lengthOfLIS(int[] nums) {
        int[] cache = new int[nums.length];
        return lengthOfLISHelper(nums, Integer.MIN_VALUE, 0, cache);
    }

    private int lengthOfLISHelper(int[] nums, int prevValue, int curPos, int[] cache) {
        if (curPos == nums.length) return 0;

        int taken = 0;
        if (nums[curPos] > prevValue) {
            if (cache[curPos + 1] > 0) {
                taken = 1 + cache[curPos + 1];
            } else {
                taken = 1 + lengthOfLISHelper(nums, nums[curPos], curPos + 1, cache);
            }

        }
        int notTaken = 0;
        if (cache[curPos + 1] > 0) {
            notTaken = cache[curPos + 1];
        } else notTaken = lengthOfLISHelper(nums, prevValue, curPos + 1, cache);
        cache[curPos + 1] = Math.max(taken, notTaken);
        return cache[curPos + 1];
    }

    public int lengthOfLIS_DP(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = Integer.MIN_VALUE;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] <= dp[j]) {
                    dp[i] = 1 + dp[j];
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max == Integer.MIN_VALUE ? 1 : max;
    }

    public int countSubstrings(String s) {
        int n = s.length();
        int count = 0;
        boolean[][] dp = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {

                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1]);

                if (dp[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public String longestPalindromeConstantSpace(String s) {
        if (s == null || s.isEmpty()) return "";
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i); //racecar
            int len2 = expandAroundCenter(s, i, i + 1); //abba
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2; //see
                end = i + len / 2; //see
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    int count = 0;

    public int countSubstringsConstantSpace(String s) {
        if (s == null || s.isEmpty()) return 0;
        //  int count = 0;
        for (int i = 0; i < s.length(); i++) {
            expandAroundCenterCountSubstrings(s, i, i); //racecar
            expandAroundCenterCountSubstrings(s, i, i + 1); //abba
        }
        return count;
    }

    private void expandAroundCenterCountSubstrings(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            count++;
        }
    }

    public int minDistance(String word1, String word2) {
        int[][] cache = new int[word1.length()][word2.length()];
        return helper(word1, word2, word1.length(), word2.length(), cache);
    }

    private int helper(String word1, String word2, int m, int n, int[][] cache) {
        if (m == 0) return n;
        if (n == 0) return m;
        if (cache[m - 1][n - 1] > 0) return cache[m - 1][n - 1];
        if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
            cache[m - 1][n - 1] = helper(word1, word2, m - 1, n - 1, cache);
            return cache[m - 1][n - 1];
        } else {
            return cache[m - 1][n - 1] = 1 + Math.min(
                    Math.min(helper(word1, word2, m, n - 1, cache),
                            helper(word1, word2, m - 1, n, cache)),
                    helper(word1, word2, m - 1, n - 1, cache));
        }
    }

    public int minDistanceDP(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                    ;
                }
            }
        }
        return dp[len1][len2];
    }

    public int minDistanceDPV2(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                    ;
                }
            }
        }
        return dp[len1][len2];
    }

    int decodeCount = 0;

    public int numDecoding(String s) {
        if(s == null || s.isEmpty() || s.charAt(0) == '0') return 0;
        int[] cache = new int[s.length()+1];
        return numDecodingHelper(s, 0, cache);
    }

    public int numDecodingHelper(String s, int index, int[] cache) {
        if(index == s.length()){
            return 1;
        }

        if(s.charAt(index) == '0'){
            return 0;
        }

        if(index == s.length() -1) {
            return 1;
        }

        if(cache[index]>0) return cache[index];

        int ans = numDecodingHelper(s, index+1, cache);
        if(Integer.parseInt(s.substring(index, index+2)) <=26){
            ans+= numDecodingHelper(s, index+2, cache);
        }
        cache[index] = ans;
        return ans;
    }

    public int numDecodingDP(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') return 0;
        int[] dp = new int[s.length()+1];
        dp[0] = 1;
        dp[1] = s.charAt(1) == '0' ? 0 : 1; //if there is only char and if it's not 0 then 1. Base case

        for (int i = 2; i < dp.length; i++) {
            if(s.charAt(i-1)!='0'){
                dp[i] = dp[i] + dp[i-1];
            }
            int twoDigit = Integer.parseInt(s.substring(i-2, i));
            if(twoDigit>=10 && twoDigit<=26) {
                dp[i] = dp[i] + dp[i-2];
            }
        }
        return dp[s.length()];
    }

    public int maximalSquare(int[][] matrix) {
        int rows = matrix.length;
        int cols = rows>0 ? matrix[0].length : 0;
        int[][] dp = new int[rows+1][cols+1];
        int max = 0;

        for (int i = 1; i <=rows ; i++) {
            for (int j = 1; j <=cols ; j++) {
                if(matrix[i-1][j-1] == 1){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        for (int i = 0; i <dp.length ; i++) {
            for (int j = 0; j <dp[i].length ; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return max*max;
    }

    public static void main(String[] args) {
        DP obj = new DP();
        obj.maximalSquare(new int[][]{
                {0,1,1,1, 0},
                {1,1, 1, 1, 0},
                {0, 1, 1, 1, 1},
                {0 ,1, 1 ,1, 1},
                {0 ,0, 1, 1, 1}});
    }
}
