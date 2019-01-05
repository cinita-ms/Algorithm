import java.util.HashMap;
import java.util.Map;

public class WalkStep {

    private Map<Integer, Integer> results = new HashMap<>();

    public int f1(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        if (results.containsKey(n)) {
            return results.get(n);
        }

        int result = f1(n - 1) + f1(n - 2);
        results.put(n, result);
        return result;
    }

    public int f2(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        int pre = 2;
        int prepre = 1;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = pre + prepre;
            prepre = pre;
            pre = result;
        }
        return result;
    }

    public static void main(String[] args) {
        WalkStep w = new WalkStep();
        System.out.printf("f1 = %d\n", w.f1(8));
        System.out.printf("f2 = %d\n", w.f2(8));
    }
}