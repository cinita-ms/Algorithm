package recursion;

import java.util.Arrays;

public class WalkStep {

    private int[] results;

    public int getStep(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        if (results == null) {
            results = new int[n];
            Arrays.fill(results, 0);
        }

        if (results[n] > 0) {
            return results[n];
        }

        int result = getStep(n - 1) + getStep(n - 2);
        results[n] = result;
        return result;
    }
}