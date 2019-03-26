package thinking;

import base.Utils;

public class DynamicProgramming {

    public static int zeroOneBackpack(int[] items, int w) {
        boolean[][] status = new boolean[items.length][w + 1];
        status[0][0] = true;
        status[0][items[0]] = true;

        for (int i = 1, len = items.length - 1; i < len; ++i) {
            // Don't add.
            for (int j = 0; j <= w; ++j) {
                if (status[i - 1][j]) {
                    status[i][j] = true;
                }
            }

            // Add.
            for (int j = 0; j <= w - items[i]; ++j) {
                if (status[i - 1][j]) {
                    status[i][j + items[i]] = true;
                }
            }
        }

        for (int k = w; k >= 0; --k) {
            if (status[items.length - 1][k]) {
                return k;
            }
        }

        return 0;
    }

    public static void double11Shopping(int[] items, int price) {
        if (items == null || items.length < 1 || price < 0) return;

        final int n = items.length;
        final int m = 3 * price;
        boolean[][] states = new boolean[n][m + 1];

        states[0][0] = true;
        states[0][items[0]] = true;

        for (int i = 1; i < n; ++i) {
            // Don't add.
            for (int j = 0; j <= m; ++j) {
                if (states[i - 1][j]) {
                    states[i][j] = true;
                }
            }

            // Add.
            for (int j = 0; j < m - items[i]; ++j) {
                if (states[i - 1][j]) {
                    states[i][j + items[i]] = true;
                }
            }
        }

        int finalP = -1;
        for (int i = price; i <= m; ++i) {
            if (states[n - 1][i]) {
                finalP = i;
                break;
            }
        }

        if (finalP < 0) return;

        for (int i = n - 1; i >= 1; --i) {
            if (finalP - items[i] >= 0 &&
                    (states[i - 1][finalP - items[i]] || states[i - 1][finalP])) {
                Utils.print(items[i] + " ");
                finalP -= items[i];
            }
        }

        // Check the 0 position.
        if (finalP > 0) {
            Utils.print(items[0]);
        }
    }

    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int[][] sum = new int[m][n];

        sum[0][0] = grid[0][0];

        for (int i = 1; i < m; i++) {
            sum[i][0] = sum[i - 1][0] + grid[i][0];
        }

        for (int j = 1; j < n; j++) {
            sum[0][j] = sum[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                sum[i][j] = Math.min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
            }
        }

        return sum[m - 1][n - 1];
    }
}
