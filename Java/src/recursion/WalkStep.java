package recursion;

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
        }

        if (results[n] > 0) {
            return results[n];
        }

        return results[n] = getStep(n - 1) + getStep(n - 2);
    }
}