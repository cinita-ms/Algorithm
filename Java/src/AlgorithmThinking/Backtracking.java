package AlgorithmThinking;

public class Backtracking {

    // 0-1 backpack

    private static int maxW;
    private static boolean[][] memo;

    public static int zeroOneBackpack(int[] items, int w, int i, int cw) {
        maxW = Integer.MIN_VALUE;
        memo = new boolean[items.length][w + 1];
        zeroOneBackpackRecur(items, w, i, cw);
        return maxW;
    }

    private static void zeroOneBackpackRecur(int[] items, int w, int i, int cw) {
        if (cw == w || i == items.length - 1) {
            if (cw > maxW) {
                maxW = cw;
            }

            return;
        }

        if (memo[i][cw]) return;

        memo[i][cw] = true;

        zeroOneBackpackRecur(items, w, i + 1, cw);
        if (cw + items[i] <= w) {
            zeroOneBackpackRecur(items, w, i + 1, cw + items[i]);
        }
    }
}
