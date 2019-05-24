package thinking;

import util.IntPair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Degreey {

    // 不重复区间最多选择。
    public static List<IntPair> f1(List<IntPair> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        list.sort(Comparator.comparingInt(o -> o.first));

        List<IntPair> lastResults = new ArrayList<>();

        for (int i = 0; i < list.size(); ++i) {

            List<IntPair> results = null;
            IntPair last = null;
            for (int j = i; j < list.size(); ++j) {
                if (j == i) {
                    IntPair head = list.get(j);

                    int k = j - 1;
                    if (k >= 0) {
                        for (; k >= 0; --k) {
                            if (list.get(k).second <= head.first) {
                            return lastResults;
                        }
                    }}

                    results = new ArrayList<>();
                    results.add(head);

                    last = head;
                    continue;
                }

                IntPair cur = list.get(j);
                if (cur.first < last.second) {
                    continue;
                }

                results.add(cur);
                last = cur;

                if (j == list.size() - 1) {
                    if (results.size() > lastResults.size()) {
                        lastResults = results;
                    }
                }
            }
        }

        return lastResults;
    }

}
