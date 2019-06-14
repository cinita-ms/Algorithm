package thinking;

import util.Utils;

public class Backtracking {

    // 8 queens.
    public static void cal8queens(boolean printAll) {
        int[] results = new int[8]; // index -> row，value -> column.

        isFound = false;
        rCal8queens(0, results, printAll);
    }

    private static boolean isFound = false;

    private static void rCal8queens(int row, int[] results, boolean printAll) {
        if (isFound) {
            return;
        }

        if (row == 8) {
            printResults(results);

            if (!printAll) {
                isFound = true;
            }

            return;
        }

        for (int column = 0; column < 8; ++column) {
            if (isOK(row, column, results)) {
                results[row] = column;
                rCal8queens(row + 1, results, printAll);
            }
        }
    }

    private static boolean isOK(int row, int column, int[] results) {
        if (row == 0) {
            return true;
        }

        int leftUp = column - 1;
        int rightUp = column + 1;
        for (int i = row - 1; i >= 0; ++i) {
            if (results[i] == column) { // *
                return false;           // *
            }

            if (leftUp >= 0) {
                if (results[row] == leftUp) { // *
                    return false;             //  *
                }
            }

            if (rightUp < 8) {
                if (results[row] == rightUp) { //  *
                    return false;              // *
                }
            }

            --leftUp;
            ++rightUp;
        }

        return true;
    }

    private static void printResults(int[] results) {
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (column == results[row]) {
                    Utils.print("*");
                } else {
                    Utils.print(" ");
                }
            }
            Utils.println("");
        }
    }

    // 0-1 backpack.
    private static int maxW;
    private static boolean[][] memo;

    /**
     * @param items Source items.
     * @param w     The weight that the backpack can load.
     */
    public static int zeroOneBackpack(int[] items, int w) {
        maxW = Integer.MIN_VALUE;
        memo = new boolean[items.length][w + 1];
        zeroOneBackpackRecur(items, w, 0, 0);
        return maxW;
    }

    /**
     * @param items Source items.
     * @param w     The weight that the backpack can load.
     * @param i     Current item position.
     * @param cw    Current weight that backpack loaded.
     */
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

    // Pattern.
    public static class Pattern {

        private final char[] pattern;
        private boolean isMatch;

        public Pattern(char[] pattern) {
            if (pattern == null) {
                throw new IllegalArgumentException();
            }

            this.pattern = pattern;
        }

        public boolean match(char[] text) {
            if (text == null) {
                return false;
            }

            isMatch = false;
            rMatch(0, 0, text);
            return isMatch;
        }

        private void rMatch(int iPattern, int iText, char[] text) {
            if (isMatch) return;

            if (iPattern == pattern.length) {
                if (iText == text.length) {
                    isMatch = true;
                }

                return;
            }

            if (iText < text.length && pattern[iPattern] == text[iText]) {
                rMatch(iPattern + 1, iText + 1, text);
            } else if (pattern[iPattern] == '?') {
                rMatch(iPattern, iText + 1, text);
                rMatch(iPattern + 1, iText + 1, text);
            } else if (pattern[iPattern] == '*') {
                for (int i = 0; i <= text.length - iText; ++i) {
                    rMatch(iPattern + 1, iText + i, text);
                }
            }
        }
    }
}
