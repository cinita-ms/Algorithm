package dynamic_programming;

import base.Utils;

public class DynamicProgramming {

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
}
