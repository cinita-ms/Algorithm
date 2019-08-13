package me.cinita.leetcode;

import me.cinita.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediumAlgorithm {

    // 字谜分组
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (Utils.isEmpty(strs)) {
            return null;
        }

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            if (s == null) continue;

            int[] table = new int[26];
            for (char c : s.toCharArray()) {
                table[c - 'a']++;
            }

            String key = arrayToKey(table);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }

    private static String arrayToKey(int[] target) {
        StringBuilder builder = new StringBuilder(target.length);
        for (int e : target) {
            builder.append(e);
        }

        return builder.toString();
    }

    // 无重复字符的最长子串
    public static int lengthOfLongestSubstring(String s) {
        if (Utils.isEmpty(s)) {
            return 0;
        }

        return 0;
    }
}
