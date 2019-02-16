package recursion;

import java.util.HashMap;
import java.util.Map;

public class WalkStep {

    private Map<Integer, Integer> results = new HashMap<>();

    public int getStep(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        if (results.containsKey(n)) {
            return results.get(n);
        }

        int result = getStep(n - 1) + getStep(n - 2);
        results.put(n, result);
        return result;
    }
}